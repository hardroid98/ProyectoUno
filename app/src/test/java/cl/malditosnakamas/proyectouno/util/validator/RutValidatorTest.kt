package cl.malditosnakamas.proyectouno.util.validator

import org.junit.Test

import org.junit.Assert.*

class RutValidatorTest {

    @Test
    fun validarRut_cuandoIngresamoUnRutValido_entoncesRetornaTrue() {
        val rut = "23056989-4"
        assertTrue(RutValidator.validate(rut))
    }

    @Test
    fun validarRut_cuandoIngresamoUnRutInvalido_entoncesRetornaFalse() {
        val rut = "23056999-4"
        assertFalse(RutValidator.validate(rut))
    }

    @Test
    fun validarRut_cuandoIngresamoUnCaracterInvalido_entoncesRetornaFalse() {
        val rut = "230a6999-4"
        assertFalse(RutValidator.validate(rut))
    }

    @Test
    fun validarRut_cuandoIngresamoUnRutVacio_entoncesRetornaFalse() {
        val rut = ""
        assertFalse(RutValidator.validate(rut))
    }

    @Test
    fun validarRut_cuandoIngresamoUnRutEnBlanco_entoncesRetornaFalse() {
        val rut = " "
        assertFalse(RutValidator.validate(rut))
    }
}