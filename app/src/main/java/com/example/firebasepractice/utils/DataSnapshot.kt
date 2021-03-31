package com.example.firebasepractice.utils

import com.example.firebasepractice.model.User
import com.google.firebase.database.DataSnapshot

fun DataSnapshot.asUser(): User? =
    getValue(User::class.java)?.copy(uid = key!!)