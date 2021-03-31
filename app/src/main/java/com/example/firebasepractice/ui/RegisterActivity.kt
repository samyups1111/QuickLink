package com.example.firebasepractice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.firebasepractice.R
import com.example.firebasepractice.model.User
import com.example.firebasepractice.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "RegisterActivity"
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        findViewById<Button>(R.id.register).setOnClickListener(this)
    }

    private fun onRegister() {
        val fullName = findViewById<EditText>(R.id.enter_fullname).text.toString()
        val email = findViewById<EditText>(R.id.enter_email).text.toString()
        val password = findViewById<EditText>(R.id.create_password).text.toString()
        val password2 = findViewById<EditText>(R.id.confirm_password).text.toString()

        if (fullName.isEmpty() || email.isEmpty() || "@" !in email || password.isEmpty() || password2.isEmpty()) {
            showToast("All fields are required")
        } else {
            when {
                password != password2 -> {
                    showToast("Passwords must match")
                }
                password.length < 6 -> {
                    showToast("Password must be at least 6 characters long")
                }
                else -> {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { it ->
                                val user = createUser(fullName, email)
                                val reference = mDatabase.child("users").child(it.result?.user!!.uid)
                                reference.setValue(user)
                                        .addOnCompleteListener {
                                            if (it.isSuccessful) {
                                                showToast("Registration was successful")
                                                startActivity(Intent(this, MainActivity::class.java))
                                                finish()
                                            } else {
                                                showToast("Something went wrong, please try again later")
                                            }
                                        }
                            }
                }
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.register -> {
                onRegister()
            }
        }
    }

    private fun createUser(fullName: String, email: String): User {
        val username = createUserName(fullName)
        return User(fullName, username, email )
    }

    private fun createUserName(userName: String) =
        userName.toLowerCase().replace(" ", ".")


}