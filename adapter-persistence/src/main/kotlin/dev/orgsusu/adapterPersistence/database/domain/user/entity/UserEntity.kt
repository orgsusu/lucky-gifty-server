package dev.orgsusu.adapterPersistence.database.domain.user.entity

import dev.orgsusu.adapterPersistence.database.global.entity.BaseTimeEntity
import dev.orgsusu.domain.user.model.UserDomain
import jakarta.persistence.*
import dev.orgsusu.domain.user.model.UserStatus
import io.mcarle.konvert.api.KonvertTo
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "tb_user")
@KonvertTo(UserDomain::class)
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    var credential: String,

    @Column
    var password: String,

    @Column(unique = true, nullable = true)
    var phone: String?,

    @Column(unique = true, nullable = true)
    var email: String?,

    @Column(nullable = true)
    var birthDay: LocalDate? = null,

    @Enumerated(EnumType.STRING)
    @Column
    var status: UserStatus = UserStatus.ACTIVE,

    @Column(nullable = true)
    var deletedAt: LocalDateTime? = null,
) : BaseTimeEntity()
