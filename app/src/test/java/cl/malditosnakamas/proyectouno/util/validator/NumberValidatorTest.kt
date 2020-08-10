package cl.malditosnakamas.proyectouno.util.validator

import org.junit.Test

import org.junit.Assert.*

class NumberValidatorTest {

    @Test
    fun validarNumero_cuandoIngresaNumeroCorrecto_entoncesRetornaTrue() {
        val number = "1"
        assertTrue(NumberValidator.validate(number))
    }

    @Test
    fun validarNumero_cuandoIngresaLetra_entoncesRetornaFalse() {
        val number = "a"
        assertFalse(NumberValidator.validate(number))
    }

    @Test
    fun validarNumero_cuandoIngresaNumeroConLetra_entoncesRetornaFalse() {
        val number = "13a4343"
        assertFalse(NumberValidator.validate(number))
    }

    @Test
    fun validarNumero_cuandoIngresaUnEspacio_entoncesRetornaFalse() {
        val number = " "
        assertFalse(NumberValidator.validate(number))
    }

    @Test
    fun validarNumero_cuandoIngresaNumnerosConEspacios_entoncesRetornaFalse() {
        val number = "123 1231"
        assertFalse(NumberValidator.validate(number))
    }

    @Test
    fun validarNumero_cuandoIngresaValorVacio_entoncesRetornaFalse() {
        val number = ""
        assertFalse(NumberValidator.validate(number))
    }
}