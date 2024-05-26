package com.example.Healsenior.data
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

val database = FirebaseFirestore.getInstance()
    fun writeNewUser(
        UID: String
    ) {
        val recordmap = emptyMap<Date, String>()
        val user =
            User(UID,
                "User"+ Math.random().toInt().toString(),
                "",
                0,
                0, 0,
                0,
                0,
                0,
                recordmap)
        //usersRef.child(user.uid).setValue(user);
        database.collection("User").document(user.uid).set(user)
    }

    fun GetUser(UID: String, callback: (User?) -> Unit) {
        database.collection("User").document(UID).get().addOnSuccessListener {
            val user = it.toObject(User::class.java)
            if (user != null) {
                callback(user)
            } else {
                callback(null)
            }
        }.addOnFailureListener {
            println("Error getting documents: $it")
        }
    }
    //GetUser 사용법 예시(변형하셔서 사용하시면 됩니다)
    /*GetUser("some_uid") { user ->
        if (user != null) {
            // 여기에서 user 객체를 사용
        } else {
            println("User not found or error occurred")
        }
    }*/

