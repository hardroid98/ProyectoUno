package cl.malditosnakamas.proyectouno.registro.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.malditosnakamas.proyectouno.R
import cl.malditosnakamas.proyectouno.databinding.FragmentRegistroUsuarioBinding
import cl.malditosnakamas.proyectouno.registro.data.local.LocalRegistroRepository
import cl.malditosnakamas.proyectouno.registro.data.local.RegistroMapper
import cl.malditosnakamas.proyectouno.registro.domain.Registro
import cl.malditosnakamas.proyectouno.registro.domain.RegistroRepository
import cl.malditosnakamas.proyectouno.registro.domain.RegistroUseCase
import cl.malditosnakamas.proyectouno.util.ConstantValues.EMPTY_STRING
import cl.malditosnakamas.proyectouno.util.validator.EmailValidator
import cl.malditosnakamas.proyectouno.util.validator.NameValidator
import cl.malditosnakamas.proyectouno.util.validator.PassValidator
import cl.malditosnakamas.proyectouno.util.validator.RutValidator
import cl.malditosnakamas.proyectouno.util.watcher.RutTextWatcher
import com.google.android.material.textfield.TextInputEditText

class RegistroUsuarioFragment : Fragment(R.layout.fragment_registro_usuario) {
    lateinit var binding: FragmentRegistroUsuarioBinding
    lateinit var registroUseCase: RegistroUseCase
    lateinit var repository: RegistroRepository
    lateinit var registroViewModel: RegistroViewModel
    lateinit var registroViewModelFactory: RegistroViewModelFactory
    private val mapper = RegistroMapper()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        bindView(view)
        setupLiveData()
        setupListeners()
    }

    private fun setupDependencies() {
        repository = LocalRegistroRepository(requireActivity().applicationContext, mapper)
        registroUseCase = RegistroUseCase(repository)
        registroViewModelFactory = RegistroViewModelFactory(registroUseCase)
        registroViewModel =
            ViewModelProvider(this, registroViewModelFactory).get(RegistroViewModel::class.java)
    }

    private fun bindView(view: View) {
        binding = FragmentRegistroUsuarioBinding.bind(view)
    }

    private fun setupLiveData() {
        registroViewModel
            .getLiveData()
            .observe(
                viewLifecycleOwner,
                Observer {
                    state -> handleState(state)
                }
            )
    }

    private fun setupListeners() {
        binding.apply {
            btnRegistrar.setOnClickListener {
                doClickRegister()
            }

            RutTextWatcher().attachEditText(etRut)
        }
    }

    private fun doClickRegister() {
        clearErrorMessages()
        if (isValidateInputValues()) {
            registroViewModel.register(obtenerRegistro())
        }
    }

    private fun clearErrorMessages() {
        binding.apply {
            ilClave.error = EMPTY_STRING
            ilEmail.error = EMPTY_STRING
            ilNombre.error = EMPTY_STRING
            ilRut.error = EMPTY_STRING
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

    private fun isValidateInputValues(): Boolean {
        var retorno = true

        binding.apply {
            RutValidator.validate(etRut.text.toString()).apply {
                if (!this) {
                    ilRut.error = getString(R.string.error_rut)
                    retorno = false
                    etRut.requestFocus()
                }
            }

            PassValidator.validate(etClave.text.toString()).apply {
                if (!this) {
                    ilClave.error = getString(R.string.error_clave)
                    retorno = false
                    etClave.requestFocus()
                }
            }

            EmailValidator.validate(etEmail.text.toString()).apply {
                if (!this) {
                    ilEmail.error = getString(R.string.error_email)
                    retorno = false
                    etEmail.requestFocus()
                }
            }

            NameValidator.validate(etNombre.text.toString()).apply {
                if (!this) {
                    ilNombre.error = getString(R.string.error_nombre)
                    retorno = false
                    etNombre.requestFocus()
                }
            }

            return retorno
        }
    }

    fun getTextValue(textInputEditText: TextInputEditText): String {
        return textInputEditText.text.toString()
    }

    private fun handleState(state: RegistroState) {
        /*if(state.result != null ){
            Toast.makeText(requireContext(), "Registro $state.result", Toast.LENGTH_SHORT).show()
        }

        if(state.error != null){
            Toast.makeText(requireContext(), "Error ${state.error}", Toast.LENGTH_SHORT).show()
        }*/
        when(state){
            is RegistroState.Loading -> showLoading()
            is RegistroState.Complete -> handleRegister(state.result)
            is RegistroState.Error -> handleError(state.error)
        }
    }

    private fun handleError(error: Throwable?) {
        Toast.makeText(requireContext(), "Error ${error?.message}", Toast.LENGTH_SHORT).show()
    }

    private fun handleRegister(result: Boolean?) {
        Toast.makeText(requireContext(), "Registro $result", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {

    }
}