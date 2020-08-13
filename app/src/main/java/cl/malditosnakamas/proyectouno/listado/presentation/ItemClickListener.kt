package cl.malditosnakamas.proyectouno.listado.presentation

import cl.malditosnakamas.proyectouno.listado.domain.model.Usuario

interface ItemClickListener {
    fun onItemClick(usuario: Usuario)
}