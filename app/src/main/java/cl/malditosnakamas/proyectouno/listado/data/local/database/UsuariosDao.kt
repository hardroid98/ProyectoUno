package cl.malditosnakamas.proyectouno.listado.data.local.database

import androidx.room.Dao
import androidx.room.Query
import cl.malditosnakamas.persistenciadedatos.personajes.data.local.database.RegistroEntity
import io.reactivex.Single

@Dao
interface UsuariosDao {

    @Query("SELECT nombre, rut, email FROM registro")
    fun obtenerDatos(): Single<List<UsuarioEntity>>
}