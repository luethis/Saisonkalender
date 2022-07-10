package ch.sluethi.saisonkalender.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.sluethi.saisonkalender.R
import ch.sluethi.saisonkalender.ui.theme.SaisonkalenderTheme

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GridHeaderPreview() {
    SaisonkalenderTheme {
        Surface {
            GridHeader(
                text = "November",
                onPrevious = { },
                onNext = {}
            )
        }
    }
}

@Composable
fun GridHeader(text: String, onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterVertically),
            horizontalArrangement = Arrangement.End,
        ) {
            IconButton(onClick = onPrevious) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.previous_month)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(onClick = onNext) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = stringResource(R.string.next_month)
                )
            }
        }
    }

}