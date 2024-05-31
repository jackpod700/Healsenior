package com.example.Healsenior.data
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

val database = FirebaseFirestore.getInstance()
    fun writeNewUser(
        UID: String
    ) {
        val recordmap = emptyMap<String, Map<String,Int>>()
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
        database.collection("User").document(user.uid).set(user)
    }

    fun GetUser(UID: String, callback: (User?) -> Unit) {
        database.collection("User").document(UID).get().addOnSuccessListener {
            val user = it.toObject(User::class.java)
            callback(user)  // 콜백에 결과 전달
        }.addOnFailureListener {
            println("Error getting documents: $it")
            callback(null)  // 실패 시 콜백에 null 전달
        }
    }
//GetUser 사용예시
//val uid = loginViewModel.auth.currentUser!!.uid
//var user1:User?=null
//GetUser(uid){user->
//    if (user != null) {
//        user1=user
//    } else {
//        println("No user found or error occurred")
//    }
//}

    fun GetUserAll(callback: (List<User>) -> Unit) {
        database.collection("User").get().addOnSuccessListener {
            val userList = it.toObjects(User::class.java)
            callback(userList)
        }.addOnFailureListener {
            println("Error getting documents: $it")
            callback(emptyList())
        }
    }
//GetUserAll 사용예시
//var userlist1:List<User>?=null
//GetUserAll{Userlist->
//    if (Userlist != null) {
//        userlist1=Userlist
//        println(userlist1)
//    } else {
//        println("No user found or error occurred")
//    }
//}

    fun GetRoutine(rid: String, callback: (Routine?) -> Unit) {
        database.collection("Routine").document(rid).get().addOnSuccessListener {
            val routine = it.toObject(Routine::class.java)
            if (routine != null) {
                callback(routine)
                return@addOnSuccessListener
            } else {
                callback(null)
            }
        }.addOnFailureListener {
            println("Error getting documents: $it")
        }
    }
//GetRoutine 사용예시
//var routine1:Routine?=null
//GetRoutine("gym1"){routine->
//    if (routine != null) {
//        routine1=routine
//        println(routine1)
//    } else {
//        println("No user found or error occurred")
//    }
//}

    fun GetRoutineAll(callback: (List<Routine>) -> Unit) {
        database.collection("Routine").get().addOnSuccessListener {
            val routineList = it.toObjects(Routine::class.java)
            callback(routineList)
        }.addOnFailureListener {
            println("Error getting documents: $it")
            callback(emptyList())
        }
    }
//GetRoutineAll 사용예시
//var routinelist1:List<Routine>?=null
//GetRoutineAll{Routinelist->
//    if (Routinelist != null) {
//        routinelist1=Routinelist
//        println(routinelist1)
//    } else {
//        println("No user found or error occurred")
//    }
//}

    fun GetRoutineDaily(rid: String, day: Int, callback: (RoutineDaily?) -> Unit) {
        database.collection("RoutineDaily").document(rid).get().addOnSuccessListener {
            val routineDaily = it.toObject(RoutineDaily::class.java)
            if (routineDaily != null) {
                callback(routineDaily)
                return@addOnSuccessListener
            } else {
                callback(null)
            }
        }.addOnFailureListener {
            println("Error getting documents: $it")
        }
    }
//GetRoutineDaily 사용예시
//var routinedaily1:RoutineDaily?=null
//GetRoutineDaily("gym1",1){routineDaily->
//    if (routineDaily != null) {
//        routinedaily1=routineDaily
//        println(routinedaily1)
//    } else {
//        println("No user found or error occurred")
//    }
//}

    fun GetRoutineDailyAll(rid: String,callback: (List<RoutineDaily>) -> Unit) {
        database.collection("RoutineDaily").whereEqualTo("rid",rid).get().addOnSuccessListener {
            val routineDailyList = it.toObjects(RoutineDaily::class.java)
            callback(routineDailyList)
        }.addOnFailureListener {
            println("Error getting documents: $it")
            callback(emptyList())
        }
    }
//GetRoutineDailyAll 사용예시
//var routinedailylist1:List<RoutineDaily>?=null
//GetRoutineDailyAll("gym1"){routinedailylist->
//    if (routinedailylist != null) {
//        routinedailylist1=routinedailylist
//        println(routinedailylist1)
//    } else {
//        println("No user found or error occurred")
//    }
//}
