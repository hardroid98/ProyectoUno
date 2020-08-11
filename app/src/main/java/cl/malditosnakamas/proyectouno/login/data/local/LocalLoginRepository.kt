package cl.malditosnakamas.proyectouno.login.data.local

import android.content.Context
import cl.malditosnakamas.proyectouno.database.ServiceDataBase
import cl.malditosnakamas.proyectouno.login.domain.Login
import cl.malditosnakamas.proyectouno.login.domain.LoginRepository
import io.reactivex.Single

class LocalLoginRepository(
    applicationContext: Context
) : LoginRepository {

    private val dataBase = ServiceDataBase(applicationContext)

    override fun login(login: Login): Single<Boolean> {
        return dataBase
            .getDB()
            .loginDao()
            .getLogin(login.email, login.clave)
            .map { usuarios ->
                usuarios.isNotEmpty()
            }
    }
}
