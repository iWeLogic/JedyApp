package com.iwelogic.jedyapp.ui.movies

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import com.iwelogic.jedyapp.models.Movie
import com.iwelogic.jedyapp.ui.views.CardHolder

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
            Text(item.title)
//            RemoteImage(
//                url = item.icon, modifier = Modifier
//                    .size(size, size)
//                    .clip(RoundedCornerShape(CornerSize(16.dp)))
//            )
        }
    }

}
