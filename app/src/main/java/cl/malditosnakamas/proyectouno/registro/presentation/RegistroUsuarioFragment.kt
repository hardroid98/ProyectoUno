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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
            registroUseCase
                .execute(obtenerRegistro())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {result -> handleResult(result)},
                    {error -> handleError(error)}
                )
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

    private fun handleResult(result: Boolean?) {
        Toast.makeText(requireContext(), "Registro $result", Toast.LENGTH_SHORT).show()
    }

    private fun handleError(error: Throwable?) {
        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
    }


}