package ch.sluethi.saisonkalender.calendar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.usecase.GetProductUseCase
import org.koin.java.KoinJavaComponent.inject

class ProductViewModel : ViewModel() {

    private val repository: GetProductUseCase by inject(GetProductUseCase::class.java)

    val data: MutableState<Product?> = mutableStateOf(null)
    val loading = mutableStateOf(false)

    fun loadProduct(id: String) {
        repository.getProduct(id).collectCommon {
            it.data?.let { product ->
                data.value = product
            }
            loading.value = it.loading
        }
    }
}