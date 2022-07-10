package ch.sluethi.saisonkalender.persistence

import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.model.RealmProduct
import co.touchlab.kermit.Logger
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query

class Persistence {

    private val realmConfiguration =
        RealmConfiguration.Builder(schema = setOf(RealmProduct::class))
            .build()

    fun insertProducts(products: List<Product>) {
        Logger.v { "insert products in cache" }
        val realm: Realm = Realm.open(realmConfiguration)
        for (product in products.map { convertToRealm(it) }) {
            realm.writeBlocking { copyToRealm(product) }
        }
        realm.close()
    }

    fun getProduct(id: String): Product? {
        Logger.v { "get a specific product from the cache" }
        val realm: Realm = Realm.open(realmConfiguration)
        val result = realm.query<RealmProduct>("id == $0", id).first().find()
        return result?.run { convertFromRealm(this) }
    }

    fun getProducts(): List<Product> {
        Logger.v { "get all products from cache" }
        val realm: Realm = Realm.open(realmConfiguration)
        val products = realm.query<RealmProduct>().find().map { convertFromRealm(it) }
        realm.close()
        return products
    }

    fun deleteProducts() {
        Logger.v { "delete all products from cache" }
        val realm: Realm = Realm.open(realmConfiguration)
        realm.writeBlocking {
            val results = this.query<RealmProduct>().find()
            delete(results)
        }
        realm.close()
    }

    fun countProducts(): Int {
        Logger.v { "count all products in cache" }
        val realm: Realm = Realm.open(realmConfiguration)
        val size = realm.query<RealmProduct>().find().size
        realm.close()
        return size
    }
}