package cl.malditosnakamas.proyectouno.login.presentation

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import cl.malditosnakamas.proyectouno.R
import cl.malditosnakamas.proyectouno.databinding.FragmentLoginBinding
import cl.malditosnakamas.proyectouno.login.data.local.LocalLoginRepository
import cl.malditosnakamas.proyectouno.login.domain.Login
import cl.malditosnakamas.proyectouno.login.domain.LoginRepository
import cl.malditosnakamas.proyectouno.login.domain.LoginUseCase
import cl.malditosnakamas.proyectouno.util.validator.EmailValidator
import cl.malditosnakamas.proyectouno.util.validator.PassValidator
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginFragment : Fragment(R.layout.fragment_login) {

    lateinit var binding: FragmentLoginBinding
    lateinit var loginUseCase: LoginUseCase
    lateinit var repository: LoginRepository
    private val compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        bindView(view)
        setupListeners()
    }

    private fun setupDependencies() {
        repository = LocalLoginRepository(requireActivity().applicationContext)
        loginUseCase = LoginUseCase(repository)
    }

    private fun bindView(view: View) {
        binding = FragmentLoginBinding.bind(view)
    }

    private fun setupListeners() {
        binding.btnIngresar.setOnClickListener {
            doClickLogin()
        }
    }

    private fun doClickLogin() {
        clearErrorMessages()
        if (isValidInputValues()) {
            compositeDisposable.add(
                loginUseCase
                    .execute(obtenerLoginValues())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {result -> handleResult(result)},
                        {error -> handleError(error)}
                    )

            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(requireContext(), "Ups, Error {${error.message}}", Toast.LENGTH_SHORT).show()
    }

    private fun handleResult(result: Boolean) {
        if(result){
            Toast.makeText(requireContext(), "Login OK", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(requireContext(), "Login Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obtenerLoginValues(): Login {
        return Login(
            getTextValue(binding.etCorreo),
            getTextValue(binding.etPassword)
        )
    }

    fun getTextValue(textInputEditText: AppCompatEditText): String {
        return textInputEditText.text.toString()
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

            if(!PassValidator.validate(etPassword.text.toString())){
                etPassword.error = getString(R.string.error_clave)
                retorno = false
                etPassword.requestFocus()
            }
        }
        return retorno
    }
}