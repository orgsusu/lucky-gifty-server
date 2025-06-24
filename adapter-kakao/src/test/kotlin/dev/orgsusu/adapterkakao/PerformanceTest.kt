package dev.orgsusu.adapterkakao

import dev.orgsusu.adapterkakao.domain.KakaoApiAdapter
import dev.orgsusu.adapterkakao.domain.mapper.KakaoDtoMapperImpl
import dev.orgsusu.adapterkakao.global.config.KakaoConfig
import dev.orgsusu.domain.kakao.port.outgoing.KakaoApiPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.system.measureTimeMillis
import kotlin.test.Test

@SpringBootTest(
    classes = [
        KakaoDtoMapperImpl::class,
        KakaoApiAdapter::class,
        KakaoConfig::class,
    ]
)
class PerformanceTest {
    @Autowired private lateinit var kakaoApiPort: KakaoApiPort

    @Test
    fun performance() {
        val initial = measureTimeMillis {
            kakaoApiPort.getAllCouponRanking(0, 20)
        }
        println("cold start: ${initial}ms")
        val five = measureTimeMillis {
            repeat(5) {
                kakaoApiPort.getAllCouponRanking(0, 20)
            }
        }
        println("5: ${five}ms")
        val fivezero = measureTimeMillis {
            repeat(50) {
                kakaoApiPort.getAllCouponRanking(0, 20)
            }
        }
        println("50: ${fivezero}ms")
    }
}
