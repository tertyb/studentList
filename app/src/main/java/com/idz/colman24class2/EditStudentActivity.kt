package com.idz.colman24class2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.edit_student_activity_save_button)
        val cancelButton: Button = findViewById(R.id.edit_student_activity_cancel_button)
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


//        val nameEditText: EditText = findViewById(R.id.add_student_activity_name_edit_text)
//        val idEditText: EditText = findViewById(R.id.add_student_activity_id_edit_text)

        val savedMessageTextView: TextView = findViewById(R.id.edit_student_activity_save_message_text_view)

        cancelButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
//            savedMessageTextView.text = "Name: ${nameEditText.text} ID: ${idEditText.text} is saved!!!..."
        }
    }
}