package dev.orgsusu.adaptertosscrypto.domain.service

import dev.orgsusu.domain.tosscert.port.outgoing.TossDecryptorPort
import im.toss.cert.sdk.TossCertSessionGenerator
import org.springframework.stereotype.Component
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.isAccessible

@Component
class TossDecryptorPort (
    private val tossCertSessionGenerator: TossCertSessionGenerator
): TossDecryptorPort {

    override fun <T : Any> decryptAll(session: String, encrypted: T): T {
        val tossSession = tossCertSessionGenerator.deserialize(session)
        val clazz = encrypted::class
        val constructor = clazz.primaryConstructor
            ?: throw IllegalArgumentException("No primary constructor found for ${clazz.simpleName}")

        val propertyMap = clazz.memberProperties
            .asSequence()
            .filterIsInstance<KProperty1<T, *>>()
            .onEach { it.isAccessible = true }
            .associateBy { it.name }

        val args = constructor.parameters.associateWith {
            val property = propertyMap[it.name]
            val value = property?.get(encrypted)

            when (value) {
                is String -> tossSession.decrypt(value)
                else -> value
            }
        }

        return constructor.callBy(args)
    }
}