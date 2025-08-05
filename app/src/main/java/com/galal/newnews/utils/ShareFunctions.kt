package com.galal.newnews.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
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

        fun showCustomSnackbar(
            view: View,
            message: String,
            backgroundColor: Int,
            textColor: Int
        ) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            val snackbarView = snackbar.view

            snackbar.setBackgroundTint(backgroundColor)

            val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(textColor)

            snackbar.show()
        }

       /* fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }*/
    }
}