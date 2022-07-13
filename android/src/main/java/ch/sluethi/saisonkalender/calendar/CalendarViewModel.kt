package ch.sluethi.saisonkalender.calendar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ch.sluethi.saisonkalender.helper.getCurrentMonth
import ch.sluethi.saisonkalender.helper.getCurrentMonthText
import ch.sluethi.saisonkalender.helper.getMonthAsText
import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.usecase.GetCalendarDataUseCase
import org.koin.java.KoinJavaComponent.inject

class CalendarViewModel : ViewModel() {

    private val repository: GetCalendarDataUseCase by inject(GetCalendarDataUseCase::class.java)

    val data: MutableList<Product> = mutableListOf()
    val loading = mutableStateOf(false)

    var currentMonth by mutableStateOf(getCurrentMonthText())
        private set

    private var currentMonthIndex by mutableStateOf(getCurrentMonth())

    init {
        fetchCalendar(true)
    }

    private fun fetchCalendar(checkUpdate: Boolean) {
        repository.getProducts(currentMonthIndex, checkUpdate).collectCommon {
            it.data?.let { products ->
                data.clear()
                data.addAll(products)
            }
            loading.value = it.loading
        }
    }

    fun nextMonth() = updateMonth(currentMonthIndex.inc())

    fun previousMonth() = updateMonth(currentMonthIndex.dec())

    private fun updateMonth(newMonth: Int) {
        currentMonthIndex = when {
            newMonth > 11 -> 0
            newMonth < 0 -> 11
            else -> newMonth
        }
        currentMonth = getMonthAsText(currentMonthIndex)
        fetchCalendar(false)
    }
}