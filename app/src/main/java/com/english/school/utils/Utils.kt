package com.english.school.utils

class Utils {
    companion object{
        fun parseStrToList(str: String): List<String> {
            val jsonString = str.replace("[", "").replace("]", "")
            val arr = jsonString.split(", ")
            return arr.toList()
        }
    }
}