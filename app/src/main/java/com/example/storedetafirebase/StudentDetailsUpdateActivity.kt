package com.example.storedetafirebase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class StudentDetailsUpdateActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    lateinit var editText_name: EditText
    lateinit var editText_email: EditText
    lateinit var button: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details_update)

        val id = intent.extras?.getString("key_id")
        val name = intent.extras?.getString("key_name")
        val email = intent.extras?.getString("key_email")

        button = findViewById(R.id.button_singUp)
        editText_name = findViewById(R.id.edit_text_name)
        editText_email = findViewById(R.id.edit_text_email)

        editText_name.setText(name)
        editText_email.setText(email)

        button.setOnClickListener {
            val sName = editText_name.text.toString()
            val sEmail = editText_email.text.toString()



            val map = mapOf(
                "name" to sName,
                "email" to sEmail,
            )
            db.collection("users").document("$id").update(map)
                .addOnSuccessListener {
                    Toast.makeText(this, "user updated", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                }

        }
    }


}