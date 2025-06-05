package dev.orgsusu.adapterPersistence.database.domain.tosscert

import dev.orgsusu.domain.tosscert.port.outgoing.TossCertTokenPort
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisTossAccessTokenCacheAdapter(
    private val redisTemplate: StringRedisTemplate
) : TossCertTokenPort {

    companion object {
        private const val TOSS_TOKEN_KEY = "toss:access_token"
    }

    override fun getToken(): String? {
        return redisTemplate.opsForValue().get(TOSS_TOKEN_KEY)
    }

    override fun saveToken(token: String, expiresInSeconds: Long) {
        redisTemplate.opsForValue().set(
            TOSS_TOKEN_KEY,
            token,
            Duration.ofSeconds(expiresInSeconds)
        )
    }
}