package cl.malditosnakamas.proyectouno.login.data

import androidx.room.Dao
import androidx.room.Query
import cl.malditosnakamas.persistenciadedatos.personajes.data.local.database.RegistroEntity
import io.reactivex.Single

@Dao
interface LoginDao {
    @Query("SELECT email, clave FROM registro")
    fun getLogin (): Single<List<RegistroEntity>>


}