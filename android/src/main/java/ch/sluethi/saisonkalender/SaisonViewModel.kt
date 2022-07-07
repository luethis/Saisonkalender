package ch.sluethi.saisonkalender

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ch.sluethi.saisonkalender.network.Firestore
import ch.sluethi.saisonkalender.model.Product

class SaisonViewModel : ViewModel() {

    val data: MutableState<List<Product>> = mutableStateOf(emptyList())
    val loading = mutableStateOf(false)

    fun fetchData() {
        Interactor(Firestore()).getProducts().collectCommon {
            it.products?.let { products ->
                data.value = products
            }
            loading.value = it.loading
        }
    }
}