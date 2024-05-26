package com.example.Healsenior.data
import com.google.firebase.database.FirebaseDatabase

class DBapi {
    private val database = FirebaseDatabase.getInstance()
    private val usersRef = database.getReference("User")
    private val routinesRef = database.getReference("Routine")
    fun writeNewUser(UID: Long, Name: String, RID: Long, DayCount: Int, Rank: Int, Point: Int, WorkoutHour: Int, CalorieSum: Int, SetSum: Int, RecordMap: Map<String, List<Long>>) {
        val user = User(UID, Name, RID, DayCount, Rank, Point, WorkoutHour, CalorieSum, SetSum, RecordMap)
        usersRef.child(user.UID.toString()).setValue(user)
    }
}