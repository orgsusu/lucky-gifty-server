package dev.orgsusu.adaptertosscrypto.domain.service

import dev.orgsusu.adaptertosscrypto.global.TossSessionConfig
import dev.orgsusu.domain.tosscert.port.outgoing.TossDecryptorPort
import org.springframework.stereotype.Component
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.isAccessible

@Component
class TossDecryptorPort (
    private val tossCertSessionConfig: TossSessionConfig
): TossDecryptorPort {

    override fun <T : Any> decryptAll(session: String, encrypted: T): T {
        val tossSession = tossCertSessionConfig.tossCertSessionGenerator().deserialize(session)
        val clazz = encrypted::class
        val constructor = clazz.primaryConstructor
            ?: throw IllegalArgumentException("No primary constructor found for ${clazz.simpleName}")

        val args = mutableMapOf<KParameter, Any?>()

        for (param in constructor.parameters) {
            val property = clazz.memberProperties.find { it.name == param.name } as? KProperty1<T, *> ?: continue
            property.isAccessible = true

            val encryptedValue = property.get(encrypted) as? String?

            val decryptedValue = if (encryptedValue != null) {
                tossSession.decrypt(encryptedValue)
            } else {
                null
            }

            args[param] = decryptedValue
        }

        return constructor.callBy(args)
    }
}