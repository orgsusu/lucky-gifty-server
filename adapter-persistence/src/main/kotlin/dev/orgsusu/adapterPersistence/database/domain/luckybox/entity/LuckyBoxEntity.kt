package dev.orgsusu.adapterPersistence.database.domain.luckybox.entity

import dev.orgsusu.adapterPersistence.database.domain.user.entity.UserEntity
import dev.orgsusu.adapterPersistence.database.global.entity.BaseTimeEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "tb_lucky_box")
class LuckyBoxEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column("lucky_box_id")
    val _luckyBoxId: UUID? = null,

    @Column("lucky_box_name", nullable = false)
    val name: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn("created_by", referencedColumnName = "id")
    val createdBy: UserEntity,

    @Column("picked_id")
    val pickedId: Long? = null,

    @OneToMany(mappedBy = "luckyBox", cascade = [CascadeType.ALL], orphanRemoval = true)
    val products: List<LuckyBoxProductEntity> = emptyList()
) : BaseTimeEntity() {
    val luckyBoxId get() = _luckyBoxId!!
}
