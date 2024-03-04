package com.example.storedetafirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class AddStudentActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    lateinit var editText_name: EditText
    lateinit var editText_email: EditText
    lateinit var editText_password: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up_pages)
        button = findViewById(R.id.button_singUp)
        editText_name = findViewById(R.id.edit_text_name)
        editText_email = findViewById(R.id.edit_text_email)
        editText_password = findViewById(R.id.edit_text_password)

        button.setOnClickListener {
            val sName = editText_name.text.toString()
            val sEmail = editText_email.text.toString()
            val sPassword = editText_password.text.toString()

            val uid = UUID.randomUUID().toString()

            val map = hashMapOf(
                "id" to uid,
                "name" to sName,
                "email" to sEmail,
                "password" to sPassword
            )
            db.collection("users").document(uid).set(map)
                .addOnSuccessListener {
                    Toast.makeText(this, "user Added", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                }

        }
    }


}
