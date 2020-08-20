package cl.malditosnakamas.proyectouno.listado.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import cl.malditosnakamas.proyectouno.R
import cl.malditosnakamas.proyectouno.databinding.FragmentUsuariosBinding
import cl.malditosnakamas.proyectouno.listado.data.local.LocalUsuariosRepository
import cl.malditosnakamas.proyectouno.listado.data.local.UsuariosMapper
import cl.malditosnakamas.proyectouno.listado.domain.ObtenerUsuariosUseCase
import cl.malditosnakamas.proyectouno.listado.domain.UsuariosRepository
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuario
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuarios

class UsuariosFragment: Fragment(R.layout.fragment_usuarios),
    ItemClickListener {

    private lateinit var binding: FragmentUsuariosBinding
    private lateinit var usuariosAdapter: UsuariosAdapter
    private lateinit var listadoUseCase: ObtenerUsuariosUseCase
    private lateinit var repository: UsuariosRepository
    private lateinit var listadoViewModel: ListadoViewModel
    private lateinit var listadoViewModelFactory: ListadoViewModelFactory
    private val mapper = UsuariosMapper()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        callViewModel()
        bindView(view)
        setupLiveData()
        setupRecyclerView()
    }

    private fun setupDependencies() {
        repository = LocalUsuariosRepository(requireContext(), mapper)
        listadoUseCase = ObtenerUsuariosUseCase(repository)
        listadoViewModelFactory = ListadoViewModelFactory(listadoUseCase)
        listadoViewModel = ViewModelProvider(this, listadoViewModelFactory)
            .get(ListadoViewModel::class.java)
    }

    private fun bindView(view: View) {
        binding = FragmentUsuariosBinding.bind(view)
    }

    private fun setupLiveData() {
        listadoViewModel
            .getLiveData()
            .observe(
                viewLifecycleOwner,
                Observer {
                    state ->
                    handleState(state)
                }
            )
    }

    private fun handleState(state: ListadoState?) {
        when (state) {
            is ListadoState.LoadingState -> showLoading()
            is ListadoState.Complete -> state.result?.let { handleReult(it) }
            is ListadoState.Error -> state.error?.let { handleError(it) }
        }
    }

    private fun showLoading() {
        // pendiente carga
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

    private fun callViewModel() {
        listadoViewModel.listado()
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(requireContext(),
            "Error {${error.message}}",
            Toast.LENGTH_LONG)
            .show()
    }

    private fun handleReult(result: Usuarios) {
        usuariosAdapter = UsuariosAdapter(result.listaUsuarios, this)
        binding.rvUsuarios.adapter = usuariosAdapter
    }

    override fun onItemClick(usuario: Usuario) {
        binding.rvUsuarios.setOnClickListener {
            // Navigation.findNavController(getView()).navigate(R.id.action_usuariosFragment_to_detallesFragment);
        }
    }
}
