package cl.malditosnakamas.proyectouno.listado.data.local

import cl.malditosnakamas.proyectouno.listado.data.local.database.UsuarioEntity
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuario
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuarios

class UsuariosMapper {
    fun mapDomainToRoom(usuario: Usuario): UsuarioEntity {
        return UsuarioEntity(
            nombre = usuario.nombre,
            rut = usuario.rut,
            email = usuario.email
        )
    }

    fun mapRoomToDomain(list: List<UsuarioEntity>): Usuarios {
        return Usuarios(list.map {
            Usuario(it.nombre, it.rut, it.email)
        })
    }
}