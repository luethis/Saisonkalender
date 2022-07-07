package ch.sluethi.saisonkalender

import ch.sluethi.saisonkalender.network.Firestore
import ch.sluethi.saisonkalender.helper.CommonFlow
import ch.sluethi.saisonkalender.helper.asCommonFlow
import ch.sluethi.saisonkalender.network.Result
import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.persistence.AppSettings
import ch.sluethi.saisonkalender.persistence.Persistence
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.flow

class Interactor(val repository: Firestore) {

    fun getProducts(): CommonFlow<Result<List<Product>>> = flow {
        emit(Result.loading())

        val cache = Persistence()

        if (cache.countProducts() > 0) {
            Logger.v("read from cache")
            emit(Result.result(cache.getProducts()))

            val remoteVersion = Firestore().fetchVersion()
            val localVersion = AppSettings.versionCode

            if (remoteVersion > localVersion) {
                Logger.v("update products")
                val products = Firestore().fetchData()
                emit(Result.result(products))
                cache.deleteProducts()
                cache.insertProducts(products)
                AppSettings.versionCode = remoteVersion
                Logger.v("products updated")
            }
        } else {
            Logger.v("first app usage")
            val products = Firestore().fetchData()
            emit(Result.result(products))
            cache.insertProducts(products)
        }
    }.asCommonFlow()
}