package ch.sluethi.saisonkalender.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.sluethi.saisonkalender.R
import ch.sluethi.saisonkalender.model.Product
import ch.sluethi.saisonkalender.network.mockData
import ch.sluethi.saisonkalender.ui.theme.SaisonkalenderTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProductCardPreview() {
    SaisonkalenderTheme {
        Surface {
            ProductCard(product = mockData[0], onClick = {})
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick(product.id) },
        elevation = 4.dp,
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
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
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = product.name,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
        }
    }
}