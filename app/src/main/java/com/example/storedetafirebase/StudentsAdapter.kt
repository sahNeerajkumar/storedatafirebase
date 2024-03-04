package com.example.storedetafirebase

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class StudentsAdapter (val studentModelList:List<StudentModel>, private val context: Context, val  onStudentClickListener: SetOnStudentClickListener): BaseAdapter(){
    override fun getCount(): Int {
        return studentModelList.size
    }

    override fun getItem(position: Int): Any {
        return studentModelList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val  view = LayoutInflater.from(context).inflate(R.layout.item_deta,parent,false)

        val  names = view.findViewById<TextView>(R.id.name_text)
        val  email = view.findViewById<TextView>(R.id.email_text)
        val  password = view.findViewById<TextView>(R.id.password_text)
        val  delete = view.findViewById<ImageView>(R.id.delete_icon)
        val  update = view.findViewById<ImageView>(R.id.edit_icon)

        names.text= studentModelList[position].name
       email.text= studentModelList[position].email
        password.text= studentModelList[position].password.toString()

        delete.setOnClickListener {
            onStudentClickListener.onDeleteClick(studentModelList[position])
        }

        update.setOnClickListener {
            onStudentClickListener.onUpdateClick(studentModelList[position])
        }

        return view
    }

}

interface SetOnStudentClickListener{
    fun onUpdateClick(student:StudentModel)
    fun onDeleteClick(student: StudentModel)
}