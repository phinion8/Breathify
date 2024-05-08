package com.phinion.breathify.ui.components.models

import androidx.annotation.DrawableRes
import com.phinion.breathify.R
import com.phinion.breathify.navigation.Screens

data class BottomNavItem(
    @DrawableRes
    val icon: Int,
    val title: String,
    val route: String
)

val bottomNavItems = listOf<BottomNavItem>(
    BottomNavItem(
        icon = R.drawable.ic_home,
        title = "Home",
        route = Screens.Home.route
    ),
    BottomNavItem(
        icon = R.drawable.ic_discover,
        title = "Discover",
        route = ""
    ),
    BottomNavItem(
        icon = R.drawable.ic_profile,
        title = "Account",
        route = Screens.Profile.route
    )
)