package cl.malditosnakamas.proyectouno.login.data.local.database

import androidx.room.Dao
import androidx.room.Query
import cl.malditosnakamas.proyectouno.registro.data.local.database.RegistroEntity
import io.reactivex.Single

@Dao
interface LoginDao {
    @Query("SELECT * FROM registro WHERE email = :email AND clave = :clave")
    fun getLogin(email: String, clave: String): Single<List<RegistroEntity>>
}