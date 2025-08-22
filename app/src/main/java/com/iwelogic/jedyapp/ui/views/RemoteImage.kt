package com.iwelogic.jedyapp.ui.views


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.iwelogic.jedyapp.R

@Composable
fun RemoteImage(url: String?, modifier: Modifier = Modifier) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = url,
        error = {
            Box(modifier = modifier, contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.error_loading), textAlign = TextAlign.Center)
            }
        },
        loading = {
            Box(modifier = modifier, contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
            }
        },
        contentScale = ContentScale.Crop,
        contentDescription = ""
    )
}