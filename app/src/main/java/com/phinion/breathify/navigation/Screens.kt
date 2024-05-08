package com.phinion.breathify.navigation

sealed class Screens(val route: String){
    data object Splash: Screens(route = Routes.SPLASH_SCREEN)
    data object OnBoarding: Screens(route = Routes.ONBOARDING_SCREEN)
    data object Welcome: Screens(route = Routes.WELCOME_SCREEN)
    data object Home: Screens(route = Routes.HOME_SCREEN)
    data object Profile: Screens(route = Routes.PROFILE_SCREEN)
}