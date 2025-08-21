package com.iwelogic.jedyapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iwelogic.jedyapp.R

@Composable
fun ErrorPage(modifier: Modifier = Modifier, onClickReload: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, alignment = Alignment.CenterVertically),
        modifier = modifier
    ) {
        Image(painter = painterResource(R.drawable.error_loading), contentDescription = "")

        Text(
            text = "Ups something went wrong",
            style = MaterialTheme.typography.headlineSmall
        )
        Button(
            onClick = onClickReload,
            modifier = Modifier.size(height = 50.dp, width = 200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
        ) {
            Text(
                text = "Reload",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }

}