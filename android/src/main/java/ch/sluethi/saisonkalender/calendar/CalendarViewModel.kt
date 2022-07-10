package ch.sluethi.saisonkalender.calendar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.platform.getCurrentMonth
import ch.sluethi.saisonkalender.platform.getCurrentMonthAsText
import ch.sluethi.saisonkalender.platform.getMonthAsText
import ch.sluethi.saisonkalender.usecase.GetCalendarDataUseCase
import org.koin.java.KoinJavaComponent.inject

class CalendarViewModel : ViewModel() {

    private val repository: GetCalendarDataUseCase by inject(GetCalendarDataUseCase::class.java)

    val data: MutableList<Product> = mutableListOf()
    val loading = mutableStateOf(false)

    val currentMonth: MutableState<String> = mutableStateOf(getCurrentMonthAsText())
    private val currentMonthIndex: MutableState<Int> = mutableStateOf(getCurrentMonth())

    fun fetchCalendar() {
        repository.getProducts(currentMonthIndex.value).collectCommon {
            it.data?.let { products ->
                data.clear()
                data.addAll(products)
            }
            loading.value = it.loading
        }
    }

    fun nextMonth() {
        currentMonthIndex.value++
        updateCalendar()
    }

    fun previousMonth() {
        currentMonthIndex.value--
        updateCalendar()
    }

    private fun updateCalendar() {
        currentMonth.value = getMonthAsText(currentMonthIndex.value)
        fetchCalendar()
    }
}