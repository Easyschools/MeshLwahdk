package com.developnetwork.meshlwahdk.utils



import android.content.Context
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSpinner
import com.developnetwork.meshlwahdk.R
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Matcher
import java.util.regex.Pattern

fun emailValidator(view: TextInputLayout, context: Context): Boolean {
    val regex =
        "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
    val result = regex.toRegex().matches(getTextInoutLayoutText(view))
    if (!result)
        setTextInoutLayoutError(view, R.string.email_field_validation, context)
    return result
}

fun emailValidator(view: EditText, context: Context): Boolean {
    val regex =
        "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
    val result = regex.toRegex().matches(view.text)
    if (!result)
        setTextInoutLayoutError(view, R.string.email_field_validation, context)
    return result
}

fun passwordValidator(view: TextInputLayout, context: Context): Boolean {
    if (getTextInoutLayoutText(view).length < 8 || getTextInoutLayoutText(view).length > 20) {
        return setTextInoutLayoutError(view, R.string.password_leanth, context)
    }
//    val regex = "^[a-zA-Z0-9_.-]*\$"
    val regex = "^(?=.*[a-zA-Z\\d].*)[a-zA-Z\\d!@#\$%&*]{7,}\$"
    val result = regex.toRegex().matches(getTextInoutLayoutText(view))
    if (!result) {
        setTextInoutLayoutError(view, R.string.invalid_password, context)
    }
    return result
}

fun passwordValidator(view: EditText, context: Context): Boolean {
    if (view.text.toString().length < 8 || view.text.toString().length > 20) {
        return setTextInoutLayoutError(view, R.string.password_leanth, context)
    }
//    val regex = "^[a-zA-Z0-9_.-]*\$"
    val regex = "^(?=.*[a-zA-Z\\d].*)[a-zA-Z\\d!@#\$%&*]{7,}\$"
    val result = regex.toRegex().matches(view.text.toString())
    if (!result) {
        setTextInoutLayoutError(view, R.string.invalid_password, context)
    }
    return result
}

fun confirmPasswordValidator(view: TextInputLayout, pass: String, context: Context): Boolean {
    if (pass != getTextInoutLayoutText(view)) {
        return setTextInoutLayoutError(view, R.string.confirm_pass_validation, context)
    }
    return true
}

fun confirmPasswordValidator(view: EditText, pass: String, context: Context): Boolean {
    if (pass != view.text.toString()) {
        return setTextInoutLayoutError(view, R.string.confirm_pass_validation, context)
    }
    return true
}

fun nameValidator(view: TextInputLayout, context: Context): Boolean {
    if (getTextInoutLayoutText(view).length > 15 || getTextInoutLayoutText(view).length < 3) {
        return setTextInoutLayoutError(view, R.string.name_length_valdation, context)
    }

//    if (!getTextInoutLayoutText(view).matches("^[a-zA-Z]*$".toRegex()))
    if (!getTextInoutLayoutText(view).matches("^[a-zA-Z\\u0621-\\u064A ]*$".toRegex()))
        return setTextInoutLayoutError(view, R.string.characters_only, context)

    return true
}

fun userNameValidator(view: TextInputLayout, context: Context): Boolean {
    val userName= getTextInoutLayoutText(view)
    val pattern = Pattern.compile("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){2,18}[a-zA-Z0-9]\$")
    val matcher: Matcher = pattern.matcher(userName)
    if(!matcher.matches()){
        return setTextInoutLayoutError(view, R.string.user_name_valdation, context)
    }
    return true
}

fun nameValidator(view: EditText, context: Context): Boolean {
    if (view.text.length > 30 || view.text.length < 2) {
        return setTextInoutLayoutError(view, R.string.name_length_valdation, context)
    }
    return true
}

fun phoneValidator(view: TextInputLayout, context: Context): Boolean {
    if (getTextInoutLayoutText(view).length > 15 || getTextInoutLayoutText(view).length < 8) {
        return setTextInoutLayoutError(view, R.string.phone_length_msg, context)
    }
    if (!android.util.Patterns.PHONE.matcher(getTextInoutLayoutText(view)).matches())
        return setTextInoutLayoutError(view, R.string.phone_must_be_numbers_msg, context)
    return true
}

fun phoneValidator(view: EditText, context: Context): Boolean {
    if (view.text.length > 15 || view.text.length < 8) {
        return setTextInoutLayoutError(view, R.string.phone_length_msg, context)
    }
    if (!android.util.Patterns.PHONE.matcher(view.text).matches())
        return setTextInoutLayoutError(view, R.string.phone_must_be_numbers_msg, context)
    return true
}

fun spinnerValidator(spinner: AppCompatSpinner, context: Context): Boolean {
    if (spinner.selectedItemId.toInt() == 0) {
        (spinner.selectedView as TextView).error = context.getString(R.string.must_select_an_item)
        return false
    }
    return true
}

fun lengthValidator(view: TextInputLayout, minLength: Int, context: Context): Boolean {
    if (getTextInoutLayoutText(view).length < minLength) {
        return setTextInoutLayoutError(view, R.string.text_length_msg, context)
    }

    return true
}
fun lengthValidator(view: EditText, minLength: Int, context: Context): Boolean {
    if (view.text.length < minLength) {
        return setTextInoutLayoutError(view, R.string.text_length_msg, context)
    }

    return true
}

private fun setTextInoutLayoutError(
    view: TextInputLayout,
    msgResID: Int,
    context: Context
): Boolean {
    view.error = context.getString(msgResID)
    return false
}

private fun setTextInoutLayoutError(view: EditText, msgResID: Int, context: Context): Boolean {
    view.error = context.getString(msgResID)
    return false
}

private fun getTextInoutLayoutText(view: TextInputLayout): String {
    view.error = ""
    return view.editText?.text.toString()
}