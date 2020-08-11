package cl.malditosnakamas.proyectouno.login.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.malditosnakamas.proyectouno.R
import cl.malditosnakamas.proyectouno.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login){

    lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        bindView(view)
        setupListeners()
    }

    private fun setupDependencies() {

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
        if(isValidInputValues()){

        }
    }

    private fun isValidInputValues(): Boolean {
        TODO("Not yet implemented")
    }
}