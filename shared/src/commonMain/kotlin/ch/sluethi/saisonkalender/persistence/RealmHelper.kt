package ch.sluethi.saisonkalender.persistence

import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.model.RealmProduct
import io.realm.kotlin.ext.toRealmList

fun convertToRealm(product: Product): RealmProduct {
    return RealmProduct().apply {
        id = product.id
        name = product.name
        description = product.description
        season = product.season.toRealmList()
        url = product.url
    }
}

fun convertFromRealm(realmProduct: RealmProduct): Product {
    return Product(
        id = realmProduct.id,
        name = realmProduct.name,
        description = realmProduct.description,
        season = realmProduct.season.toRealmList(),
        url = realmProduct.url
    )
}