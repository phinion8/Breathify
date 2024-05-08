package com.phinion.breathify.ui.screens.onboarding.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.phinion.breathify.R
import com.phinion.breathify.data.remote.AppConfig
import com.phinion.breathify.domain.models.Value
import com.phinion.breathify.ui.components.ShowLottieAnimation
import com.phinion.breathify.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.phinion.breathify.ui.theme.black
import com.phinion.breathify.ui.theme.blue
import com.phinion.breathify.ui.theme.grey
import com.phinion.breathify.ui.theme.primaryColor
import com.phinion.breathify.ui.theme.secondaryColor
import com.phinion.breathify.ui.theme.white
import okhttp3.internal.filterList

@Composable
fun OnBoardingGoal(
    viewModel: OnBoardingViewModel
) {
    val goalsData = AppConfig.getAppConfiguration().data.breathingToolData.prc.filterList {
        (this.code == "goal")
    }

    val goalList = goalsData[0].values.toMutableList()

    val selectedGoal by viewModel.selectedGoal.collectAsState()

    val sampleGoal = Value(
        code = "3",
        dsc = "NightTime",
        "123",
        ttl = "Fall Asleep",
        url = ""
    )

    goalList.add(sampleGoal)


    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowLottieAnimation(rawRes = R.raw.pace_anim, modifier = Modifier.size(200.dp))

            Text(
                text = "Select The Goal You\nWant To Achieve",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 16.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {

                items(goalList) { item ->

                    GoalSelectionItem(
                        goal = item,
                        selected = item.code == selectedGoal?.code,
                        backgroundImage = R.drawable.sample_stress_img
                    ) { music ->
                        viewModel.updateSelectedGoal(music)
                    }
                }

            }
        }

    }

}

@Composable
fun GoalSelectionItem(
    goal: Value,
    selected: Boolean = false,
    @DrawableRes
    backgroundImage: Int,
    onClick: (Value) -> Unit
) {

    Box(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .clickable {
            onClick(goal)
        }
        .size(150.dp)) {
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = "background image",
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = if (isSystemInDarkTheme()) 0.5F else 0.25F)))
        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 8.dp, top = 8.dp),
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = "Selection icon",
            tint = if (selected) Color.Yellow else primaryColor
        )
        Column(
            modifier = Modifier
                .padding(all = 12.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = goal.ttl.uppercase(),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = if (selected) secondaryColor else primaryColor
            )
        }
    }

}