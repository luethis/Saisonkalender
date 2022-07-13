package ch.sluethi.saisonkalender.usecase

import ch.sluethi.saisonkalender.helper.CommonFlow
import ch.sluethi.saisonkalender.helper.asCommonFlow
import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.network.Result
import ch.sluethi.saisonkalender.persistence.Persistence
import ch.sluethi.saisonkalender.usecase.UpdateCalendarUseCase.UpdateResult
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.flow

class GetCalendarDataUseCase(
    private val cache: Persistence,
    private val updater: UpdateCalendarUseCase
) {

    fun getProducts(month: Int, checkUpdate : Boolean): CommonFlow<Result<List<Product>>> = flow {
        writeLog("getProducts() invoked")
        emit(Result.loading())
        if (cache.countProducts() > 0) {
            writeLog("there are items in the cache")
            emit(Result.result(filterProductsForMonth(cache.getProducts(), month)))
            if(checkUpdate){
                writeLog("update products if needed")
                when (updater.updateProducts()) {
                    UpdateResult.UPDATED -> emit(
                        Result.result(
                            filterProductsForMonth(
                                cache.getProducts(),
                                month
                            )
                        )
                    )
                    UpdateResult.NOT_NEEDED, UpdateResult.UPDATE_FAILED -> Unit
                }
            }
        } else {
            writeLog("first app sage")
            when (updater.updateProducts()) {
                UpdateResult.UPDATED, UpdateResult.NOT_NEEDED -> {
                    emit(Result.result(filterProductsForMonth(cache.getProducts(), month)))
                }
                UpdateResult.UPDATE_FAILED -> emit(Result.error("could not load data"))
            }
        }
    }.asCommonFlow()

    private fun filterProductsForMonth(list: List<Product>, month: Int) =
        list.filter { it.season[month] }

    private fun writeLog(message: String) {
        Logger.v("GetCalendarUseCase - $message")
    }
}