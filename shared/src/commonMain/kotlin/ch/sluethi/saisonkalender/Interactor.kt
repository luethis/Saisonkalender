package ch.sluethi.saisonkalender

import ch.sluethi.saisonkalender.helper.CommonFlow
import ch.sluethi.saisonkalender.helper.asCommonFlow
import ch.sluethi.saisonkalender.model.QueryState
import ch.sluethi.saisonkalender.network.FirestoreRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class Interactor(val repository: FirestoreRepository) {

    fun getProducts(): CommonFlow<QueryState> = flow {
        emit(QueryState.loading())
        delay(1000)
        emit(QueryState.result(FirestoreRepository().fetchData()))
    }.asCommonFlow()
}