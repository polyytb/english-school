package com.english.school.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.english.school.ui.theme.Gray
import com.english.school.ui.theme.LightGray
import com.english.school.ui.theme.lShape
import com.english.school.ui.theme.textFieldHeight
import com.english.school.ui.theme.textFieldWidth

@Composable
fun EditText (
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    password: Boolean = false,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    onClick: () -> Unit = {},
    onChange: (String) -> Unit,

) {
    TextField(
        value = text,
        label = { Text(hint, color = Gray) },
        onValueChange = onChange,
        visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = LightGray,
            unfocusedContainerColor = LightGray,
            focusedTextColor = Black,
            unfocusedTextColor = Black,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledContainerColor = LightGray,
            disabledTextColor = Black,
            disabledIndicatorColor = Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        enabled = enabled,
        modifier = modifier
            .height(textFieldHeight)
            .width(textFieldWidth)
            .clip(RoundedCornerShape(lShape))
            .shadow(5.dp, RoundedCornerShape(lShape))
            .clickable { onClick() }
    )
}