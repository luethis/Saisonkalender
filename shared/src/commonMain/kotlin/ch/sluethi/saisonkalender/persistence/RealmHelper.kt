package ch.sluethi.saisonkalender.persistence

import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.model.RealmProduct
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList

fun convertToRealm(product: Product): RealmProduct {
    return RealmProduct().apply {
        name = product.name
        description = product.description
        season = product.season
        url = product.url
    }
}

fun convertFromRealm(realmProduct: RealmProduct): Product {
    return Product(
        name = realmProduct.name,
        description = realmProduct.description,
        season = realmProduct.season,
        url = realmProduct.url
    )
}