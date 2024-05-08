package com.phinion.breathify.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.phinion.breathify.R
import com.phinion.breathify.ui.theme.primaryColor

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = "App icon"
            )
            if (!isSystemInDarkTheme())
                Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Breathify",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        Icon(
            modifier = Modifier
                .size(35.dp)
                .padding(end = 8.dp),
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = "Notification Icon",
            tint = primaryColor
        )

    }
}