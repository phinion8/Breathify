package com.phinion.breathify.ui.screens.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phinion.breathify.R
import com.phinion.breathify.ui.screens.onboarding.viewmodel.OnBoardingViewModel
import com.phinion.breathify.ui.theme.poppins_medium
import com.phinion.breathify.ui.theme.primaryColor

@Composable
fun OnBoardingName(
    viewModel: OnBoardingViewModel
) {

    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier.padding(top = 42.dp, end = 16.dp, start = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Let's Get Started With\nYour Name",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.avatar_male),
            contentDescription = "Avatar Pic"
        )

        Spacer(modifier = Modifier.height(32.dp))

        BasicTextField(
            modifier = Modifier.focusRequester(focusRequester),
            value = viewModel.userName.value,
            onValueChange = {
                viewModel.updateUserName(it)
            },
            textStyle = TextStyle(
                fontSize = 32.sp,
                color = primaryColor,
                textAlign = TextAlign.Center,
                fontFamily = poppins_medium
            ),
            singleLine = true,
            cursorBrush = SolidColor(primaryColor),
        )

    }

}