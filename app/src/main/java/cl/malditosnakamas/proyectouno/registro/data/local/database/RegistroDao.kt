package cl.malditosnakamas.proyectouno.registro.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cl.malditosnakamas.persistenciadedatos.personajes.data.local.database.RegistroEntity
import io.reactivex.Single

@Dao
interface RegistroDao {
    @Query("SELECT * FROM registro")
    fun getAll(): Single<List<RegistroEntity>>

    @Insert
    fun insertAll(vararg personajesEntity: RegistroEntity)

    @Insert
    fun insert(registroEntity: RegistroEntity) : Single<Long>
}