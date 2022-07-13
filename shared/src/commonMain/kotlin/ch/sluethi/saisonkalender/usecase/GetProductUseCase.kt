package ch.sluethi.saisonkalender.usecase

import ch.sluethi.saisonkalender.helper.CommonFlow
import ch.sluethi.saisonkalender.helper.asCommonFlow
import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.network.Result
import ch.sluethi.saisonkalender.persistence.Persistence
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.flow

class GetProductUseCase(private val cache: Persistence) {

    fun getProduct(id: String): CommonFlow<Result<Product>> = flow {
        writeLog("getProduct() invoked")
        emit(Result.loading())

        val result = cache.getProduct(id)

        if (result == null) {
            emit(Result.error("product not found"))
        } else {
            emit(Result.result(result))
        }
    }.asCommonFlow()

    private fun writeLog(message: String) {
        Logger.v("GetProductUseCase - $message")
    }
}