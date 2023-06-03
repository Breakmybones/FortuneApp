package com.example.feature_profile_screen_impl.presentation.fragment.utils

import java.text.SimpleDateFormat
import java.util.*

fun getZodiacSign(dateOfBirth: String): String {
    if (!dateOfBirth.contains("-")) {
        return dateOfBirth
    }
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(dateOfBirth)

    if (date != null) {
        val calendar = Calendar.getInstance()
        calendar.time = date

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1

        return when (month) {
            1 -> if (day >= 20) "Водолей" else "Козерог"
            2 -> if (day >= 19) "Рыбы" else "Водолей"
            3 -> if (day >= 21) "Овен" else "Рыбы"
            4 -> if (day >= 20) "Телец" else "Овен"
            5 -> if (day >= 21) "Близнецы" else "Телец"
            6 -> if (day >= 21) "Рак" else "Близнецы"
            7 -> if (day >= 23) "Лев" else "Рак"
            8 -> if (day >= 23) "Дева" else "Лев"
            9 -> if (day >= 23) "Весы" else "Дева"
            10 -> if (day >= 23) "Скорпион" else "Весы"
            11 -> if (day >= 22) "Стрелец" else "Скорпион"
            12 -> if (day >= 22) "Козерог" else "Стрелец"
            else -> "Неверная дата"
        }
    } else {
        return "Неверный формат даты"
    }
}
