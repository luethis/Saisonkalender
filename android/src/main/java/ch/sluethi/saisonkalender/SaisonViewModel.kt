package ch.sluethi.saisonkalender

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ch.sluethi.saisonkalender.model.Product
import org.koin.java.KoinJavaComponent.inject

class SaisonViewModel() : ViewModel() {

    val data: MutableState<List<Product>> = mutableStateOf(emptyList())
    val loading = mutableStateOf(false)

    val repository : Interactor by inject(Interactor::class.java)

    fun fetchData() {
        repository.getProducts().collectCommon {
            it.products?.let { products ->
                data.value = products
            }
            loading.value = it.loading
        }
    }
}