package cl.malditosnakamas.proyectouno.login.presentation

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.malditosnakamas.proyectouno.login.domain.Login
import cl.malditosnakamas.proyectouno.login.domain.LoginUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<LoginState>()
    private val compositeDisposable = CompositeDisposable()

    fun getLiveData() = liveData

    fun login(email: String, clave: String) {
        liveData.postValue(LoginState.LoadingState)
        compositeDisposable.add(
            loginUseCase
                .execute(
                    Login(email, clave)
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> handleResult(result) },
                    { error -> handleError(error) }
                )

        )
    }

    private fun handleResult(result: Boolean?) {
        liveData.postValue(LoginState.Complete(result))
    }

    private fun handleError(error: Throwable?) {
        liveData.postValue(LoginState.Error(error))
    }
}