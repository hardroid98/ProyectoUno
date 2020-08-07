package cl.malditosnakamas.persistenciadedatos.personajes.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface RegistroDao {
    @Query("SELECT * FROM registro")
    fun getAll(): Single<List<RegistroEntity>>

    @Insert
    fun insertAll(vararg personajesEntity: RegistroEntity)

    @Insert
    fun insert(registroEntity: RegistroEntity) : Single<Boolean>
}