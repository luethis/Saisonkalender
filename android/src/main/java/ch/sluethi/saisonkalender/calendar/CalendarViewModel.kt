package ch.sluethi.saisonkalender.calendar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ch.sluethi.saisonkalender.Interactor
import ch.sluethi.saisonkalender.model.Product
import org.koin.java.KoinJavaComponent.inject

class CalendarViewModel : ViewModel() {

    private val repository: Interactor by inject(Interactor::class.java)

    val data: MutableList<Product> = mutableListOf()
    val loading = mutableStateOf(false)

    fun fetchData() {
        repository.getProducts().collectCommon {
            it.data?.let { products ->
                data.clear()
                data.addAll(products)
            }
            loading.value = it.loading
        }
    }
}