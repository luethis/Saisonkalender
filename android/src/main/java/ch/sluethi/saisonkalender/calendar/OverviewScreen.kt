package ch.sluethi.saisonkalender.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ch.sluethi.saisonkalender.SaisonViewModel
import ch.sluethi.saisonkalender.components.LoadingIndicator
import ch.sluethi.saisonkalender.components.ProductCard
import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.navigation.CalendarNavItem

@Composable
fun OverviewScreen(viewModel: SaisonViewModel, navHostController: NavHostController) {
    val data = viewModel.data.value
    val loading = viewModel.loading.value

    LaunchedEffect(key1 = true, block = {
        viewModel.fetchData()
    })

    Column {
        if (viewModel.data.value.isNotEmpty()) {
            OverviewList(list = data) {
                navHostController.navigate(CalendarNavItem.Detail.assembleCall(it))
            }
        }
    }
    LoadingIndicator(loading)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OverviewList(list: List<Product>, onClick: (String) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(all = 8.dp),
        content = {
            items(items = list) { item ->
                ProductCard(product = item, onClick)
            }
        })
}