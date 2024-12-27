package com.idz.colman24class2.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idz.colman24class2.OnItemClickListener
import com.idz.colman24class2.R
import com.idz.colman24class2.model.Student

class StudentsRecyclerAdapter(private val students: List<Student>?): RecyclerView.Adapter<StudentViewHolder>() {

    var listener = object : OnItemClickListener {
        override fun onItemClick(position: Int) {
            Log.d("TAG", "On click Activity listener on position $position")
        }

        override fun onItemClick(student: Student?) {
            Log.d("TAG", "On student clicked name: ${student?.name}")
        }
    }

        override fun getItemCount(): Int = students?.size ?: 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val inflation = LayoutInflater.from(parent.context)
            val view = inflation.inflate(R.layout.student_list_row, parent, false)
            return StudentViewHolder(view, listener)
        }

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            holder.bind(students?.get(position), position)
        }


    }