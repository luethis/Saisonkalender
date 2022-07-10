package ch.sluethi.saisonkalender.usecase

import ch.sluethi.saisonkalender.helper.CommonFlow
import ch.sluethi.saisonkalender.helper.asCommonFlow
import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.network.Firestore
import ch.sluethi.saisonkalender.network.Result
import ch.sluethi.saisonkalender.persistence.AppSettings
import ch.sluethi.saisonkalender.persistence.Persistence
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.flow

class GetCalendarDataUseCase(
    private val firestore: Firestore,
    private val settings: AppSettings,
    private val cache: Persistence
) {

    fun getProducts(month: Int): CommonFlow<Result<List<Product>>> = flow {
        emit(Result.loading())

        if (cache.countProducts() > 0) {
            Logger.v("read from cache")
            emit(Result.result(cache.getProducts()))

            val remoteVersion = firestore.fetchVersion()
            val localVersion = settings.versionCode

            if (remoteVersion > localVersion) {
                Logger.v("update products")
                val products = filterProductsForMonth(firestore.fetchData(), month)
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

    private fun filterProductsForMonth(list: List<Product>, month: Int) = list.filter { it.season[month] }
}