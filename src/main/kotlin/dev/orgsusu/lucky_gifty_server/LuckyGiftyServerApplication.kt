package dev.orgsusu.lucky_gifty_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication(scanBasePackages = ["dev.orgsusu"])
@EnableJpaRepositories(basePackages = ["dev.orgsusu.adapterPersistence.database.user.repository"])
@EntityScan(basePackages = ["dev.orgsusu.adapterPersistence.database.user.entity", "dev.orgsusu.domain"])
class LuckyGiftyServerApplication

fun main(args: Array<String>) {
    runApplication<LuckyGiftyServerApplication>(*args)
}
