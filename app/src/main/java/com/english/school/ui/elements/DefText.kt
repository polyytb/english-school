package com.english.school.ui.elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.english.school.ui.theme.Black
import com.english.school.ui.theme.mText
import com.english.school.ui.theme.nonScaledSp

@Composable
fun DefText(
    textId: Int,
    size: Int = mText,
    color: Color = Black,
    weight: FontWeight = FontWeight.Normal,
    style: FontStyle = FontStyle.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = textId),
        fontSize = size.nonScaledSp,
        color = color,
        fontStyle = style,
        fontWeight = weight,
        modifier = modifier
    )
}

@Composable
fun DefText(
    textId: String,
    size: Int = mText,
    color: Color = Black,
    weight: FontWeight = FontWeight.Normal,
    style: FontStyle = FontStyle.Normal,
    textDecoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
) {
    Text(
        text = textId,
        fontSize = size.nonScaledSp,
        color = color,
        fontStyle = style,
        fontWeight = weight,
        modifier = modifier,
        textAlign = textAlign,
        maxLines = if (singleLine) 1 else 50,
        textDecoration = textDecoration,
    )
}