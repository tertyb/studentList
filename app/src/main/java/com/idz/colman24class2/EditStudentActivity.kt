package com.idz.colman24class2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.idz.colman24class2.model.Student

class EditStudentActivity : AppCompatActivity() {

    private var previousStudentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.edit_student_activity_save_button)
        val cancelButton: Button = findViewById(R.id.edit_student_activity_cancel_button)
        val deleteButton: Button = findViewById(R.id.edit_student_activity_delete_button)


        // Get the intent parameters
        val studentName = intent.getStringExtra("STUDENT_NAME")
        val studentId = intent.getStringExtra("STUDENT_ID")
        previousStudentId = studentId
        val studentPhone = intent.getStringExtra("STUDENT_PHONE")
        val studentAddress = intent.getStringExtra("STUDENT_ADDRESS")
        val studentChecked = intent.getBooleanExtra("STUDENT_CHECKED", false)

        // Bundle the parameters to pass to the fragment
        val bundle = Bundle().apply {
            putString("STUDENT_NAME", studentName)
            putString("STUDENT_ID", studentId)
            putString("STUDENT_PHONE", studentPhone)
            putString("STUDENT_ADDRESS", studentAddress)
            putBoolean("STUDENT_CHECKED", studentChecked)
        }

        // If savedInstanceState is null, replace fragment with arguments
        if (savedInstanceState == null) {
            val studentFragment = StudentFragment().apply {
                arguments = bundle
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, studentFragment)
                .commit()
        }

        val savedMessageTextView: TextView = findViewById(R.id.edit_student_activity_save_message_text_view)

        cancelButton.setOnClickListener {
            finish() // Close the activity when cancel button is clicked
        }

        deleteButton.setOnClickListener {
            deleteStudent()
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
        Log.d("EditStudentActivity", "Saved student data: $student")

        // Pass the student data back to the previous activity
        val resultIntent = Intent().apply {
            putExtra("name", student.name)
            putExtra("id", student.id)
            putExtra("phone", student.phone)
            putExtra("address", student.address)
            putExtra("isChecked", student.isChecked)
            putExtra("previousStudentId", previousStudentId)
        }
        setResult(RESULT_OK, resultIntent)
        finish()  // Finish the activity and return to the previous one
    }

    private fun deleteStudent() {

        val resultIntent = Intent().apply {
            putExtra("previousStudentId", previousStudentId)
            putExtra("delete", true)
        }
        setResult(RESULT_OK, resultIntent)
        finish()  // Finish the activity and return to the previous one
    }
}
