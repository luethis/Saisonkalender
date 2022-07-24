package ch.sluethi.saisonkalender.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ch.sluethi.saisonkalender.R
import ch.sluethi.saisonkalender.components.GridHeader
import ch.sluethi.saisonkalender.components.LoadingIndicator
import ch.sluethi.saisonkalender.components.ProductCard
import ch.sluethi.saisonkalender.navigation.CalendarNavigationItem

@Composable
fun CalendarScreen(navHostController: NavHostController, viewModel: CalendarViewModel) {
    Column {
        TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
        ProductGrid(viewModel = viewModel) {
            navHostController.navigate(
                CalendarNavigationItem.Detail.assembleCall(it)
            )
        }
    }
    LoadingIndicator(viewModel.loading.value)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductGrid(viewModel: CalendarViewModel, onClick: (String) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(all = 8.dp),
        content = {
            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                GridHeader(
                    text = viewModel.currentMonth,
                    onPrevious = { viewModel.previousMonth() },
                    onNext = { viewModel.nextMonth() })
            }
            items(items = viewModel.data) { item ->
                ProductCard(product = item, onClick)
            }
        })
}