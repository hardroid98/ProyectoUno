package cl.malditosnakamas.proyectouno.util.validator

import org.junit.Test

import org.junit.Assert.*

class NameValidatorTest {

    @Test
    fun validarNombre_cuandoSeInsertaUnNombreValido_entoncesRetornaTrue() {
        val name = "Harttyn Arce"
        assertTrue(NameValidator.validate(name))
    }

    @Test
    fun validarNombre_cuandoSeInsertaUnNumero_entoncesRetornaFalse() {
        val name = "Harttyn Arce1"
        assertFalse(NameValidator.validate(name))
    }

    @Test
    fun validarNombre_cuandoSeInsertaUnSoloNombreSinApellido_entoncesRetornaFalse() {
        val name = "Harttyn"
        assertFalse(NameValidator.validate(name))
    }

    @Test
    fun validarNombre_cuandoSeInsertaUnEspacioEnBlanco_entoncesRetornaFalse() {
        val name = " "
        assertFalse(NameValidator.validate(name))
    }

    @Test
    fun validarNombre_cuandoInsertaUnaCadenaVacia_entoncesRetornaFalse() {
        val name = ""
        assertFalse(NameValidator.validate(name))
    }
}