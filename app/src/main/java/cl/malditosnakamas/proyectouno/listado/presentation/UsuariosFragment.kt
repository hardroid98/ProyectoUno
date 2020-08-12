package cl.malditosnakamas.proyectouno.listado.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import cl.malditosnakamas.proyectouno.R
import cl.malditosnakamas.proyectouno.databinding.FragmentUsuariosBinding
import cl.malditosnakamas.proyectouno.listado.data.local.LocalUsuariosRepository
import cl.malditosnakamas.proyectouno.listado.data.local.UsuariosMapper
import cl.malditosnakamas.proyectouno.listado.domain.ObtenerUsuariosUseCase
import cl.malditosnakamas.proyectouno.listado.domain.UsuariosRepository
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuario
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuarios
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UsuariosFragment: Fragment(R.layout.fragment_usuarios),
    ItemClickListener {

    private lateinit var binding: FragmentUsuariosBinding
    private lateinit var usuariosAdapter: UsuariosAdapter
    private lateinit var useCase: ObtenerUsuariosUseCase
    private lateinit var repository: UsuariosRepository
    private val mapper = UsuariosMapper()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        bindView(view)
        setupRecyclerView()
        setupUseCase()
        binding = FragmentUsuariosBinding.bind(view)

    }

    private fun setupDependencies() {
        TODO("Not yet implemented")
    }

    private fun bindView(view: View) {
        binding = FragmentUsuariosBinding.bind(view)
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvUsuarios.setHasFixedSize(true)
            rvUsuarios.layoutManager = GridLayoutManager(
                requireContext(),
                2,
                GridLayoutManager.VERTICAL,
                false)
        }
    }

    @SuppressLint("CheckResult")
    private fun setupUseCase() {
        repository = LocalUsuariosRepository(requireContext(), mapper)
        useCase = ObtenerUsuariosUseCase(repository)
        useCase.excecute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result -> handleReult(result)},
                {error -> handleError(error)}
            )
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(requireContext(),
            "Error {${error.message}}",
            Toast.LENGTH_LONG)
            .show()
    }

    private fun handleReult(result: Usuarios) {
        val adapter = UsuariosAdapter(result.listaUsuarios)
        binding.rvUsuarios.adapter = usuariosAdapter
        }

    override fun onItemClick(usuario: Usuario) {
        binding.rvUsuarios.setOnClickListener {
            // Navigation.findNavController(getView()).navigate(R.id.action_usuariosFragment_to_detallesFragment);        }
        }
    }
}