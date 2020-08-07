package cl.malditosnakamas.proyectouno.util.formater

import cl.malditosnakamas.proyectouno.util.ConstantValues.EMPTY_STRING

object RutFormatter {
    private const val INVALID_RUT_CHARACTER_REGEX = ""
    private const val ONLY_NUMBERS_REGEX = ""

    fun displayFormat(rut: String): String {
        var original: String = rut.replace(INVALID_RUT_CHARACTER_REGEX, EMPTY_STRING)
        if (original.isEmpty()) {
            return original
        }

        if (original.length > 9) {
            original = original.substring(0, 9)
        }
        val verifier = original[original.length - 1]
        original = original.substring(0, original.length - 1)
        original = original.replace(ONLY_NUMBERS_REGEX.toRegex(), EMPTY_STRING)
        return if (original.isNotEmpty()) {
            val number: String = ThousandFormatter.format(original)
            "$number-$verifier"
        } else {
            EMPTY_STRING + verifier
        }
    }
}