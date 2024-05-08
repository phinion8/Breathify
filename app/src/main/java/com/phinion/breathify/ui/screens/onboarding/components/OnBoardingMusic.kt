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
import androidx.hilt.navigation.compose.hiltViewModel
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
fun OnBoardingMusic(
    viewModel: OnBoardingViewModel
) {
    val musicData = AppConfig.getAppConfiguration().data.breathingToolData.prc.filterList {
        (this.code == "msc")
    }

    val musicList = musicData[0].values.toMutableList()

    val selectedMusic by viewModel.selectedMusic.collectAsState()

    val sampleMusic = Value(
        code = "calm",
        dsc = "Calm Music",
        "123",
        ttl =  "Calming",
        url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
    )

    musicList.add(sampleMusic)


    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowLottieAnimation(rawRes = R.raw.music_anim, modifier = Modifier.size(200.dp))

            Text(
                text = "Select Music For Your\nBreathing Exercises",
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

                items(musicList) { item ->

                    MusicSelectionItem(
                        music = item,
                        selected = item.code == selectedMusic?.code
                    ) { music ->
                        viewModel.updateSelectedMusic(music)
                    }
                }

            }
        }

    }

}

@Composable
fun MusicSelectionItem(
    music: Value,
    selected: Boolean = false,
    onClick: (Value) -> Unit
) {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick(music)
            }
            .background(if (selected) blue else grey)
            .padding(all = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(72.dp),
            painter = painterResource(id = R.drawable.ic_music_bar),
            contentDescription = "Music Bar",
            tint = if (selected) white else primaryColor
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = music.ttl.uppercase(),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = if (selected) white else primaryColor
        )
    }
}


