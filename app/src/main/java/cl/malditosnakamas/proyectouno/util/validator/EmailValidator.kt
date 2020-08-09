package cl.malditosnakamas.proyectouno.util.validator

import java.util.regex.Matcher
import java.util.regex.Pattern

object EmailValidator {
    private const val regex =
        "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"

    fun validate(email: String?): Boolean {
        if (email == null || email.length <= 0) return false
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }
}