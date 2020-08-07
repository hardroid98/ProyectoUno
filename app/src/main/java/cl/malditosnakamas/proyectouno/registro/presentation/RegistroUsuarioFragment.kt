package cl.malditosnakamas.proyectouno.registro.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import cl.malditosnakamas.proyectouno.R
import cl.malditosnakamas.proyectouno.databinding.FragmentRegistroUsuarioBinding
import cl.malditosnakamas.proyectouno.registro.data.local.LocalRegistroRepository
import cl.malditosnakamas.proyectouno.registro.domain.Registro
import cl.malditosnakamas.proyectouno.registro.domain.RegistroRepository
import cl.malditosnakamas.proyectouno.registro.domain.RegistroUseCase
import com.google.android.material.textfield.TextInputEditText

class RegistroUsuarioFragment : Fragment(R.layout.fragment_registro_usuario) {

    lateinit var binding: FragmentRegistroUsuarioBinding
    lateinit var registroUseCase: RegistroUseCase
    lateinit var repository: RegistroRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentRegistroUsuarioBinding.bind(view)
        setupListener()
    }

    private fun setupDependencies() {
        repository = LocalRegistroRepository()
        registroUseCase = RegistroUseCase(repository)
    }

    private fun setupListener() {
        binding.btnRegistrar.setOnClickListener {
            Toast.makeText(requireContext(), "Holaaaaa", Toast.LENGTH_SHORT).show()
            registroUseCase.execute(obtenerRegistro())
        }
    }

    private fun obtenerRegistro(): Registro {
        return Registro(
            getTextValue(binding.etNombre),
            getTextValue(binding.etClave),
            getTextValue(binding.etRut),
            getTextValue(binding.etEmail)
        )
    }

    fun getTextValue(textInputEditText: TextInputEditText) : String{
        return textInputEditText.text.toString()
    }


}