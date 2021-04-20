package com.example.getfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var et_username : EditText
    lateinit var et_password: EditText
    lateinit var btn_register: Button
    lateinit var btn_login: Button
    private val TAG = "CreateAccountActivity"
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_register = findViewById(R.id.btn_login_register)
        btn_login = findViewById(R.id.btn_login_submit)
        et_username = findViewById(R.id.et_login_usernameku)
        btn_register.setOnClickListener {
            var pindah = Intent(this, RegisterActivity::class.java)
            startActivity(pindah)
        }
        btn_login.setOnClickListener {
            if (et_username.text.toString()== ""){
                Toast.makeText(
                    this, "Mohon Mengisi Username",
                    Toast.LENGTH_SHORT
                ).show()        et_password = findViewById(R.id.et_login_passwordku)
                firebaseAuth = FirebaseAuth.getInstance()

            } else if (et_password.text.toString()==""){
                Toast.makeText(
                    this, "Mohon Mengisi Password",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                loginme(et_username.text.toString(), et_password.text.toString())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        checkme()
    }

    fun loginme(username: String, passwoord: String){
        firebaseAuth.signInWithEmailAndPassword(username, passwoord)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val pindah = Intent(this, MainActivity::class.java)
                    startActivity(pindah)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun checkme(){
        val currentUser = firebaseAuth!!.currentUser
        if (currentUser != null){
            val pindah = Intent(this, MainActivity::class.java)
            startActivity(pindah)
        }
    }
}