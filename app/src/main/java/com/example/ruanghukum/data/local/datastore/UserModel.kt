package com.example.ruanghukum.data.local.datastore

data class UserModel (
    val email: String,
    val name: String,
    val picture: String,
    val address: String,
    val phoneNumber: String,
    val gender: String,
    val jobTitle: String,
    val idCardNumber: String,
    val birthDate: String,
    val token: String,
)