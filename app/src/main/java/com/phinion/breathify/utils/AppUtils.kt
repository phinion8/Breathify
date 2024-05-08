package com.phinion.breathify.utils

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object AppUtils {
    fun validateName(name: String): Boolean {
        // Check if the name is not empty
        if (name.isEmpty()) {
            return false
        }

        // Check if the name contains only letters and spaces
        val regex = Regex("^[a-zA-Z ]+\$")
        return regex.matches(name)
    }
}

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}