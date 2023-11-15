package com.sngular.flixitApp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Filterable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun ViewGroup.inflate(
    layoutId: Int,
    attachToRoot: Boolean = false
): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

inline fun <reified T : Any> Fragment.extraNotNull(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}

fun RecyclerView.Adapter<RecyclerView.ViewHolder>.filter(filter: String) {
    this.takeIf { it is Filterable }?.run {
        ((this as Filterable).filter).filter(filter)
    }
}

fun isValidEmail(email: String): Boolean {
    val pattern = Pattern
        .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")

    val matcher: Matcher = pattern.matcher(email)
    return matcher.matches()
}

fun isMobileNumber(data: String): Boolean {

    val patterns = (
        "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" +
            "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" +
            "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$"
        )

    val pattern = Pattern
        .compile(patterns)

    val matcher: Matcher = pattern.matcher(data)
    return matcher.matches()
}

fun Context.openSettings() {
    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", packageName, null)
    }.also {
        startActivity(it)
    }
}

fun String?.valueToInt(): Int {
    return try {
        val str = this ?: ""
        str.trim().toInt()
    } catch (e: Exception) {
        0
    }
}

fun String?.valueToDouble(): Double {
    return try {
        val str = this ?: ""
        str.trim().toDouble()
    } catch (e: Exception) {
        0.0
    }
}

fun String?.floatdToDouble(): Double {

    return try {
        val format: NumberFormat = NumberFormat.getInstance(Locale.getDefault())
        val number: Number = format.parse(this)
        number.toDouble()
    } catch (e: Exception) {
        0.0
    }
}
