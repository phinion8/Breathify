package com.phinion.breathify.ui.screens.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phinion.breathify.R
import com.phinion.breathify.ui.components.CustomElevatedButton
import com.phinion.breathify.ui.components.ShowLottieAnimation
import com.phinion.breathify.ui.theme.lightGrey

@Composable
fun WelcomeScreen(
    onFinishedClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        ShowLottieAnimation(rawRes = R.raw.on_boarding_anim, modifier = Modifier.size(300.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.calm_your_mind_with_breathing_exercises),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.discover_peace_and_tranquility_with_breathing_exercises_that_soothe_your_mind_and_body),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = lightGrey,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(42.dp))

            CustomElevatedButton(onClick = {
                onFinishedClick()
            }, text = "Get Started")
        }

    }
}