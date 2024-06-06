package com.english.school.ui.screen.reg

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.english.school.R
import com.english.school.ui.elements.BackPressHandler
import com.english.school.ui.elements.DefButton
import com.english.school.ui.elements.EditText
import com.english.school.ui.navigation.Screen
import com.english.school.ui.screen.main.MainViewModel
import com.english.school.ui.theme.Blue
import com.english.school.ui.theme.Gray
import com.english.school.ui.theme.lPadding
import com.english.school.ui.theme.lText

@Composable
fun RegView(
    navController: NavController,
    mainViewModel: MainViewModel = viewModel()
) {
    var isAuth by remember { mutableStateOf(false) }

    val context = LocalContext.current

    UI(
        isAuth = isAuth,
        reg = { mail, pass, repPass ->
            isAuth = true
            mainViewModel.reg(context, mail, pass, repPass) {
                isAuth = false
                if (it)
                    navController.navigate(Screen.Main.name)
            }
        },
        back = {
            navController.navigate(Screen.Login.name)
        }
    )
    BackPressHandler{
        navController.navigate(Screen.Login.name)
    }
}

@Composable
fun UI(
    isAuth: Boolean = false,
    reg: (String, String, String) -> Unit = {_, _, _ ->},
    back: () -> Unit = {}
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ){
        Auth(isAuth, reg, back)
    }
}

@Composable
fun Auth(
    isAuth: Boolean = false,
    reg: (String, String, String) -> Unit = {_, _, _ ->},
    back: () -> Unit = {}
) {
    var mail by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var repPass by remember { mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EditText(mail, stringResource(id = R.string.mail)) { mail = it }

        EditText(
            pass,
            stringResource(id = R.string.pass),
            Modifier.padding(top = lPadding),
            true,
        ) { pass = it }

        EditText(
            repPass,
            stringResource(id = R.string.repPass),
            Modifier.padding(top = lPadding),
            true,
        ) { repPass = it }

        DefButton(
            text = stringResource(id = R.string.registration),
            textSize = lText,
            onClick = { reg(mail, pass, repPass) },
            enabled = !isAuth,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isAuth) Gray else Blue,
                contentColor = White
            ),
            modifier = Modifier
                .padding(top = lPadding)
                .width(190.dp)
        )

        DefButton(
            text = stringResource(id = R.string.back),
            onClick = { back() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
                contentColor = White
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .width(190.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    UI()
}