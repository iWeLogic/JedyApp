package com.iwelogic.jedyapp.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iwelogic.jedyapp.models.Movie
import com.iwelogic.jedyapp.theme.LocalDimens

@Composable
fun MovieItem(item: Movie, modifier: Modifier = Modifier, onClickItem: (Movie) -> Unit) {
    CardHolder(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClickItem(item)
                },
        ) {
            RemoteImage(
                url = item.poster!!, modifier = Modifier
                    .size(100.dp, 140.dp)
                    .clip(MaterialTheme.shapes.large)
            )

            Text(
                item.title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = LocalDimens.current.large),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
