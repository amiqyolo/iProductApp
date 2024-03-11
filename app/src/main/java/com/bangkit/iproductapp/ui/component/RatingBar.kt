package com.bangkit.iproductapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit.iproductapp.R
import com.bangkit.iproductapp.ui.theme.IProductAppTheme
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    stars: Int = 5,
    starsColor: Color = Color(0xFFFF6F00),
) {
    val filledStars = floor(rating).toInt()
    val unFilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_24),
                contentDescription = null,
                tint = starsColor
            )
        }

        if (halfStar) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_half_24),
                contentDescription = null,
                tint = starsColor
            )
        }

        repeat(unFilledStars) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_outline_24),
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingBarPreview() {
    IProductAppTheme {
        RatingBar(rating = 4.9)
    }
}