package com.english.school.ui.screen.rules

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.english.school.ui.elements.BackPressHandler
import com.english.school.ui.elements.BackRow
import com.english.school.ui.elements.DefText
import com.english.school.ui.navigation.Screen
import com.english.school.ui.screen.main.MainViewModel
import com.english.school.ui.theme.Blue

data class Rule(
    val rule: String = "",
    val desc: String = ""
)

@Composable
fun RulesView(
    navController: NavController,
) {
    UI(
        backClick = {
            navController.navigate(Screen.Main.name)
        }
    )

    BackPressHandler{}
}

@Composable
private fun UI(
    backClick: () -> Unit = {}
) {
    var open by remember { mutableStateOf(Rule()) }
    val list = MainViewModel.ruleList.collectAsState()
    Column (
        Modifier
            .fillMaxSize()
            .background(White)
            .padding(start = 20.dp, end = 20.dp)
    ){
        BackRow{
            if (open.rule.isEmpty())
                backClick()
            else
                open = Rule()
        }

        if (open.rule.isEmpty()){
            List(
                list.value,
                open = {
                    open = it
                }
            )
        } else {
            Info(open)
        }
    }
}

@Composable
private fun Info(rule: Rule) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 30.dp, end = 30.dp)
    ){
        DefText(textId = rule.rule, size = 20, modifier = Modifier.padding(top = 10.dp))

        Box (
            modifier = Modifier
                .padding(top = 20.dp)
                .background(Blue, RoundedCornerShape(30.dp))
                .padding(20.dp)
        ){
            DefText(textId = rule.desc, size = 14, color = White)
        }

    }
}

@Composable
private fun List(list: List<Rule>, open: (Rule) -> Unit) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            items(list.size) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Blue, RoundedCornerShape(30.dp))
                        .padding(start = 20.dp)
                        .clickable {
                            open(list[it])
                        }
                ){
                    DefText(textId = list[it].rule, color = White, size = 12)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    UI()
}