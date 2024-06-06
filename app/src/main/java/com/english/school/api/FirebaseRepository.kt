package com.english.school.api

import android.app.Activity
import android.util.Log
import com.english.school.data.storage.models.Words
import com.english.school.ui.screen.rules.Rule
import com.english.school.utils.Const
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class FirebaseRepository {

    companion object {
        private var auth: FirebaseAuth = Firebase.auth

        fun checkAuth(callback: (FirebaseUser?) -> Unit) {
            callback(auth.currentUser)
        }
    }

    private var firestore: FirebaseFirestore = Firebase.firestore

    fun signInFirebase(
        activity: Activity,
        email: String,
        password: String,
        callback: (FirebaseUser?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        Log.e("TEST", "signInWithEmail:success")
                        val user = auth.currentUser
                        callback(user)
                    } else {
                        Log.e("TEST", "signInWithEmail:failure", task.exception)

                        callback(null)
                    }
                }
        }
    }

    fun signUpFirebase(
        activity: Activity,
        email: String,
        password: String,
        callback: (FirebaseUser?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        Log.e("TEST", "createUserWithEmail:success")
                        val user = auth.currentUser
                        callback(user)
                    } else {
                        Log.e("TEST", "createUserWithEmail:failure", task.exception)
                        callback(null)
                    }
                }
        }
    }

    fun signOutFirebase() {
        auth.signOut()
    }

    fun getRules(callback: (List<Rule>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = mutableListOf<Rule>()
            firestore.collection(Const.RULE)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        list.add(
                            Rule(
                                document.data[Const.RULE].toString(),
                                document.data[Const.DESK].toString()
                            )
                        )
                    }
                    callback(list)
                }
                .addOnFailureListener { exception ->
                    Log.e(Const.LOG_TAG, "Error getting documents.", exception)
                }
        }
    }

    fun insertWords(list: List<Words>) {
        CoroutineScope(Dispatchers.IO).launch {
            list.forEach {
                val myUuid = UUID.randomUUID()
                firestore.collection(Const.WORDS)
                    .document(myUuid.toString())
                    .set(mapOf(
                        Const.ID to myUuid.toString(),
                        Const.WORD to it.word,
                        Const.TRANSCRIPTIONS to it.transcriptions,
                        Const.TRANSLATE to it.translate,
                        Const.VARIANTS to it.variants,
                    ))
            }
        }
    }

    fun insertRules(list: List<Rule>) {
        CoroutineScope(Dispatchers.IO).launch {
            list.forEach {
                firestore.collection(Const.RULE)
                    .document()
                    .set(mapOf(
                        Const.RULE to it.rule,
                        Const.DESK to it.desc
                    ))
            }
        }
    }

    fun getWords(callback: (List<Words>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = mutableListOf<Words>()
            firestore.collection(Const.WORDS)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        list.add(
                            Words(
                                document.data[Const.ID].toString(),
                                document.data[Const.WORD].toString(),
                                document.data[Const.TRANSCRIPTIONS].toString(),
                                document.data[Const.TRANSLATE].toString(),
                                document.data[Const.VARIANTS].toString()
                            )
                        )
                    }
                    callback(list)
                }
                .addOnFailureListener { exception ->
                    Log.e(Const.LOG_TAG, "Error getting documents.", exception)
                }
        }
    }

    fun insertAnswer(answer: Answer) {
        CoroutineScope(Dispatchers.IO).launch {
            firestore.collection(Const.ANSWER)
                .document()
                .set(mapOf(
                    Const.ID to answer.id,
                    Const.WORD_ID to answer.wordId,
                    Const.STATUS to answer.status
                ))
        }
    }

    fun getAnswers(userKey: String, callback: (List<Answer>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = mutableListOf<Answer>()
            firestore.collection(Const.ANSWER)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val key = document.data[Const.ID].toString()
                        if (userKey == key) {
                            list.add(
                                Answer(
                                    document.data[Const.ID].toString(),
                                    document.data[Const.WORD_ID].toString(),
                                    document.data[Const.STATUS].toString()
                                )
                            )
                        }

                    }
                    callback(list)
                }
                .addOnFailureListener { exception ->
                    Log.e(Const.LOG_TAG, "Error getting documents.", exception)
                }
        }
    }
}

data class Answer(
    var id: String = "",
    var wordId: String = "",
    var status: String = "Success",
)