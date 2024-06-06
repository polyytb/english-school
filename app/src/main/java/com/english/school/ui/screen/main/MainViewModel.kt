package com.english.school.ui.screen.main

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import com.english.school.R
import com.english.school.api.Answer
import com.english.school.api.FirebaseRepository
import com.english.school.data.storage.PrefManager
import com.english.school.data.storage.models.Words
import com.english.school.ui.screen.rules.Rule
import com.english.school.utils.ToastHelper
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class MainViewModel: ViewModel() {

    companion object {
        var user: FirebaseUser? = null

        val wordList = MutableStateFlow(listOf<Words>())
        val ruleList = MutableStateFlow(listOf<Rule>())
        val answersList = MutableStateFlow(mutableListOf<Answer>())
    }

    fun login(context: Context, login: String, password: String, callback: (Boolean) -> Unit) {
        if (login.isEmpty() || password.isEmpty()){
            ToastHelper().show(context, context.getString(R.string.toastEmptyToken))
            callback(false)
            return
        }
        if (password.length < 6) {
            ToastHelper().show(context, context.getString(R.string.toastErrorPass))
            callback(false)
            return
        }

        val activity = context as Activity

        FirebaseRepository().signInFirebase(activity, login, password) {
            callback(it != null)
        }
    }

    fun reg(context: Context, login: String, password: String, secondPassword: String, callback: (Boolean) -> Unit) {
        if (login.isEmpty() || password.isEmpty()){
            ToastHelper().show(context, context.getString(R.string.toastEmptyToken))
            callback(false)
            return
        }
        if (password.length < 6) {
            ToastHelper().show(context, context.getString(R.string.toastErrorPass))
            callback(false)
            return
        }
        if (password != secondPassword) {
            ToastHelper().show(context, context.getString(R.string.toastErrorPassEqual))
            callback(false)
            return
        }

        FirebaseRepository().signUpFirebase(context as Activity, login, password) {
            callback(it != null)
        }
    }

    fun logout() {
        PrefManager().clear()
        user = null
        answersList.value = mutableListOf()
        FirebaseRepository().signOutFirebase()
    }

    fun loadRules() {
        FirebaseRepository().getRules {
            ruleList.value = it
        }
    }

    fun loadWords() {
        FirebaseRepository().getWords {
            wordList.value = it
        }
    }

    fun loadAnswers() {
        if (user == null) FirebaseRepository.checkAuth { user = it }
        if (user != null) {
            FirebaseRepository().getAnswers(user!!.uid) {
                answersList.value = it.toMutableList()
            }
        }
    }

    fun insertAnswer(wordId: String) {
        if (user == null) FirebaseRepository.checkAuth { user = it }
        if (user != null) {
            FirebaseRepository().insertAnswer(Answer(user!!.uid, wordId, "Success"))
        }
    }
}