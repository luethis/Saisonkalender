package ch.sluethi.saisonkalender.persistence

import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.model.RealmProduct

fun convertToRealm(product: Product): RealmProduct {
    return RealmProduct().apply {
        id = product.id
        name = product.name
        description = product.description
        season = product.season
        url = product.url
    }
}

fun convertFromRealm(realmProduct: RealmProduct): Product {
    return Product(
        id = realmProduct.id,
        name = realmProduct.name,
        description = realmProduct.description,
        season = realmProduct.season,
        url = realmProduct.url
    )
}