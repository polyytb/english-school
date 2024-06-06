package com.english.school.ui.screen.tests

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.english.school.R
import com.english.school.api.Answer
import com.english.school.data.storage.models.Words
import com.english.school.ui.elements.BackPressHandler
import com.english.school.ui.elements.BackRow
import com.english.school.ui.elements.DefText
import com.english.school.ui.navigation.Screen
import com.english.school.ui.screen.main.MainViewModel
import com.english.school.ui.theme.Blue
import com.english.school.utils.ToastHelper
import com.english.school.utils.Utils

@Composable
fun TestsView(
    navController: NavController,
    mainViewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        mainViewModel.loadWords()
    }

    UI(
        mainViewModel,
        backClick = {
            navController.navigate(Screen.Main.name)
        },
        update = {
//            mainViewModel.updateWord(context, it)
        }
    )

    BackPressHandler{}
}

@Composable
private fun UI(
    mainViewModel: MainViewModel,
    update: (Words) -> Unit = {},
    backClick: () -> Unit = {}
) {
    val context = LocalContext.current

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
            Info(mainViewModel, open) {
                if (it != null)
                    update(it)
                val timer = object: CountDownTimer(1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) { }

                    override fun onFinish() {
                        if (it != null)
                            mainViewModel.loadWords()
                        open = Words()
                    }
                }
                timer.start()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Info(mainViewModel: MainViewModel, words: Words, update: (Words?) -> Unit) {

    val context = LocalContext.current
    var answer by remember { mutableStateOf("") }
    val variants = Utils.parseStrToList(words.variants)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 30.dp, end = 30.dp)
    ){
        DefText(textId = "Нажмите на кнопку с правильным переводом", size = 14, weight = FontWeight.Bold,
            textAlign = TextAlign.Center, modifier = Modifier.padding(top = 10.dp))
        DefText(textId = words.word, size = 30, weight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 20.dp))

        FlowRow (
            Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.Center
        ){
            variants.forEach {
                Box (
                    modifier = Modifier
                        .padding(5.dp)
                        .height(60.dp)
                        .width(100.dp)
                        .background(
                            if (answer.isNotEmpty()) {
                                if (words.translate == it) Green
                                else Red
                            } else Blue,
                            RoundedCornerShape(30.dp)
                        )
                        .clickable {
                            if (answer.isEmpty()) {
                                answer = it
                                if (it == words.translate) {
                                    MainViewModel.answersList.value.add(Answer(wordId = words.id))
                                    mainViewModel.insertAnswer(words.id)
                                    ToastHelper().show(context, context.getString(R.string.success))
                                    update(words)
                                } else {
                                    ToastHelper().show(context, context.getString(R.string.fail))
                                    update(null)
                                }
                            }
                        }
                ){
                    DefText(textId = it, color = White, size = 14,
                        modifier = Modifier.align(Alignment.Center))
                }
            }

        }

    }
}

@Composable
private fun List(list: List<Words>, open: (Words) -> Unit) {

    val answerList = MainViewModel.answersList.collectAsState()

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

                val status = answerList.value.firstOrNull{ elem -> elem.wordId == list[it].id }

                Box (
                    modifier = Modifier
                        .size(80.dp)
                        .background(
                            if (status != null) Green else Blue,
                            RoundedCornerShape(30.dp)
                        )
                        .clickable {
                            open(list[it])
                        }
                ){
                    DefText(textId = "Тест ${it + 1} ", color = White, size = 14,
                        modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    UI(hiltViewModel())
}