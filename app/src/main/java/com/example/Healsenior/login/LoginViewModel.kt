package com.example.Healsenior.login

import android.app.Activity
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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.Healsenior.data.GetUser
import com.example.Healsenior.data.User
import com.example.Healsenior.ui.MainActivity
import com.example.Healsenior.data.writeNewUser
import java.lang.Math.random

//class LoginViewModel(application: Application) : AndroidViewModel(application) {
//
//    val auth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val googleSignInClient: GoogleSignInClient
//
//    init {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(application.getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        googleSignInClient = GoogleSignIn.getClient(application, gso)
//    }
//
//    fun getGoogleSignInIntent(): Intent {
//        return googleSignInClient.signInIntent
//    }
//
//    fun handleSignInResult(result: ActivityResult, activity: Activity) {
//        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//        try {
//            val account = task.getResult(ApiException::class.java)
//            if (account != null) {
//                Log.d("Login", "Google Sign-In successful, ID Token: ${account.idToken}")
//                firebaseAuthWithGoogle(account.idToken!!, activity)
//            } else {
//                Log.e("Login", "Google Sign-In account is null")
//            }
//        } catch (e: ApiException) {
//            Log.e("Login", "Google Sign-In failed: ${e.message}", e)
//        }
//    }
//
//    private fun firebaseAuthWithGoogle(idToken: String, activity: Activity) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Log.d("Login", "signInWithCredential:success")
//                    // 로그인 성공 후 필요한 작업 수행
//                    saveLoginState(getApplication(), true)
//                    if(task.result?.additionalUserInfo?.isNewUser == true) {
//                        writeNewUser(auth.currentUser!!.uid)
//                    }
//                    restartMainActivity(activity)
//                } else {
//                    Log.w("Login", "signInWithCredential:failure", task.exception)
//                }
//            }
//    }
//
//    fun getCurrentUserUid(): String? {
//        return auth.currentUser?.uid
//    }
//}

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val googleSignInClient: GoogleSignInClient

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> get() = _currentUser

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(application.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(application, gso)

        // 현재 로그인된 사용자의 정보를 가져옴
        fetchCurrentUser()
    }

    fun getGoogleSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    fun handleSignInResult(result: ActivityResult, activity: Activity) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                Log.d("Login", "Google Sign-In successful, ID Token: ${account.idToken}")
                firebaseAuthWithGoogle(account.idToken!!, activity)
            } else {
                Log.e("Login", "Google Sign-In account is null")
            }
        } catch (e: ApiException) {
            Log.e("Login", "Google Sign-In failed: ${e.message}", e)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String, activity: Activity) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Login", "signInWithCredential:success")
                    // 로그인 성공 후 필요한 작업 수행
                    saveLoginState(getApplication(), true)
                    if (task.result?.additionalUserInfo?.isNewUser == true) {
                        writeNewUser(auth.currentUser!!.uid)
                    }
                    fetchCurrentUser()
                    restartMainActivity(activity)
                } else {
                    Log.w("Login", "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun fetchCurrentUser() {
        val uid = auth.currentUser?.uid
        if (uid != null) {
            GetUser(uid) { user ->
                _currentUser.postValue(user)
            }
        }
    }

    fun getCurrentUserUid(): String? {
        return auth.currentUser?.uid
    }
}
private fun saveLoginState(context: Context, isLoggedIn: Boolean) {
    val sharedPref = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putBoolean("isLoggedIn", isLoggedIn)
        apply()
    }
}

fun checkLoginState(context: Context): Boolean {
    val sharedPref = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    return sharedPref.getBoolean("isLoggedIn", false)
}

fun logout(context: Context) {
    FirebaseAuth.getInstance().signOut()
    saveLoginState(context, false)
    restartMainActivity(context as Activity)
}

fun restartMainActivity(activity: Activity) {
    val intent = Intent(activity, MainActivity::class.java)
    activity.finish()
    activity.startActivity(intent)
}
