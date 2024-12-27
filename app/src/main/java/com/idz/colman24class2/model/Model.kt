package com.idz.colman24class2.model

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {
        for (i in 0..3) {
            val student = Student(
                name = "Name $i",
                id = "Student ID: $i",
                avatarUrl = "",
                phone = "05235675",
                address = "hashomonai 2 tel aviv",
                isChecked = true
            )
            students.add(student)
        }
    }
}