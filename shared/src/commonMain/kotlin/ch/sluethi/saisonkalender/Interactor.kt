package ch.sluethi.saisonkalender

import ch.sluethi.saisonkalender.helper.CommonFlow
import ch.sluethi.saisonkalender.helper.asCommonFlow
import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.network.Firestore
import ch.sluethi.saisonkalender.network.Result
import ch.sluethi.saisonkalender.persistence.AppSettings
import ch.sluethi.saisonkalender.persistence.Persistence
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.flow

class Interactor(
    private val firestore: Firestore,
    private val settings: AppSettings,
    private val cache: Persistence
) {

    fun getProducts(): CommonFlow<Result<List<Product>>> = flow {
        emit(Result.loading())

        if (cache.countProducts() > 0) {
            Logger.v("read from cache")
            emit(Result.result(cache.getProducts()))

            val remoteVersion = firestore.fetchVersion()
            val localVersion = settings.versionCode

            if (remoteVersion > localVersion) {
                Logger.v("update products")
                val products = firestore.fetchData()
                emit(Result.result(products))
                cache.deleteProducts()
                cache.insertProducts(products)
                settings.versionCode = remoteVersion
                Logger.v("products updated")
            }
        } else {
            Logger.v("first app usage")
            val products = firestore.fetchData()
            emit(Result.result(products))
            cache.insertProducts(products)
        }
    }.asCommonFlow()

    fun getProduct(id: String): CommonFlow<Result<Product>> = flow {
        emit(Result.loading())

        val result = cache.getProduct(id)

        if (result == null) {
            emit(Result.error("product not found"))
        } else {
            emit(Result.result(result))
        }
    }.asCommonFlow()
}