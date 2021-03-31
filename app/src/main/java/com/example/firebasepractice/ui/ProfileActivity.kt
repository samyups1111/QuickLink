package com.example.firebasepractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.firebasepractice.R
import com.example.firebasepractice.model.User
import com.example.firebasepractice.utils.ValueListenerAdapter
import com.example.firebasepractice.utils.asUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfileActivity : AppCompatActivity() {

    private val TAG = "ProfileActivity"
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference
    private lateinit var mUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference

        currentUserReference().addListenerForSingleValueEvent(
            ValueListenerAdapter{
                mUser = it.asUser()!!
                findViewById<TextView>(R.id.name).text = mUser.fullName
            }

        )
    }

    fun currentUserReference(): DatabaseReference =
        mDatabase.child("users").child(mAuth.currentUser.uid)
}