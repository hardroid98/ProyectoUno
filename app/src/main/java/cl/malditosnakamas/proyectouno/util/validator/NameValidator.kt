package cl.malditosnakamas.proyectouno.util.validator

import cl.malditosnakamas.proyectouno.util.validator.NumberValidator.validateNumeric

object NameValidator {

    fun validate(name: String): Boolean {
        return validateNumeric(name)
    }

    fun validate(first: String, second: String): Boolean {
        return first == second && validateNumeric(first)
    }
}