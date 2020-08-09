package cl.malditosnakamas.proyectouno.util.validator

import cl.malditosnakamas.proyectouno.util.ConstantValues.EMPTY_STRING

object PhoneNumberValidator {
    const val PHONE_NUMBER_SYMBOLS_COUNT = 11
    const val NUMBER_VALUES = "[0-9]+"

    fun validatePhone(phone: String?): Boolean {
        if (phone == null) return false
        val validatePhone = phone.replace("\\s+".toRegex(), EMPTY_STRING)
        return (validatePhone.matches(NUMBER_VALUES.toRegex())
                && validatePhone.length == PHONE_NUMBER_SYMBOLS_COUNT)
    }

}