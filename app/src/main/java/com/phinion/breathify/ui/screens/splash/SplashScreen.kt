package com.phinion.breathify.ui.screens.splash

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phinion.breathify.R
import com.phinion.breathify.ui.screens.splash.viewmodel.SplashViewModel
import com.phinion.breathify.ui.theme.SPLASH_ICON_SIZE
import com.phinion.breathify.utils.Constants
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    onSplashComplete: (moveToOnBoardingScreen: Boolean) -> Unit
) {
    val onBoardingComplete by splashViewModel.onBoardingState.collectAsState()
    val isLoading by splashViewModel.loadingState.collectAsState()
    val isError by splashViewModel.errorState.collectAsState()

    if (isError){
        Toast.makeText(LocalContext.current, splashViewModel.error.value, Toast.LENGTH_LONG).show()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LaunchedEffect(key1 = Unit) {
            delay(Constants.SPLASH_DELAY)
            if (!isLoading && !isError)
            onSplashComplete(onBoardingComplete)
        }

        Image(
            modifier = Modifier.size(SPLASH_ICON_SIZE),
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = "App Icon"
        )


    }

}