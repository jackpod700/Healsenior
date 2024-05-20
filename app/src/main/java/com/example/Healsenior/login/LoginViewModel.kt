package com.example.Healsenior.login

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.lifecycle.AndroidViewModel
import com.example.Healsenior.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import android.content.Context
import android.content.SharedPreferences

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val googleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(application.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(application, gso)
    }

    fun getGoogleSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    fun handleSignInResult(result: ActivityResult) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                Log.d("Login", "Google Sign-In successful, ID Token: ${account.idToken}")
                firebaseAuthWithGoogle(account.idToken!!)
            } else {
                Log.e("Login", "Google Sign-In account is null")
            }
        } catch (e: ApiException) {
            Log.e("Login", "Google Sign-In failed: ${e.message}", e)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Login", "signInWithCredential:success")
                    // 로그인 성공 후 필요한 작업 수행
                } else {
                    Log.w("Login", "signInWithCredential:failure", task.exception)
                }
            }
    }

    private fun saveLoginState(context: Context, isLoggedIn: Boolean) {
        val sharedPref = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("isLoggedIn", isLoggedIn)
            apply()
        }
    }

    private fun checkLoginState(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("isLoggedIn", false)
    }

    fun logout(context: Context) {
        FirebaseAuth.getInstance().signOut()
        saveLoginState(context, false)
    }
}