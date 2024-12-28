package com.idz.colman24class2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.idz.colman24class2.model.Student

class MainActivity : AppCompatActivity() {

    var blueFragment: BlueFragment? = null

    private var fragmentOne: StudentsListFragment? = null

    private var buttonFour: Button? = null

    private var inDisplayFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fragmentOne = StudentsListFragment()
        buttonFour = findViewById(R.id.main_activity_button_four)

        buttonFour?.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivityForResult(intent, ADD_STUDENT_REQUEST_CODE)

        }

        display(fragmentOne)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val name =  data.getStringExtra("name") ?: ""
            val id = data.getStringExtra("id") ?: ""
            val avatarUrl = data.getStringExtra("avatarurl") ?: ""
            val phone = data.getStringExtra("phone") ?: ""
            val address = data.getStringExtra("address") ?: ""
            val isChecked = data.getBooleanExtra("isChecked",false) ?: false

            val newStudent = Student(
                name = name,
                id = id,
                avatarUrl = "",
                phone = phone,
                address = address,
                isChecked = isChecked
            )

            if(requestCode == ADD_STUDENT_REQUEST_CODE) {
                newStudent?.let {
                    // Add the new student to the fragment's adapter
                    (fragmentOne as? StudentsListFragment)?.addStudent(it)
                }
            }

        }
    }

    private fun display(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.main_activity_frame_layout, it)

                inDisplayFragment?.let {
                    remove(it)
                }

                addToBackStack("TAG")
                commit()
            }

            inDisplayFragment = fragment
        }
    }

    private fun removeFragment() {
        blueFragment?.let { fragment ->
            supportFragmentManager.beginTransaction().apply {
                remove(fragment)
                commit()
            }
        }
        blueFragment = null
    }

    fun addFragment() {
        blueFragment = BlueFragment.newInstance("This is my text 👨‍🎓")
        blueFragment?.let {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.main_activity_frame_layout, it)
                addToBackStack("TAG")
                commit()
            }
        }
    }

    companion object {
        private const val ADD_STUDENT_REQUEST_CODE = 1
    }


}