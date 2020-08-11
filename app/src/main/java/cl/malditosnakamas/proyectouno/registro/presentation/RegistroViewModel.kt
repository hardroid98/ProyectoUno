package cl.malditosnakamas.proyectouno.registro.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.malditosnakamas.proyectouno.registro.domain.Registro
import cl.malditosnakamas.proyectouno.registro.domain.RegistroUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RegistroViewModel(
    private val registroUseCase: RegistroUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<RegistroState>()
    private val compositeDisposable = CompositeDisposable()

    fun getLiveData() = liveData

    fun register(params: Registro) {
        compositeDisposable.add(
            registroUseCase.execute(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> handleResult(result) },
                    { error -> handleError(error) }
                )

        )
    }

    private fun handleError(error: Throwable?) {
        //Esto ya no lo tenemos :(
        liveData.postValue(RegistroState(error = error))
    }

    private fun handleResult(result: Boolean) {
        liveData.postValue(RegistroState(result = result))
    }

}