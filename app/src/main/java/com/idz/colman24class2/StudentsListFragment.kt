package com.idz.colman24class2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idz.colman24class2.adapter.StudentsRecyclerAdapter
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

class StudentsListFragment : Fragment() {

    private var students: MutableList<Student>? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_students_list, container, false)

        // Initialize students list
        students = Model.shared.students ?: mutableListOf()

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.students_list_activity_recycler_view)
        recyclerView.setHasFixedSize(true)

        // Set up the layout manager
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        // Initialize adapter
        adapter = StudentsRecyclerAdapter(students)

        // Set up click listener for adapter
        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG", "On click Activity listener on position $position")
            }

            override fun onItemClick(student: Student?) {
                Log.d("TAG", "On student clicked name: ${student?.name}")

                val intent = Intent(requireContext(), StudentActivity::class.java).apply {
                    putExtra("STUDENT_NAME", student?.name)
                    putExtra("STUDENT_ID", student?.id)
                    putExtra("STUDENT_PHONE", student?.phone)
                    putExtra("STUDENT_ADDRESS", student?.address)
                    putExtra("STUDENT_CHECKED", student?.isChecked)
                }
                startActivity(intent)
            }
        }

        // Set the adapter for the RecyclerView
        recyclerView.adapter = adapter

        return view
    }

    fun addStudent(student: Student) {
        students?.add(student)
        adapter.notifyItemInserted(students?.size?.minus(1) ?: 0) // Notify adapter to display the new student
    }
}
