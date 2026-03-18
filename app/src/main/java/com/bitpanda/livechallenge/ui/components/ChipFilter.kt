package com.bitpanda.livechallenge.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bitpanda.livechallenge.R
import com.bitpanda.livechallenge.ui.theme.PrimaryColor
import com.bitpanda.livechallenge.ui.theme.SelectedAccent

@Composable
fun StatefulFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    areTopCoins: Boolean
) {
    StatelessFilterChip(
        selected = selected,
        onClick = onClick,
        text = if (areTopCoins) stringResource(R.string.best_coins) else stringResource(R.string.worst_coins),
        leadingIconResId = if (areTopCoins) R.drawable.arrow_up else R.drawable.arrow_down
    )
}

@Composable
private fun StatelessFilterChip(
    selected: Boolean,
    onClick: () -> Unit = {},
    text: String = "Example",
    leadingIconResId: Int,
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = text,
                color = PrimaryColor
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(leadingIconResId),
                contentDescription = null,
                tint = PrimaryColor
            )
        },
        colors = FilterChipDefaults.filterChipColors().copy(
            selectedContainerColor = Color.White,
            containerColor = Color.White
        ),
        border = if (selected) BorderStroke(1.dp, SelectedAccent) else null
    )
}

@Preview
@Composable
private fun PreviewFilterChipSelected() {
    StatelessFilterChip(selected = true, leadingIconResId = R.drawable.arrow_up)
}

@Preview
@Composable
private fun PreviewFilterChipNotSelected() {
    StatelessFilterChip(selected = false, leadingIconResId = R.drawable.arrow_down)
}