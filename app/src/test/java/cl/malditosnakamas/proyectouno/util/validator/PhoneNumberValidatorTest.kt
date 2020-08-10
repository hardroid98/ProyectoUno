package cl.malditosnakamas.proyectouno.util.validator

import org.junit.Test

import org.junit.Assert.*

class PhoneNumberValidatorTest {

    @Test
    fun validarNumero_cuandoIngresamosUnNumeroValido_entoncesRetornaTrue() {
        val numeroCelu = "+56945356708"
        assertTrue(PhoneNumberValidator.validatePhone(numeroCelu))
    }

    @Test
    fun validarNumero_cuandoIngresamosUnFormatoValido_entoncesRetornaTrue() {
        val numeroCelu = "+569 453 56 708"
        assertTrue(PhoneNumberValidator.validatePhone(numeroCelu))
    }

    @Test
    fun validarNumero_cuandoIngresamosUnNumeroInvalido_entoncesRetornaFalse() {
        val numeroCelu = "+569453c56808"
        assertFalse(PhoneNumberValidator.validatePhone(numeroCelu))
    }

    @Test
    fun validarLargoMinimo_cuandoIngresamosUnNumeroMenorAOchoCaracteres_entoncesRetornaFalse() {
        val numeroCelu = "5356808"
        assertFalse(PhoneNumberValidator.validatePhone(numeroCelu))
    }

    @Test
    fun validarLargoMaximo_cuandoIngresamosUnNumeroMayorADoceCaracteres_entoncesRetornaFalse() {
        val numeroCelu = "+56922225356808"
        assertFalse(PhoneNumberValidator.validatePhone(numeroCelu))
    }


}