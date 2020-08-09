package cl.malditosnakamas.proyectouno.util.validator

object NumberValidator {
    fun validateNumeric(password: String): Boolean {
        return password.matches("\\d{4}".toRegex())
    }
}