package cl.malditosnakamas.proyectouno.registro.data.local

import cl.malditosnakamas.persistenciadedatos.personajes.data.local.database.RegistroEntity
import cl.malditosnakamas.proyectouno.registro.domain.Registro

class RegistroMapper {
    fun mapDomainToRoom(registro: Registro): RegistroEntity {
        return RegistroEntity(
            id = 1,
            nombre = registro.nombre,
            rut = registro.rut,
            clave = registro.clave,
            email = registro.email
        )
    }
}
