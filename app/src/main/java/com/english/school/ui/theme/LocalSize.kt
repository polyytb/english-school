package com.english.school.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val mText: Int  = 14
val lText: Int = 16
val lPadding = 20.dp
val lShape = 40.dp

//TextField
val textFieldHeight = 60.dp
val textFieldWidth = 280.dp

//Button
val smallButtonWidth = 150.dp
val buttonHeight = 40.dp

val Int.nonScaledSp
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp


