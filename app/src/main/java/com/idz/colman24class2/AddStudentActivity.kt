package com.idz.colman24class2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.idz.colman24class2.adapter.StudentsRecyclerAdapter
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.add_student_activity_save_button)
        val cancelButton: Button = findViewById(R.id.add_student_activity_cancel_button)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StudentFragment())
                .commit()
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StudentFragment())
                .commit()
        }


        val savedMessageTextView: TextView = findViewById(R.id.add_student_activity_save_message_text_view)

        cancelButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            // Retrieve the fragment instance
            val studentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as StudentFragment
            val student = studentFragment.getStudentData()

            // Handle the student data (e.g., save it to a database or show a message)
            saveStudentData(student)
            savedMessageTextView.text = "Name: ${student.name} ID: ${student.id} is saved!!!..."
        }


    }

    private fun saveStudentData(student: Student) {
        // For now, just log the data, you can save it to a database, or show a confirmation message
        Log.d("AddStudentActivity", "Saved student data: $student")

        // Pass the student data back to the previous activity
        val resultIntent = Intent().apply {
            putExtra("name", student.name)
            putExtra("id", student.id)
            putExtra("phone", student.phone)
            putExtra("address", student.address)
            putExtra("isChecked", student.isChecked)
        }
        setResult(RESULT_OK, resultIntent)
        finish()  // Finish the activity and return to the previous one
    }




}