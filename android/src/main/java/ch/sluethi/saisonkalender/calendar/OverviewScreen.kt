package ch.sluethi.saisonkalender.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ch.sluethi.saisonkalender.components.LoadingIndicator
import ch.sluethi.saisonkalender.SaisonViewModel
import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.navigation.CalendarNavItem

@Composable
fun OverviewScreen(viewModel: SaisonViewModel, navHostController: NavHostController) {
    val data = viewModel.data.value
    val loading = viewModel.loading.value

    Column {
        Button(onClick = { viewModel.fetchData() }) {
            Text(text = "fetch that data")
        }
        if (viewModel.data.value.isNotEmpty()) {
            OverviewList(list = data) {
                navHostController.navigate(CalendarNavItem.Detail.assembleCall(it))
            }
        }
    }
    LoadingIndicator(loading)
}

@Composable
fun OverviewList(list: List<Product>, onClick: (String) -> Unit) {
    LazyColumn {
        items(items = list) { item ->
            OverviewItem(product = item, onClick)
        }
    }
}

@Composable
fun OverviewItem(product: Product, onClick: (String) -> Unit) {
    Column(modifier = Modifier.clickable { onClick(product.name) }) {
        Text(text = product.name)
        Text(text = product.description)
    }
}