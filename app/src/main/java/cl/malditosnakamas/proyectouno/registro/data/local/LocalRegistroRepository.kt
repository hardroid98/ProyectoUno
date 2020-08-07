package cl.malditosnakamas.proyectouno.registro.data.local

import android.content.Context
import cl.malditosnakamas.proyectouno.database.ServiceDataBase
import cl.malditosnakamas.proyectouno.registro.domain.Registro
import cl.malditosnakamas.proyectouno.registro.domain.RegistroRepository
import io.reactivex.Single

class LocalRegistroRepository(
    applicationContext: Context,
    private val registroMapper: RegistroMapper
) : RegistroRepository {

    private val dataBase = ServiceDataBase(applicationContext)

    override fun registro(registro: Registro): Single<Boolean> {
        return dataBase
            .getDB()
            .registroDao()
            .insert(registroMapper.mapDomainToRoom(registro)).map {
                it > 0
            }
    }
}