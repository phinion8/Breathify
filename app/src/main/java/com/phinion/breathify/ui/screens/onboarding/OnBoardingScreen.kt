package com.phinion.breathify.ui.screens.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phinion.breathify.ui.screens.onboarding.components.OnBoardingBreakTime
import com.phinion.breathify.ui.screens.onboarding.components.OnBoardingGoal
import com.phinion.breathify.ui.screens.onboarding.components.OnBoardingMusic
import com.phinion.breathify.ui.screens.onboarding.components.OnBoardingName
import com.phinion.breathify.ui.screens.onboarding.components.OnBoardingPace
import com.phinion.breathify.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.phinion.breathify.ui.theme.blue
import com.phinion.breathify.ui.theme.white
import com.phinion.breathify.utils.AppUtils
import com.phinion.breathify.utils.showToast
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val pagerState = rememberPagerState(
        pageCount = {
            5
        }
    )

    var pageCount by remember {
        mutableIntStateOf(0)
    }

    val coroutineScope = rememberCoroutineScope()

    var progress by remember {
        mutableFloatStateOf(0F)
    }

    val linearProgressAnim by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 500), label = "Linear Progress Animation"
    )

    val focusManager = LocalFocusManager.current

    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        verticalAlignment = Alignment.Top
    ) { index ->

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .padding(top = 48.dp)
                        .align(Alignment.CenterHorizontally),
                    progress = { linearProgressAnim },
                )
                Spacer(modifier = Modifier.height(16.dp))
                when (index) {
                    0 -> {
                        OnBoardingName(
                            onBoardingViewModel
                        )
                    }

                    1 -> {
                        OnBoardingMusic(onBoardingViewModel)
                        focusManager.clearFocus()
                    }

                    2 -> {
                        OnBoardingPace(onBoardingViewModel)
                    }

                    3 -> {
                        OnBoardingBreakTime(onBoardingViewModel)
                    }

                    4 -> {
                        OnBoardingGoal(onBoardingViewModel)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = if (pageCount > 0) 24.dp else 48.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            if (AppUtils.validateName(onBoardingViewModel.userName.value)){
                                pageCount++
                                if (pageCount < pagerState.pageCount)
                                    pagerState.scrollToPage(pageCount)
                                else
                                    onBoardingViewModel.saveOnBoardingState(true)
                                    context.showToast("OnBoarding Completed...")
                                progress += 0.25F
                            }else{
                                context.showToast("Please enter correct name.")
                            }
                        }
                    },
                    containerColor = blue,
                    contentColor = white
                ) {
                    Icon(
                        imageVector = if (pageCount <= 3) Icons.AutoMirrored.Filled.ArrowForward else Icons.Default.Check,
                        contentDescription = "Next",
                        tint = white
                    )
                }
            }
        }
    }


}


