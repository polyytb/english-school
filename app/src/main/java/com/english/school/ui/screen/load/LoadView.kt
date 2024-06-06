package com.english.school.ui.screen.load

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.english.school.R
import com.english.school.api.FirebaseRepository
import com.english.school.ui.elements.BackPressHandler
import com.english.school.ui.navigation.Screen
import com.english.school.utils.Const
import com.english.school.ui.screen.main.MainViewModel
import com.english.school.ui.screen.main.loadWordList
import com.english.school.ui.screen.main.ruleList
import kotlinx.coroutines.delay

@Composable
fun LoadView(
    navController: NavController,
    mainViewModel: MainViewModel = viewModel()
) {

    Countdown(1000L) {
        mainViewModel.loadWords()
        mainViewModel.loadRules()
        mainViewModel.loadAnswers()

        FirebaseRepository.checkAuth{
            if (it != null) {
                navController.navigate(Screen.Main.name)
            }
            else navController.navigate(Screen.Login.name)
        }
    }

    UI()

    BackPressHandler{}
}

@Composable
private fun UI() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.english_logo),
            contentDescription = Const.IMAGE_DESCRIPTION,
            modifier = Modifier.size(300.dp)
        )
    }
}

@Composable
fun Countdown(targetTime: Long, endEvent: () -> Unit) {
    var remainingTime by remember(targetTime) {
        mutableLongStateOf(targetTime - System.currentTimeMillis())
    }

    LaunchedEffect(remainingTime) {
        delay(targetTime)
        remainingTime = targetTime - System.currentTimeMillis()
        endEvent()
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    UI()
}