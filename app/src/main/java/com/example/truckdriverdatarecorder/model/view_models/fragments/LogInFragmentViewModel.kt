package com.example.truckdriverdatarecorder.model.view_models.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.truckdriverdatarecorder.custom_fragment.viewModel.PapaViewModel
import com.example.truckdriverdatarecorder.repository.TruckDriverRepository
import com.example.truckdriverdatarecorder.utils.orefs_controller.PrefsController
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LogInFragmentViewModel @Inject constructor(
    app: Application,
    prefsController: PrefsController,
    repository: TruckDriverRepository
) : PapaViewModel(app, prefsController, repository) {

    enum class LoginUserState { IDLE, SUCCESS, INVALID_LOGIN }
    val loginUserState = MutableLiveData(LoginUserState.IDLE)

    fun loginUser(email: String, name: String, password: String) {
        Observable.just(1)
            .map {
                repository.getUserWithEmailOrUserName(email, name)
            }
            .filter {
                if (it == null) {
                    loginUserState.value = LoginUserState.INVALID_LOGIN
                }
                true
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.password == password) {
                    prefsController.saveUserId(it.id)
                    loginUserState.value = LoginUserState.SUCCESS
                } else {
                    loginUserState.value = LoginUserState.INVALID_LOGIN
                }
            }, {
                loginUserState.value = LoginUserState.INVALID_LOGIN
            }, {
                Log.d(TAG, "loginUser: Process Completed!")
            })
    }
    companion object{
        private const val TAG = "LogInFragmentViewModel"
    }

}