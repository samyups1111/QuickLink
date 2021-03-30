package com.example.firebasepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "LoginActivity"

    private lateinit var mAuth : FirebaseAuth
    private lateinit var loginButton :Button
    private lateinit var username: EditText
    private lateinit var userPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.login_button)
        username = findViewById(R.id.enter_username)
        userPassword = findViewById(R.id.enter_password)

        mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        loginButton.setOnClickListener(this)
        findViewById<TextView>(R.id.forgot_password).setOnClickListener(this)
        findViewById<TextView>(R.id.create_account).setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.login_button -> {
                val email = username.text.toString()
                val password = userPassword.text.toString()
                if (validate(email, password)) {
                    mAuth
                        .signInWithEmailAndPassword(email, password).addOnCompleteListener {
                            if (it.isSuccessful) {
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            } else {
                                showToast("You must have entered the wrong email or password")
                            }
                        }
                } else {
                    showToast("Please enter email and password")
                }
            }
            R.id.create_account -> {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
            R.id.forgot_password -> {
                showToast("Forgot Password Clicked")
            }
        }
    }

    private fun validate(email: String, password: String) =
        email.isNotEmpty() && password.isNotEmpty()
}