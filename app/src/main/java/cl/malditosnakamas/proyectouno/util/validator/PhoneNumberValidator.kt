package cl.malditosnakamas.proyectouno.util.validator

import cl.malditosnakamas.proyectouno.util.ConstantValues.EMPTY_STRING
import cl.malditosnakamas.proyectouno.util.ConstantValues.PLUS_VALUE
import cl.malditosnakamas.proyectouno.util.ConstantValues.SPACE_STRING

object PhoneNumberValidator {
    private const val NUMBER_VALUES = "[0-9]+"
    private const val MIN_LENGTH_NUMBER = 8
    private const val MAX_LENGTH_NUMBER = 11

    fun validatePhone(phone: String): Boolean {
        val validatePhone =
            phone.replace(PLUS_VALUE, EMPTY_STRING).replace(SPACE_STRING, EMPTY_STRING)
        return (validatePhone.isNotEmpty()
                && validatePhone.matches(NUMBER_VALUES.toRegex())
                && validatePhoneLength(validatePhone))
    }

    private fun validatePhoneLength(validatePhone: String): Boolean {
        return validatePhone.length in (MIN_LENGTH_NUMBER)..MAX_LENGTH_NUMBER
    }
}