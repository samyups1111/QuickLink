package com.example.firebasepractice.model

import com.google.firebase.database.Exclude

data class User(
    val fullName: String = "",
    val userName: String = "",
    val email: String = "",
    val photo: String? = null,
    @Exclude val uid: String = ""
)
