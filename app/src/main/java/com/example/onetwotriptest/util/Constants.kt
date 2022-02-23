package com.example.onetwotriptest.util

class Constants {
    companion object {
        const val BASE_URL = "https://603e34c648171b0017b2ec55.mockapi.io/ott/"

        fun handleAirportName(airportCode: String) : String{
            return when (airportCode) {
                "SVO" -> "Москва (Международный аэропорт Шереметьево)"
                "HND" -> "Токио (Международный аэропорт Токио)"
                "NRT" -> "Токио (Международный аэропорт Нарита)"
                "EWR" -> "Ньюарк (Международный аэропорт Ньюарк Либерти)"
                "DME" -> "Москва (Международный аэропорт Домодедово)"
                "DOH" -> "Доха (Международный аэропорт Доха)"
                "JFK" -> "Нью-йорк (Международный аэропорт имени Дж. Кеннеди)"
                "LHR" -> "Лондон (Аэропорт Хитроу)"
                "FRA" -> "Франкфурт-на-майне (Аэропорт Франкфурт-на-Майне)"
                else -> airportCode
            }

        }
    }
}