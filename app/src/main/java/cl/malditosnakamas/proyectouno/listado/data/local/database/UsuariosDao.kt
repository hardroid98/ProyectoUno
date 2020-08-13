package cl.malditosnakamas.proyectouno.listado.data.local.database

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

@Dao
interface UsuariosDao {

    @Query("SELECT nombre, rut, email FROM registro")
    fun obtenerDatos(): Single<List<UsuarioEntity>>
}