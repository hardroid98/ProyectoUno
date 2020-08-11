package cl.malditosnakamas.proyectouno.listado.presentation

import cl.malditosnakamas.proyectouno.listado.domain.model.Persona

interface ItemClickListener {
    fun onItemClick(persona: Persona)
}