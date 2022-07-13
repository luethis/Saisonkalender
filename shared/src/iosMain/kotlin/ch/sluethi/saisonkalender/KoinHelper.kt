package ch.sluethi.saisonkalender

import ch.sluethi.saisonkalender.di.appModule
import ch.sluethi.saisonkalender.usecase.GetCalendarDataUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KoinHelper : KoinComponent {
    private val repository: GetCalendarDataUseCase by inject()
    suspend fun loadProducts() = repository.updateProducts()
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}