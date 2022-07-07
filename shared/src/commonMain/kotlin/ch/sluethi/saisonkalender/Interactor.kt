package ch.sluethi.saisonkalender

import ch.sluethi.saisonkalender.firestore.Firestore
import ch.sluethi.saisonkalender.helper.CommonFlow
import ch.sluethi.saisonkalender.helper.asCommonFlow
import ch.sluethi.saisonkalender.model.QueryState
import ch.sluethi.saisonkalender.persistence.AppSettings
import ch.sluethi.saisonkalender.persistence.Persistence
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.flow

class Interactor(val repository: Firestore) {

    fun getProducts(): CommonFlow<QueryState> = flow {
        emit(QueryState.loading())

        val cache = Persistence()

        if (cache.countProducts() > 0) {
            Logger.v("read from cache")
            emit(QueryState.result(cache.getProducts()))

            val remoteVersion = Firestore().fetchVersion()
            val localVersion = AppSettings.versionCode

            if (remoteVersion > localVersion) {
                Logger.v("update products")
                val products = Firestore().fetchData()
                emit(QueryState.result(products))
                cache.deleteProducts()
                cache.insertProducts(products)
                AppSettings.versionCode = remoteVersion
                Logger.v("products updated")
            }
        } else {
            Logger.v("first app usage")
            val products = Firestore().fetchData()
            emit(QueryState.result(products))
            cache.insertProducts(products)
        }
    }.asCommonFlow()
}