package com.english.school.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.english.school.R
import com.english.school.ui.theme.Gray
import com.english.school.utils.Const

@Composable
fun BackRow(
    iconColor: Color = Gray,
    onClick:() -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
    ) {
        Icon(
            painterResource(id = R.drawable.back),
            Const.IMAGE_DESCRIPTION,
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    onClick()
                },
            tint = iconColor
        )
    }
}