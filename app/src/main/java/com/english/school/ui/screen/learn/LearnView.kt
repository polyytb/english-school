package com.english.school.ui.screen.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.english.school.data.storage.models.Words
import com.english.school.ui.elements.BackPressHandler
import com.english.school.ui.elements.BackRow
import com.english.school.ui.elements.DefText
import com.english.school.ui.navigation.Screen
import com.english.school.ui.screen.main.MainViewModel
import com.english.school.ui.theme.Blue


@Composable
fun LearnView(
    navController: NavController,
    mainViewModel: MainViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        mainViewModel.loadWords()
    }

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
    var open by remember { mutableStateOf(Words()) }
    val list = MainViewModel.wordList.collectAsState()

    Column (
        Modifier
            .fillMaxSize()
            .background(White)
            .padding(start = 20.dp, end = 20.dp)
    ){
        BackRow{
            if (open.word.isEmpty())
                backClick()
            else
                open = Words()
        }

        if (open.word.isEmpty()){
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
private fun Info(word: Words) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp)
    ){
        DefText(textId = "Слово", size = 14, modifier = Modifier.padding(top = 20.dp))
        DefText(textId = word.word, size = 24, color = Blue, modifier = Modifier.padding(top = 10.dp))
        DefText(textId = "Транскрипция", size = 14, modifier = Modifier.padding(top = 20.dp))
        DefText(textId = word.transcriptions, size = 20, color = Blue, modifier = Modifier.padding(top = 10.dp))
        DefText(textId = "Перевод", size = 14, modifier = Modifier.padding(top = 20.dp))
        DefText(textId = word.translate, size = 20, color = Blue, modifier = Modifier.padding(top = 10.dp))
    }
}

@Composable
private fun List(list: List<Words>, open: (Words) -> Unit) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            items(list.size) {
                Box (
                    modifier = Modifier
                        .size(80.dp)
                        .background(Blue, RoundedCornerShape(30.dp))
                        .clickable {
                            open(list[it])
                        }
                ){
                    DefText(textId = list[it].word, color = White, size = 14,
                        modifier = Modifier.align(Alignment.Center))
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