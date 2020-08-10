package cl.malditosnakamas.proyectouno.util.validator

import org.junit.Test

import org.junit.Assert.*

class EmailValidatorTest {

    @Test
    fun emailValidate_whenInsertValidEmail_whenReturnTrue() {
        val email = "harttyn.arce@gmail.com"
        assertTrue(EmailValidator.validate(email))
    }

    @Test
    fun emailValidate_whenInsertInvalidEmail_whenReturnFalse() {
        val email = "harttyn.arce-gmail.com"
        assertFalse(EmailValidator.validate(email))
    }

    @Test
    fun emailValidate_whenInsertEmptyEmail_whenReturnFalse() {
        val email = ""
        assertFalse(EmailValidator.validate(email))
    }
}