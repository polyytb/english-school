package com.english.school.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.english.school.ui.theme.buttonHeight
import com.english.school.ui.theme.mText
import com.english.school.ui.theme.nonScaledSp
import com.english.school.ui.theme.smallButtonWidth

@Composable
fun DefButton(
    text: String,
    textSize: Int = mText,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = White,
    ),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = colors,
        enabled = enabled,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .width(smallButtonWidth)
            .height(buttonHeight)
    ) {
        Text(text, fontSize = textSize.nonScaledSp)
    }
}