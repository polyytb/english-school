package com.english.school.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
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
import com.english.school.utils.Const
import com.english.school.ui.screen.main.MainViewModel
import com.english.school.ui.theme.Blue
import com.english.school.ui.theme.Gray
import com.english.school.ui.theme.lPadding

@Composable
fun LoginView(
    navController: NavController,
    mainViewModel: MainViewModel = viewModel()
) {
    var isAuth by remember { mutableStateOf(false) }

    val context = LocalContext.current

    UI(
        isAuth = isAuth,
        onClickLogin = { mail, pass ->
            isAuth = true
            mainViewModel.login(context, mail, pass) {
                isAuth = false
                if (it) navController.navigate(Screen.Main.name)
            }
        },
        regOnClick = {
            navController.navigate(Screen.Reg.name)
        }
    )

    BackPressHandler{}
}

@Composable
private fun UI(
    isAuth: Boolean = false,
    onClickLogin: (String, String) -> Unit = {_, _ ->},
    regOnClick: () -> Unit = {}
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ){
        Logo()
        Auth(isAuth, onClickLogin, regOnClick = regOnClick)
    }
}

@Composable
fun Auth(
    isAuth: Boolean = false,
    onClick: (String, String) -> Unit,
    regOnClick: () -> Unit
) {
    var mail by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

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

        Row (
            Modifier.fillMaxWidth().padding(start = 60.dp, end = 60.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            DefButton(
                text = stringResource(id = R.string.registration),
                onClick = regOnClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue,
                    contentColor = White
                ),
                modifier = Modifier
                    .padding(top = lPadding)
                    .width(120.dp)
            )
            DefButton(
                text = stringResource(id = R.string.enter),
                onClick = { onClick(mail, pass) },
                enabled = !isAuth,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isAuth) Gray else Blue,
                    contentColor = White
                ),
                modifier = Modifier
                    .padding(top = lPadding)
                    .width(120.dp)
            )
        }
    }
}

@Composable
fun Logo() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.english_logo),
            contentDescription = Const.IMAGE_DESCRIPTION,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    UI()
}