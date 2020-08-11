package cl.malditosnakamas.proyectouno.listado.data.local

import cl.malditosnakamas.proyectouno.listado.data.local.database.PersonaEntity
import cl.malditosnakamas.proyectouno.listado.domain.model.Persona

class PersonaMapper {
    fun mapDomainToRoom(persona: Persona): PersonaEntity {
        return PersonaEntity(
            nombre = persona.nombre,
            rut = persona.rut,
            clave = persona.clave,
            email = persona.email
        )
    }
}