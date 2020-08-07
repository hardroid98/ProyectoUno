package cl.malditosnakamas.proyectouno.database

import android.content.Context
import androidx.room.Room

private const val DATA_BASE_NAME = "proyecto-uno.db"
class ServiceDataBase(
    applicationContext: Context
) {
    private val db = Room.databaseBuilder(
        applicationContext,
        ProyectoUnoDataBase::class.java, DATA_BASE_NAME
    ).build()

    fun getDB() = db
}