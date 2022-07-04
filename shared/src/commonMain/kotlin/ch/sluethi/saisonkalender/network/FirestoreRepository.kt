package ch.sluethi.saisonkalender.network

import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.mock.mockData
import kotlinx.coroutines.delay

class FirestoreRepository {
    suspend fun fetchData(): List<Product> {
        delay(1000)
        return mockData
    }
}