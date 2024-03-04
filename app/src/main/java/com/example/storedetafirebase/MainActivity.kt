package com.example.storedetafirebase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity(), SetOnStudentClickListener {
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var listView: ListView
    private lateinit var userStudentsAdapter: StudentsAdapter

    val db = FirebaseFirestore.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton = findViewById(R.id.floatingActionButton)
        listView = findViewById(R.id.recycleView)
        val userArray = ArrayList<StudentModel>()

        userStudentsAdapter = StudentsAdapter(userArray, this, this)

        listView.adapter = userStudentsAdapter

        showStudents()
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }

    }



    fun showStudents(){
        db.collection("users").get()
            .addOnSuccessListener {
                val  data = it.toObjects(StudentModel::class.java)
                userStudentsAdapter = StudentsAdapter(data, this, this)

                listView.adapter = userStudentsAdapter
                Toast.makeText(this, "user Added", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "failde", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onUpdateClick(student: StudentModel) {
         val  intent = Intent(this, StudentDetailsUpdateActivity::class.java)
        intent.putExtra("key_id", student.id.toString())
        intent.putExtra("key_name", student.name.toString())
        intent.putExtra("key_email", student.email.toString())
        startActivity(intent)
    }

    override fun onDeleteClick(student: StudentModel) {
        deleteStudent(student.id.toString())
    }

    fun deleteStudent(id:String){
        db.collection("users").document(id).delete()
            .addOnSuccessListener {
                Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()

                showStudents()
            }
            .addOnFailureListener {
                Toast.makeText(this, "deletion failed", Toast.LENGTH_SHORT).show()

            }
    }

}