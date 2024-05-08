package com.phinion.breathify.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phinion.breathify.ui.theme.blue
import com.phinion.breathify.ui.theme.white

@Composable
fun CustomElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String
) {

    ElevatedButton(
        onClick = {
            onClick()
        }, shape = CircleShape,
        modifier = modifier
            .fillMaxWidth(0.9f),
        colors = ButtonDefaults.buttonColors(containerColor = blue, contentColor = white)
    ) {

        Text(
            modifier = Modifier.padding(
                vertical = 4.dp
            ), text = text, style = MaterialTheme.typography.bodyLarge
        )

    }

}