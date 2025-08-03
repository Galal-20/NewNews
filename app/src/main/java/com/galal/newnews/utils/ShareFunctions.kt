package com.galal.newnews.utils

import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class ShareFunctions {

    companion object{
         fun getTimeAgo(dateString: String?): String {
            return try {
                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                format.timeZone = TimeZone.getTimeZone("UTC")
                val date = format.parse(dateString ?: "") ?: return "Unknown"
                val prettyTime = PrettyTime(Locale.getDefault())
                prettyTime.format(date)
            } catch (e: Exception) {
                "Unknown"
            }
        }
    }
}