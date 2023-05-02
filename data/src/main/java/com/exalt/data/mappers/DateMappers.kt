package com.exalt.data.mappers

import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class DateMappers @Inject constructor(){

    fun toDate(date: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", Locale.FRANCE)
        val newFormat = SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE)
        val date = originalFormat.parse(date)
        return newFormat.format(date)
    }
}