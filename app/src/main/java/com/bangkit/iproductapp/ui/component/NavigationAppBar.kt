package com.bangkit.iproductapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.iproductapp.ui.theme.IProductAppTheme

@Composable
fun NavigationAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        IconButton(
            onClick = onBackPressed,
            content = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                )
            },
        )
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .weight(1F)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationAppBarPreview() {
    IProductAppTheme {
        NavigationAppBar(
            title = "Detail Product",
            onBackPressed = {},
        )
    }
}