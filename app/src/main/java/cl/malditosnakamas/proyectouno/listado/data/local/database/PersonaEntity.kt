package cl.malditosnakamas.proyectouno.listado.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactos")
data class PersonaEntity (
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "apellido") val apellido: String,
    @ColumnInfo(name = "apodo") val apodo: String,
    @ColumnInfo(name = "celular") val numeroTelf: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "foto") val foto: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}