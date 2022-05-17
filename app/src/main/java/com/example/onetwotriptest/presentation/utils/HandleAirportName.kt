package com.example.onetwotriptest.presentation.utils

object HandleAirportName {

    fun handleAirportName(airportCode: String): String {
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

fun String.handleAirportName(): String {
    return when (this) {
        "SVO" -> "Москва (Международный аэропорт Шереметьево)"
        "HND" -> "Токио (Международный аэропорт Токио)"
        "NRT" -> "Токио (Международный аэропорт Нарита)"
        "EWR" -> "Ньюарк (Международный аэропорт Ньюарк Либерти)"
        "DME" -> "Москва (Международный аэропорт Домодедово)"
        "DOH" -> "Доха (Международный аэропорт Доха)"
        "JFK" -> "Нью-йорк (Международный аэропорт имени Дж. Кеннеди)"
        "LHR" -> "Лондон (Аэропорт Хитроу)"
        "FRA" -> "Франкфурт-на-майне (Аэропорт Франкфурт-на-Майне)"
        else -> this
    }
}