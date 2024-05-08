package com.phinion.breathify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.phinion.breathify.ui.screens.home.HomeScreen
import com.phinion.breathify.ui.screens.onboarding.OnBoardingScreen
import com.phinion.breathify.ui.screens.onboarding.components.WelcomeScreen
import com.phinion.breathify.ui.screens.profile.ProfileScreen
import com.phinion.breathify.ui.screens.splash.SplashScreen

@Composable
fun SetUpNavigation(
    navController: NavHostController,
    showBottomBar: (Boolean) -> Unit
) {

    NavHost(navController = navController, startDestination = Screens.Splash.route) {

        composable(route = Screens.Splash.route) {
            showBottomBar(false)
            SplashScreen(
                onSplashComplete = {onBoardingComplete->
                    if (onBoardingComplete)
                        navController.navigate(Screens.Home.route)
                    else
                        navController.navigate(Screens.Welcome.route)
                }
            )
        }

        composable(route = Screens.Welcome.route){
            WelcomeScreen(
                onFinishedClick = {
                    navController.navigate(Screens.OnBoarding.route)
                }
            )
        }

        composable(route = Screens.OnBoarding.route) {
            showBottomBar(false)
            OnBoardingScreen()

        }

        composable(route = Screens.Home.route) {
            showBottomBar(true)
            HomeScreen()
        }

        composable(route = Screens.Profile.route) {
            ProfileScreen()
        }

    }

}