package com.example.secondprojectbymvvm.viewmodel

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AuthViewModel():ViewModel() {

    lateinit var userData: MutableLiveData<FirebaseUser>
    lateinit var loggedStatus: MutableLiveData<Boolean>
    lateinit var  messageObs:MutableLiveData<String>
    var firebaseUserLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()
    var userLoggedLiveData: MutableLiveData<Boolean> = MutableLiveData()


    private val auth: FirebaseAuth = Firebase.auth
    private lateinit var currentUser : FirebaseUser


    //Email Registration Part
    fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if(auth.currentUser != null) {
                    currentUser = auth.currentUser as FirebaseUser
                    firebaseUserLiveData.postValue(auth.currentUser)
                }

            } else {
                messageObs = MutableLiveData<String>()
                messageObs.postValue(task.exception!!.message)
            }
            sendEmailVerification()
        }
    }
    private fun sendEmailVerification() {
        currentUser.let {
            currentUser.sendEmailVerification()
        }
    }




    //Login Part
    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseUserLiveData.postValue(auth.currentUser)
                } else {
                    messageObs.postValue(task.exception!!.message)
                }
            }
    }


    //Logout Part
    fun signOut() {
        auth.signOut()
        userLoggedLiveData.postValue(true)
    }

    init {
        if (auth.currentUser != null) {
            firebaseUserLiveData.postValue(auth.currentUser)
        }
    }

}