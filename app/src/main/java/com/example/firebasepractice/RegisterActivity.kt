package com.example.firebasepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "RegisterActivity"
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.register).setOnClickListener(this)
    }

    private fun onRegister() {
        val fullName = findViewById<EditText>(R.id.enter_fullname).text.toString()
        val email = findViewById<EditText>(R.id.enter_email).text.toString()
        val password = findViewById<EditText>(R.id.create_password).text.toString()
        val password2 = findViewById<EditText>(R.id.confirm_password).text.toString()

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            showToast("All fields are required")
        } else {
            if (password != password2) {
                showToast("Passwords must match")
            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
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

    override fun onClick(view: View) {
        when (view.id) {
            R.id.register -> {
                onRegister()
            }
        }
    }
}