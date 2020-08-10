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
import cl.malditosnakamas.proyectouno.util.validator.EmailValidator
import cl.malditosnakamas.proyectouno.util.validator.NameValidator
import cl.malditosnakamas.proyectouno.util.validator.PassValidator
import cl.malditosnakamas.proyectouno.util.validator.RutValidator
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

private const val MIN_LENGTH_INPUT = 8
private const val MIN_LENGTH_CLAVE = 4

class RegistroUsuarioFragment : Fragment(R.layout.fragment_registro_usuario) {

    lateinit var binding: FragmentRegistroUsuarioBinding
    lateinit var registroUseCase: RegistroUseCase
    lateinit var repository: RegistroRepository
    val mapper = RegistroMapper()
    val compositeDisposable = CompositeDisposable()

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
        binding.btnRegistrar.setOnClickListener {
            doClickRegister()
        }
    }

    private fun doClickRegister() {
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
                    etRut.error = getString(R.string.error_rut)
                    retorno = false
                }
            }

            NameValidator.validate(etNombre.text.toString()).apply {
                if (!this) {
                    etNombre.error = getString(R.string.error_nombre)
                    retorno = false
                }
            }

            PassValidator.validate(etClave.text.toString()).apply {
                if (!this) {
                    etClave.error = getString(R.string.error_clave)
                    retorno = false
                }
            }

            EmailValidator.validate(etEmail.text.toString()).apply {
                if (!this) {
                    etEmail.error = getString(R.string.error_email)
                    retorno = false
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