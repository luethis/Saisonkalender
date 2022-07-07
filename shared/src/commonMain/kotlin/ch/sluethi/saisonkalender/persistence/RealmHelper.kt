package ch.sluethi.saisonkalender.persistence

import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.model.RealmProduct
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList

fun convertToRealm(product: Product): RealmProduct {
    return RealmProduct().apply {
        name = product.name.values.toRealmList()
        description = product.description.values.toRealmList()
        season = product.season
        url = product.url
    }
}

fun convertFromRealm(realmProduct: RealmProduct): Product {
    return Product(
        name = convertRealmListToMap(realmProduct.name),
        description = convertRealmListToMap(realmProduct.description),
        season = realmProduct.season,
        url = realmProduct.url
    )
}

fun convertRealmListToMap(list: RealmList<String>): Map<String, String> {
    return mapOf(
        "de" to list[0],
        "en" to list[1]
    )
}