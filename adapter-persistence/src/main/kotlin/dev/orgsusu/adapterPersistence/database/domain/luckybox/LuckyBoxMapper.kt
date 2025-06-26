package dev.orgsusu.adapterPersistence.database.domain.luckybox

import dev.orgsusu.adapterPersistence.database.domain.luckybox.entity.LuckyBoxEntity
import dev.orgsusu.adapterPersistence.database.domain.luckybox.entity.LuckyBoxProductEntity
import dev.orgsusu.domain.luckybox.model.LuckyBoxProductDomain
import dev.orgsusu.domain.luckybox.model.LuckyBoxSimpleDomain
import io.mcarle.konvert.api.Konvert
import io.mcarle.konvert.api.Konverter
import io.mcarle.konvert.api.Mapping
import io.mcarle.konvert.injector.spring.KComponent

@Konverter
@KComponent
interface LuckyBoxMapper {
    @Konvert(
        mappings = [
            Mapping(target = "luckyBoxId", expression = "it.id.luckyBoxId"),
            Mapping(target = "id", expression = "it.id.productId"),
        ]
    )
    fun entityToDomain(product: LuckyBoxProductEntity): LuckyBoxProductDomain
    @Konvert(
        mappings = [
            Mapping(target = "id", source = "luckyBoxId"),
            Mapping(target = "resultExists", expression = "it.pickedId != null"),
        ]
    )
    fun entityToDomain(product: LuckyBoxEntity): LuckyBoxSimpleDomain
}
