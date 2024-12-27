package com.idz.colman24class2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StudentActivity : AppCompatActivity() {

    private var editButton: Button? = null
    private var studentName: String? = null
    private var studentId: String? = null
    private var studentPhone: String? = null
    private var studentAddress: String? = null
    private var studentChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        studentName = intent.getStringExtra("STUDENT_NAME")
        studentId = intent.getStringExtra("STUDENT_ID")
        studentPhone = intent.getStringExtra("STUDENT_PHONE")
        studentAddress = intent.getStringExtra("STUDENT_ADDRESS")
        studentChecked = intent.getBooleanExtra("STUDENT_CHECKED", false)

        val nameTextView: TextView = findViewById(R.id.studentName)
        val idTextView: TextView = findViewById(R.id.studentId)
        val phoneTextView: TextView = findViewById(R.id.studentPhone)
        val addressTextView: TextView = findViewById(R.id.studentAdress)
        val checkedView: CheckBox = findViewById(R.id.studentChecked)

        // Set the text of the TextViews to the received data
        nameTextView.text = studentName ?: "No Name"
        idTextView.text = studentId.toString()
        phoneTextView.text = studentPhone ?: "No Phone"
        addressTextView.text = studentAddress ?: "No Address"
        checkedView.isChecked = studentChecked

        editButton = findViewById(R.id.edit_button)

        editButton?.setOnClickListener {
            // Create an intent to launch EditStudentActivity and pass the data
            val intent = Intent(this, EditStudentActivity::class.java).apply {
                putExtra("STUDENT_NAME", studentName)
                putExtra("STUDENT_ID", studentId)
                putExtra("STUDENT_PHONE", studentPhone)
                putExtra("STUDENT_ADDRESS", studentAddress)
                putExtra("STUDENT_CHECKED", studentChecked)
            }
            startActivity(intent)
        }
    }
}
