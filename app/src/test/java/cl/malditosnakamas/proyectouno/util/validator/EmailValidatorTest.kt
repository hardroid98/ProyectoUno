package cl.malditosnakamas.proyectouno.util.validator

import org.junit.Test

import org.junit.Assert.*

class EmailValidatorTest {

    @Test
    fun validarEmail_cuandoSeInsertaUnMailValido_entoncesRetornaTrue() {
        val email = "harttyn.arce@gmail.com"
        assertTrue(EmailValidator.validate(email))
    }

    @Test
    fun validarEmail_cuandoSeInsertaUnEmailInvalido_entoncesRetornaFalse() {
        val email = "harttyn.arce-gmail.com"
        assertFalse(EmailValidator.validate(email))
    }

    @Test
    fun validarEmail_cuandoSeInsertaUnEmailVacio_entoncesRetornaFalse() {
        val email = ""
        assertFalse(EmailValidator.validate(email))
    }
}