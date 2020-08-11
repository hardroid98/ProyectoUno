package cl.malditosnakamas.proyectouno.listado.data.local

import android.content.Context
import cl.malditosnakamas.proyectouno.database.ServiceDataBase
import cl.malditosnakamas.proyectouno.listado.domain.UsuariosRepository
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuarios
import io.reactivex.Single

// implmenta el UsuariosRepository y hace referencia a la base de datos
class LocalUsuariosRepository(
    applicationContext: Context,
    private val usuariosMapper: UsuariosMapper
) : UsuariosRepository {

    // Este objeto hace referencia a la base de datos
    private val dataBase = ServiceDataBase(applicationContext)

    override fun obtenerDatos(): Single<Usuarios> {
        return //TODO: AIURAAAA!!!
    }

}