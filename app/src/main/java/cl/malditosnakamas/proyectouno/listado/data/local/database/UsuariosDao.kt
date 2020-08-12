package cl.malditosnakamas.proyectouno.listado.data.local.database

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

@Dao
interface UsuariosDao {

    @Query("SELECT * FROM usuarios")
    fun obtenerDatos(): Single<List<UsuarioEntity>>
}