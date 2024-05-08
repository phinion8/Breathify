package com.phinion.breathify.ui.screens.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.phinion.breathify.R
import com.phinion.breathify.data.remote.AppConfig
import com.phinion.breathify.domain.models.Value
import com.phinion.breathify.ui.components.ShowLottieAnimation
import com.phinion.breathify.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.phinion.breathify.ui.theme.blue
import com.phinion.breathify.ui.theme.grey
import com.phinion.breathify.ui.theme.primaryColor
import com.phinion.breathify.ui.theme.white
import okhttp3.internal.filterList

@Composable
fun OnBoardingBreakTime(
    viewModel: OnBoardingViewModel
) {
    val breakTimeDate = AppConfig.getAppConfiguration().data.breathingToolData.prc.filterList {
        (this.code == "br")
    }

    val breakTimeList = breakTimeDate[0].values.toMutableList()

    val selectedBreakTime by viewModel.selectedBreakTime.collectAsState()

    val sampleBreakTime = Value(
        code = "3",
        dsc = "",
        "123",
        ttl =  "3 Minutes",
        url = ""
    )

    breakTimeList.add(sampleBreakTime)


    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowLottieAnimation(rawRes = R.raw.break_time_anim, modifier = Modifier.size(200.dp))

            Text(
                text = "Select The Break Time",
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

                items(breakTimeList) { item ->

                    BreakTimeSelectionItem(
                        breakTime = item,
                        selected = item.code == selectedBreakTime?.code
                    ) { music ->
                        viewModel.updateSelectedBreakTime(music)
                    }
                }

            }
        }

    }

}

@Composable
fun BreakTimeSelectionItem(
    breakTime: Value,
    selected: Boolean = false,
    onClick: (Value) -> Unit
) {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick(breakTime)
            }
            .background(if (selected) blue else grey)
            .padding(all = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(72.dp),
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = "Music Bar",
            tint = if (selected) white else primaryColor
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = breakTime.ttl.uppercase(),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = if (selected) white else primaryColor
        )
    }
}