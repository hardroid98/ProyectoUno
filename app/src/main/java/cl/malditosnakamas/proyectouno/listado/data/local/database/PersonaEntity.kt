package cl.malditosnakamas.proyectouno.listado.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactos")
data class PersonaEntity (
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "clave") val clave: String,
    @ColumnInfo(name = "rut") val rut: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}