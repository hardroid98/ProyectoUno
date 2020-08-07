package cl.malditosnakamas.persistenciadedatos.personajes.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registro")
data class RegistroEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "clave") val clave: String,
    @ColumnInfo(name = "rut") val rut: String
)