package cl.malditosnakamas.proyectouno.registro.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RegistroUsuarioFragment : Fragment(R.layout.fragment_registro_usuario) {
    lateinit var binding: FragmentRegistroUsuarioBinding
    lateinit var registroUseCase: RegistroUseCase
    lateinit var repository: RegistroRepository
    private val mapper = RegistroMapper()
    private val compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentRegistroUsuarioBinding.bind(view)
        setupListener()
    }

    private fun setupDependencies() {
        repository = LocalRegistroRepository(requireActivity().applicationContext, mapper)
        registroUseCase = RegistroUseCase(repository)
    }

    private fun setupListener() {
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
            compositeDisposable.add(
                registroUseCase
                    .execute(obtenerRegistro())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { result -> handleResult(result) },
                        { error -> handleError(error) }
                    )
            )
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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
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

    private fun handleResult(result: Boolean?) {
        Toast.makeText(requireContext(), "Registro $result", Toast.LENGTH_SHORT).show()
    }

    private fun handleError(error: Throwable?) {
        Toast.makeText(requireContext(), "Error ${error?.message}", Toast.LENGTH_SHORT).show()
    }
}