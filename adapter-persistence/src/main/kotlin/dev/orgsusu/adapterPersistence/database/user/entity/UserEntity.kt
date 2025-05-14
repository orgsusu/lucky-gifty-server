package dev.orgsusu.adapterPersistence.database.user.entity

import dev.orgsusu.adapterPersistence.database.common.entity.BaseTimeEntity
import jakarta.persistence.*
import dev.orgsusu.domain.model.user.UserStatus
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "tb_user")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    var credential: String,

    @Column
    var password: String,

    @Column(unique = true)
    var phoneNum: String,

    @Column(unique = true)
    var mail: String,

    @Column(nullable = true)
    var birthDate: LocalDate? = null,

    @Enumerated(EnumType.STRING)
    @Column
    var status: UserStatus = UserStatus.ACTIVE,

    @Column(nullable = true)
    var deletedAt: LocalDateTime? = null,
) : BaseTimeEntity()