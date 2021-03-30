package com.example.firebasepractice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.firebasepractice.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.logout).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.logout -> {
                mAuth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}