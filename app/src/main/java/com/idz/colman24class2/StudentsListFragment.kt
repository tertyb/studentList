package com.idz.colman24class2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
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
                startActivityForResult(intent, STUDENT_REQUEST_CODE)
            }
        }

        // Set the adapter for the RecyclerView
        recyclerView.adapter = adapter

        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val name =  data.getStringExtra("name") ?: ""
            val id = data.getStringExtra("id") ?: ""
            val avatarUrl = data.getStringExtra("avatarurl") ?: ""
            val phone = data.getStringExtra("phone") ?: ""
            val address = data.getStringExtra("address") ?: ""
            val isChecked = data.getBooleanExtra("isChecked",false)
            val previousStudentId = data.getStringExtra("previousStudentId") ?: ""
            val delete = data.getBooleanExtra("delete",false)

            val editedStudent = Student(
                name = name,
                id = id,
                avatarUrl = "",
                phone = phone,
                address = address,
                isChecked = isChecked
            )


             if (requestCode == STUDENT_REQUEST_CODE) {
                 if (delete) {
                     deleteStudent(previousStudentId)
                 } else {
                     editStudent(editedStudent,previousStudentId)
                 }
            }
        }
    }

    fun addStudent(student: Student) {
        students?.add(student)
        adapter.notifyItemInserted(students?.size?.minus(1) ?: 0) // Notify adapter to display the new student
    }

    fun editStudent(student: Student,previousStudentId:String) {
        students?.let { studentList ->
            // Find the index of the student to edit
            val index = studentList.indexOfFirst { it.id == previousStudentId }
            if (index >= 0) {

                studentList[index] = student // Update the student at the found index
                adapter.notifyItemChanged(index) // Notify the adapter about the change
            } else {
                Log.e("EditStudent", "Student not found in the list!")
            }
        } ?: Log.e("EditStudent", "Student list is null!")
    }


    fun deleteStudent(previousStudentId:String) {
        students?.let { studentList ->
            // Find the index of the student to delete
            val index = studentList.indexOfFirst { it.id == previousStudentId }
            if (index >= 0) {
                studentList.removeAt(index) // Remove the student from the list
                adapter.notifyItemRemoved(index) // Notify the adapter about the removal
            } else {
                Log.e("DeleteStudent", "Student not found in the list!")
            }
        } ?: Log.e("DeleteStudent", "Student list is null!")
    }

    companion object {
        private const val STUDENT_REQUEST_CODE = 2
    }
}
