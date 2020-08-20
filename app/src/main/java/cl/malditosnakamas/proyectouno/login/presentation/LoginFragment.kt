package cl.malditosnakamas.proyectouno.login.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import cl.malditosnakamas.proyectouno.R
import cl.malditosnakamas.proyectouno.databinding.FragmentLoginBinding
import cl.malditosnakamas.proyectouno.login.data.local.LocalLoginRepository
import cl.malditosnakamas.proyectouno.login.domain.Login
import cl.malditosnakamas.proyectouno.login.domain.LoginRepository
import cl.malditosnakamas.proyectouno.login.domain.LoginUseCase
import cl.malditosnakamas.proyectouno.util.validator.EmailValidator
import cl.malditosnakamas.proyectouno.util.validator.PassValidator
import io.reactivex.disposables.CompositeDisposable

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var repository: LoginRepository
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginViewModelFactory: LoginViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        bindView(view)
        setupLiveData()
        setupListeners()
    }

    private fun setupDependencies() {
        repository = LocalLoginRepository(requireActivity().applicationContext)
        loginUseCase = LoginUseCase(repository)
        loginViewModelFactory = LoginViewModelFactory(loginUseCase)
        loginViewModel =
            ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }

    private fun bindView(view: View) {
        binding = FragmentLoginBinding.bind(view)
    }

    private fun setupLiveData() {
        loginViewModel
            .getLiveData()
            .observe(
                viewLifecycleOwner,
                Observer { state ->
                    handleState(state)
                }
            )
    }

    private fun setupListeners() {
        binding.apply {
            btnIngresar.setOnClickListener {
                doClickLogin()
            }

            btnIrARegistro.setOnClickListener {
                irARegistro()
            }
        }
    }

    private fun doClickLogin() {
        clearErrorMessages()
        if (isValidInputValues()) {
            loginViewModel.login(
                binding.etCorreo.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

    private fun handleState(state: LoginState) {
        when (state) {
            is LoginState.LoadingState -> showLoading()
            is LoginState.Complete -> state.result?.let { handleResult(it) }
            is LoginState.Error -> state.error?.let { handleError(it) }
        }
    }

    private fun showLoading() {
        //Acá mostramos un dialogo que diga cargandooo!!!!
    }

    private fun handleResult(result: Boolean) {
        if (result) {
            irAListadoDeUsuarios()
        } else {
            Toast.makeText(requireContext(), "Login Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(requireContext(), "Ups, Error {${error.message}}", Toast.LENGTH_SHORT).show()
    }

    private fun irARegistro() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_loginFragment_to_registroUsuarioFragment)
    }

    private fun irAListadoDeUsuarios() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_loginFragment_to_usuariosFragment)
    }

    private fun clearErrorMessages() {

    }

    private fun isValidInputValues(): Boolean {
        var retorno = true
        binding.apply {
            if (!EmailValidator.validate(etCorreo.text.toString())) {
                etCorreo.error = getString(R.string.error_email)
                retorno = false
                etCorreo.requestFocus()
            }

            if (!PassValidator.validate(etPassword.text.toString())) {
                etPassword.error = getString(R.string.error_clave)
                retorno = false
                etPassword.requestFocus()
            }
        }
        return retorno
    }
}