package ch.sluethi.saisonkalender

import ch.sluethi.saisonkalender.di.appModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KoinHelper : KoinComponent {
    private val repository: Interactor by inject()
    suspend fun loadProducts() = repository.getProducts()
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}