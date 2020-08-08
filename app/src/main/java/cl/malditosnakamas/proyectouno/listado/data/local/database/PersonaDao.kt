package cl.malditosnakamas.proyectouno.listado.data.local.database

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

@Dao
interface PersonaDao {

    @Query("SELECT * FROM contactos")
    fun getAll(): Single<List<PersonaEntity>>
}