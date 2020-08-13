package cl.malditosnakamas.proyectouno.listado.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.malditosnakamas.proyectouno.listado.domain.ObtenerUsuariosUseCase
import cl.malditosnakamas.proyectouno.listado.domain.model.Usuarios
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListadoViewModel(
    private val obtenerUsuariosUseCase: ObtenerUsuariosUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<ListadoState>()
    private val compositeDisposable = CompositeDisposable()

    fun getLiveData() = liveData

    fun listado() {
        liveData.postValue(ListadoState.LoadingState)
        compositeDisposable.add(obtenerUsuariosUseCase.excecute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleReult(result) },
                { error -> handleError(error) }
            ))
    }

    private fun handleError(error: Throwable?) {
        liveData.postValue(ListadoState.Error(error))
    }

    private fun handleReult(result: Usuarios?) {
        liveData.postValue(ListadoState.Complete(result))
    }

}