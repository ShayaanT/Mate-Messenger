package com.example.messagingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {

    private lateinit var etname: EditText
    private lateinit var etemail: EditText
    private lateinit var etpassword: EditText
    private lateinit var btnsignup: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        etname = findViewById(R.id.etname)
        etemail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        btnsignup = findViewById(R.id.btnsignup)

        btnsignup.setOnClickListener {
            val email = etemail.text.toString()
            val password = etpassword.text.toString()

            signUp(email, password)
        }

    }

    private fun signUp(email: String, password: String){
        //logic of creating the user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code for getting to home
                    val intent = Intent(this@Signup, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Signup, "An error occurred", Toast.LENGTH_SHORT).show()

                }
            }
    }
}
