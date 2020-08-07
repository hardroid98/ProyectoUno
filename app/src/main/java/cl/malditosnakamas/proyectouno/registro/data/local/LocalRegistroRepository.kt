package cl.malditosnakamas.proyectouno.registro.data.local

import android.content.Context
import androidx.room.Room
import cl.malditosnakamas.proyectouno.database.ProyectoUnoDataBase
import cl.malditosnakamas.proyectouno.registro.domain.Registro
import cl.malditosnakamas.proyectouno.registro.domain.RegistroRepository
import io.reactivex.Single

class LocalRegistroRepository(
    applicationContext: Context,
    private val registroMapper: RegistroMapper
) : RegistroRepository {

    val db = Room.databaseBuilder(
        applicationContext,
        ProyectoUnoDataBase::class.java, "database-pproyecto-uno"
    ).build()

    override fun registro(registro: Registro): Single<Boolean> {
        return db.registroDao().insert(registroMapper.mapDomainToRoom(registro))
    }
}