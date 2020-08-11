package cl.malditosnakamas.proyectouno.registro.data.local

import cl.malditosnakamas.proyectouno.registro.data.local.database.RegistroEntity
import cl.malditosnakamas.proyectouno.registro.domain.Registro

class RegistroMapper {
    fun mapDomainToRoom(registro: Registro): RegistroEntity {
        return RegistroEntity(
            nombre = registro.nombre,
            rut = registro.rut,
            clave = registro.clave,
            email = registro.email
        )
    }
}
