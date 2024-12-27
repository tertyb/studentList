package com.idz.colman24class2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.idz.colman24class2.model.Student

class StudentFragment: Fragment() {

    private var nameEditText: EditText? = null
    private var idEditText: EditText? = null
    private var avatarUrlEditText: EditText? = null
    private var phoneEditText: EditText? = null
    private var addressEditText: EditText? = null
    private var isCheckedCheckBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    fun getStudentData(): Student {
        val name = nameEditText?.text.toString()
        val id = idEditText?.text.toString()
        val avatarUrl = avatarUrlEditText?.text.toString()
        val phone = phoneEditText?.text.toString()
        val address = addressEditText?.text.toString()
        val isChecked = isCheckedCheckBox?.isChecked ?: false

        return Student(
            name = name,
            id = id,
            avatarUrl = "",
            phone = phone,
            address = address,
            isChecked = isChecked
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.student_fragment, container, false)

        nameEditText = view.findViewById(R.id.student_fragment_name)
        idEditText = view.findViewById(R.id.student_fragment_id)
        phoneEditText = view.findViewById(R.id.student_fragment_phone)
        addressEditText = view.findViewById(R.id.student_fragment_address)
        isCheckedCheckBox = view.findViewById(R.id.student_fragment_checked)

        return view
    }


}
