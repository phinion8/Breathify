package com.phinion.breathify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.phinion.breathify.navigation.SetUpNavigation
import com.phinion.breathify.ui.components.BottomNavBar
import com.phinion.breathify.ui.components.models.bottomNavItems
import com.phinion.breathify.ui.theme.BreathingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreathingTheme {

                val navController = rememberNavController()
                var bottomBarVisibility by remember {
                    mutableStateOf(false)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold(
                        content = { paddingValues ->


                            SetUpNavigation(navController = navController, showBottomBar = {
                                bottomBarVisibility = it
                            })


                        },
                        bottomBar = {
                            if (bottomBarVisibility)
                                BottomNavBar(
                                    modifier = Modifier.fillMaxWidth(),
                                    bottomNavItems = bottomNavItems,
                                    navController = navController,
                                    onItemClick = {
                                        navController.navigate(it.route) {
                                            navController.graph.startDestinationRoute?.let { screen_route ->

                                            }
                                            this.launchSingleTop = true
                                            this.restoreState = true
                                        }
                                    }
                                )
                        }
                    )


                }
            }
        }
    }
}


