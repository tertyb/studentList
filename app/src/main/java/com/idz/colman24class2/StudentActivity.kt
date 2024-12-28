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
import com.idz.colman24class2.model.Student

class StudentActivity : AppCompatActivity() {

    private var editButton: Button? = null
    private var backButton: Button? = null
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
        backButton = findViewById(R.id.go_back_button)

        backButton?.setOnClickListener {
            finish()
        }

        editButton?.setOnClickListener {
            // Create an intent to launch EditStudentActivity and pass the data
            val intent = Intent(this, EditStudentActivity::class.java).apply {
                putExtra("STUDENT_NAME", studentName)
                putExtra("STUDENT_ID", studentId)
                putExtra("STUDENT_PHONE", studentPhone)
                putExtra("STUDENT_ADDRESS", studentAddress)
                putExtra("STUDENT_CHECKED", studentChecked)
            }
            startActivityForResult(intent, EDIT_STUDENT_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_STUDENT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val name =  data.getStringExtra("name") ?: ""
            val id = data.getStringExtra("id") ?: ""
            val avatarUrl = data.getStringExtra("avatarurl") ?: ""
            val phone = data.getStringExtra("phone") ?: ""
            val address = data.getStringExtra("address") ?: ""
            val isChecked = data.getBooleanExtra("isChecked",false)
            val previousStudentId = data.getStringExtra("previousStudentId") ?: ""
            val delete = data.getBooleanExtra("delete",false)

            val resultIntent = Intent().apply {
                putExtra("name", name)
                putExtra("id", id)
                putExtra("phone", phone)
                putExtra("address", address)
                putExtra("isChecked", isChecked)
                putExtra("previousStudentId", previousStudentId)
                putExtra("delete", delete)
            }
            setResult(RESULT_OK, resultIntent)
            finish()  // Finish the activity and return to the previous one
        }
    }

    companion object {
        private const val EDIT_STUDENT_REQUEST_CODE = 2
    }
}
