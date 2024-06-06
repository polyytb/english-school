package com.english.school.ui.screen.main

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.english.school.R
import com.english.school.ui.elements.BackPressHandler
import com.english.school.ui.elements.DefButton
import com.english.school.ui.elements.DefText
import com.english.school.ui.navigation.Screen
import com.english.school.ui.theme.Blue
import com.english.school.ui.theme.Gray
import com.english.school.ui.theme.lPadding
import com.english.school.utils.Const

@Composable
fun MainView(
    navController: NavController,
    mainViewModel: MainViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        mainViewModel.loadAnswers()
    }

    UI(
        rule = {
            navController.navigate(Screen.Rule.name)
        },
        learn = {
            navController.navigate(Screen.Learn.name)
        },
        test = {
            navController.navigate(Screen.Tests.name)
        },
        logout = {
            mainViewModel.logout()
            navController.navigate(Screen.Login.name)
        }
    )

    BackPressHandler {}
}

@Composable
private fun UI(
    rule: () -> Unit = {},
    learn: () -> Unit = {},
    test: () -> Unit = {},
    logout: () -> Unit = {}
) {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CustomBtn(
            R.drawable.rule,
            R.string.rules
        ) {
            rule()
        }
        CustomBtn(
            R.drawable.learn,
            R.string.learnWords
        ) {
            learn()
        }
        CustomBtn(
            R.drawable.test,
            R.string.tests
        ) {
            test()
        }
        DefButton(
            text = stringResource(id = R.string.logout),
            onClick = { logout() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(top = lPadding)
                .width(120.dp)
        )
    }
}

@Composable
private fun CustomBtn(iconId: Int, strId: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(10.dp)
            .size(120.dp)
            .border(1.dp, Blue, RoundedCornerShape(30.dp))
            .clickable {
                onClick()
            }
    ){
        Icon(
            painterResource(id = iconId),
            contentDescription = Const.IMAGE_DESCRIPTION,
            modifier = Modifier.size(50.dp),
            tint = Blue
        )
        DefText(textId = strId, modifier = Modifier.padding(top = 5.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    UI(hiltViewModel())
}