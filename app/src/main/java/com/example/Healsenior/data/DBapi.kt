package com.example.Healsenior.data
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

//val database = FirebaseDatabase.getInstance()
val database = FirebaseFirestore.getInstance()
    //private val usersRef = database.getReference("User")
    //private val routinesRef = database.getReference("Routine")
    fun writeNewUser(
        UID: String
    ) {
        val recordmap = emptyMap<Date, List<Long>>()
        val user =
            User(UID,
                "User"+ Math.random().toInt().toString(),
                0,
                0,
                0, 0,
                0,
                0,
                0,
                recordmap)
        //usersRef.child(user.uid).setValue(user);
        database.collection("User").document(user.uid).set(user)
    }

    fun GetUser(UID: String) {
        //usersRef.child(UID).get().addOnSuccessListener {
        database.collection("User").document(UID).get().addOnSuccessListener {
            val user = it.toObject(User::class.java)
            if (user != null) {
                println(user)
            } else {
                println("No such document")
            }
        }.addOnFailureListener {
            println("Error getting documents: $it")
        }
    }
    fun writeNewRoutine(
        RID: Long,
        name: String,
        workoutList: Map<Int, List<Long>>,
        place: String,
        goal: String,
        description: String,
        time: Long,
        difficulty: Int
    ) {
        val routine = Routine(RID, name, workoutList, place, goal, description, time, difficulty)
        //routinesRef.child(routine.rid.toString()).setValue(routine)
        database.collection("Routine").document(routine.rid.toString()).set(routine)
    }
