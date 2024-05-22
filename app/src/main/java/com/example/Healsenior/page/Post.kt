package com.example.Healsenior.page

data class Post(
    val title: String,
    val author: String,
    val date: String,
    val content: String,  // 게시글 본문 내용 추가
    val likes: Int,
    val comments: Int,
    val views: Int
)