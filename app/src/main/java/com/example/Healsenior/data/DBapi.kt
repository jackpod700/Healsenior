package com.example.Healsenior.data

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

val database = FirebaseFirestore.getInstance()
fun writeNewUser(
    UID: String
) {
    val recordmap = emptyMap<String, Map<String, Int>>()
    val user =
        User(
            UID,
            "User" + Math.random().toInt().toString(),
            "",
            0,
            0, 0,
            0,
            0,
            0,
            recordmap
        )
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

fun UpdateUser(user: User) {
    database.collection("User").document(user.uid).set(user)
}

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
    database.collection("RoutineDaily/" + rid + "/Days").document("Day" + day.toString()).get()
        .addOnSuccessListener {
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

fun GetRoutineDailyAll(rid: String, callback: (List<RoutineDaily>) -> Unit) {
    database.collection("RoutineDaily/" + rid + "/Days").get().addOnSuccessListener {
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

fun GetWorkout(wid: String, callback: (Workout?) -> Unit) {
    database.collection("Workout").document(wid).get().addOnSuccessListener {
        val workout = it.toObject(Workout::class.java)
        if (workout != null) {
            callback(workout)
            return@addOnSuccessListener
        } else {
            callback(null)
        }
    }.addOnFailureListener {
        println("Error getting documents: $it")
    }
}

fun GetPostId(callback: (Int) -> Unit) {
    database.collection("Post").document("0").get().addOnSuccessListener {
        val postId = (it["count"] as? Long)?.toInt() // Long 타입을 Int로 변환
        if (postId != null) {
            callback(postId)
        } else {
            // Handle the case where postId is null
        }
    }.addOnFailureListener {
        // Handle the failure
    }
}


fun writeNewPost(post: Post, onComplete: (Boolean) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val postCounterRef = db.collection("Post").document("0")

    db.runTransaction { transaction ->
        val snapshot = transaction.get(postCounterRef)
        val currentCount = snapshot.getLong("count") ?: throw Exception("Count not found")
        val newPostId = currentCount + 1

        // Set the new PID in the post
        post.pid = newPostId.toInt()

        // Update the counter document
        transaction.update(postCounterRef, "count", newPostId)

        // Add the new post to the "Post" collection
        val newPostRef = db.collection("Post").document(post.pid.toString())
        transaction.set(newPostRef, post)

        // Initialize the comment count for the new post
        val commentRef = db.collection("Comment").document(post.pid.toString())
        transaction.set(commentRef, mapOf("count" to 0))
    }.addOnSuccessListener {
        onComplete(true)
    }.addOnFailureListener { e ->
        e.printStackTrace()
        onComplete(false)
    }
}

fun GetPostAll(callback: (List<Post>) -> Unit) {
    database.collection("Post").get().addOnSuccessListener {
        val postList = it.toObjects(Post::class.java)
        callback(postList)
    }.addOnFailureListener {
        println("Error getting documents: $it")
        callback(emptyList())
    }
}

fun GetPostByUid(uid: String, callback: (List<Post>) -> Unit) {
    database.collection("Post").whereEqualTo("uid", uid).get().addOnSuccessListener {
        val postList = it.toObjects(Post::class.java)
        callback(postList)
    }.addOnFailureListener {
        println("Error getting documents: $it")
        callback(emptyList())
    }
}

fun UpdatePostLike(pid: Int) {
    database.collection("Post").document(pid.toString()).update("like", FieldValue.increment(1))
}

fun UpdatePostView(pid: Int) {
    database.collection("Post").document(pid.toString()).update("view", FieldValue.increment(1))
}

fun GetCommentId(pid: Int, callback: (Int) -> Unit) {
    database.collection("Comment").document(pid.toString()).get().addOnSuccessListener {
        val commentId = it
        callback(commentId.data?.get("count") as Int)
    }.addOnFailureListener {
        println("Error getting documents: $it")
    }
}

fun writeNewComment(
    comment: Comment
) {
    GetCommentId(comment.pid) { commentId ->
        comment.cid = commentId
    }
    database.collection("Comment/" + comment.pid.toString()).document(comment.cid.toString())
        .set(comment)
}

fun GetCommentAll(pid: Int, callback: (List<Comment>) -> Unit) {
    database.collection("Comment/" + pid.toString()).get().addOnSuccessListener {
        val commentList = it.toObjects(Comment::class.java)
        callback(commentList)
    }.addOnFailureListener {
        println("Error getting documents: $it")
        callback(emptyList())
    }
}

fun GetGoodsAll(callback: (List<Goods>) -> Unit) {
    database.collection("Goods").get().addOnSuccessListener {
        val goodsList = it.toObjects(Goods::class.java)
        callback(goodsList)
    }.addOnFailureListener {
        println("Error getting documents: $it")
        callback(emptyList())
    }
}