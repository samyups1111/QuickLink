package com.example.firebasepractice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.firebasepractice.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.logout).setOnClickListener(this)
        findViewById<Button>(R.id.proile_button).setOnClickListener(this)
        setupBottomNavigation()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.logout -> {
                mAuth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            R.id.proile_button -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_tab -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.profile_tab -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}