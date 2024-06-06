package com.english.school.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.english.school.utils.Const

class Words(
    val id: String = "",
    val word: String = "",
    val transcriptions: String = "",
    val translate: String = "",
    var variants: String = "",
    var status: String = ""
)

