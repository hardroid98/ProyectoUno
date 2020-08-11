package cl.malditosnakamas.proyectouno.listado.data.local

import android.content.Context
import cl.malditosnakamas.proyectouno.database.ServiceDataBase
import cl.malditosnakamas.proyectouno.listado.domain.PersonasRepository
import cl.malditosnakamas.proyectouno.listado.domain.model.Personas
import io.reactivex.Single

class LocalPersonaRepository(
    applicationContext: Context,
    private val personaMapper: PersonaMapper
) : PersonasRepository {

    private val dataBase = ServiceDataBase(applicationContext)

    override fun getAll(): Single<Personas> {
        return //TODO: Preguntar al profe
    }

}