package com.idz.colman24class2.model

data class Student(
    val name: String,
    val id: String,
    val avatarUrl: String,
    val phone: String,
    val address: String,
    var isChecked: Boolean
)
