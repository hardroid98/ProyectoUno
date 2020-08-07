package cl.malditosnakamas.proyectouno.registro.data.local

import cl.malditosnakamas.proyectouno.registro.domain.Registro
import cl.malditosnakamas.proyectouno.registro.domain.RegistroRepository
import io.reactivex.Single

class LocalRegistroRepository : RegistroRepository {

    override fun registro(registro: Registro): Single<Boolean> {
        return Single.just(true)
    }
}