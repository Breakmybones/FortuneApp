package com.example.featureregistrationimpl.presentation

import android.content.Context
import android.widget.Toast


fun registerUser(email: String?, password: String?, password2: String?, username: String?, date_of_birth: String?, context: Context): Boolean {

    val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")

    if (email.isNullOrEmpty() && password.isNullOrEmpty() && username.isNullOrEmpty() && date_of_birth.isNullOrEmpty() && email.isNullOrEmpty()) {
        Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
        return false
    }

    if (!emailRegex.matches(email!!)) {
        Toast.makeText(context, "Введите правильную почту", Toast.LENGTH_SHORT).show()
        return false
    }

    if (password != password2) {
        Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
        return false
    }

    else {
        return true
    }
}