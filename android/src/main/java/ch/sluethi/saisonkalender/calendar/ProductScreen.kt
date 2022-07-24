package ch.sluethi.saisonkalender.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ch.sluethi.saisonkalender.R
import ch.sluethi.saisonkalender.components.LoadingIndicator
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    id: String,
    viewModel: ProductViewModel = viewModel()
) {
    val data = viewModel.data.value
    val loading = viewModel.loading.value

    LaunchedEffect(key1 = id) { viewModel.loadProduct(id) }

    data?.let { product ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(data.name) },
                    navigationIcon = {
                        IconButton(onClick = { navHostController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, "Back")
                        }
                    }
                )
            }
        ) {
            Column {
                Card(modifier = Modifier.padding(24.dp), elevation = 8.dp) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(product.url)
                            .crossfade(true)
                            .build(),
                        contentDescription = product.name,
                        modifier = Modifier
                            .fillMaxWidth(),
                        placeholder = painterResource(id = R.drawable.nothing),
                        contentScale = ContentScale.FillWidth
                    )
                }

                Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp)) {
                    Text(product.description, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
    LoadingIndicator(isDisplayed = loading)
}