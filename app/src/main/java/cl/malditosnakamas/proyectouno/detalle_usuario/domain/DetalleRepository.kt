package cl.malditosnakamas.proyectouno.detalle_usuario.domain

import io.reactivex.Single

interface DetalleRepository {

    fun detalle (detalle: Detalle) : Single<Boolean>
}