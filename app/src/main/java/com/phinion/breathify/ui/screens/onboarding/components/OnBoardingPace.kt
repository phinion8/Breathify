package com.phinion.breathify.ui.screens.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.phinion.breathify.R
import com.phinion.breathify.data.remote.AppConfig
import com.phinion.breathify.domain.models.Value
import com.phinion.breathify.ui.components.ExpandableText
import com.phinion.breathify.ui.components.ShowLottieAnimation
import com.phinion.breathify.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.phinion.breathify.ui.theme.blue
import com.phinion.breathify.ui.theme.grey
import com.phinion.breathify.ui.theme.lightGrey
import com.phinion.breathify.ui.theme.primaryColor
import com.phinion.breathify.ui.theme.white
import okhttp3.internal.filterList

@Composable
fun OnBoardingPace(
    viewModel: OnBoardingViewModel
) {

    val paceData = AppConfig.getAppConfiguration().data.breathingToolData.prc.filterList {
        (this.code == "pace")
    }

    val paceList = paceData[0].values.toMutableList()

    val selectedPace by viewModel.selectedPace.collectAsState()

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
                text = "Select Pace For Your Breathing Exercise",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            ExpandableText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = paceData[0].dsc,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    lightGrey,
                    textAlign = TextAlign.Center
                ),
                readMoreTextStyle = MaterialTheme.typography.bodyMedium.copy(
                    lightGrey,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {

                items(paceList) { item ->
                    PaceSelectionItem(
                        pace = item,
                        selected = item.code == selectedPace?.code,
                    ) { pace ->
                        viewModel.updateSelectedPace(pace)
                    }
                }

            }
        }

    }

}

@Composable
fun PaceSelectionItem(
    pace: Value,
    selected: Boolean = false,
    onClick: (Value) -> Unit
) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick(pace)
            }
            .background(if (selected) blue else grey)
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(72.dp),
            painter = painterResource(id = R.drawable.ic_snail),
            contentDescription = "Music Bar",
            tint = if (selected) white else primaryColor
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = pace.ttl.uppercase(),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = if (selected) white else primaryColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            ExpandableText(
                text = pace.dsc,
                modifier = Modifier,
                textStyle = MaterialTheme.typography.bodySmall.copy(
                    color = if (selected) white else lightGrey,
                    textAlign = TextAlign.Start
                ),
                maxLine = 5,
                readMoreTextStyle = MaterialTheme.typography.bodySmall.copy(
                    color = if (selected) white else lightGrey,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold
                )
            )
        }

    }
}