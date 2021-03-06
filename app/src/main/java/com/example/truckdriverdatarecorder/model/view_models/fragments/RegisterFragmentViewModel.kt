package com.example.truckdriverdatarecorder.model.view_models.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.truckdriverdatarecorder.custom_fragment.viewModel.PapaViewModel
import com.example.truckdriverdatarecorder.model.room_database.entities.UserEntity
import com.example.truckdriverdatarecorder.repository.TruckDriverRepository
import com.example.truckdriverdatarecorder.utils.orefs_controller.PrefsController
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    app: Application,
    prefsController: PrefsController,
    repository: TruckDriverRepository
) : PapaViewModel(app, prefsController, repository) {

    enum class UserRegistrationState { IDLE, SUCCESS, FAIL }

    val userRegistrationState = MutableLiveData(UserRegistrationState.IDLE)

    fun getUserWithEmail(email: String): LiveData<UserEntity> {
        return repository.getUserWithEmail(email)
    }

    var name = ""

    fun saveUser(user: UserEntity) {
        this.name = user.name

        Observable.just(user)
            .filter {
                if (repository.getUserWithEmail(it.email).value == null) {
                    repository.createNewUser(it)
                }
                true
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userRegistrationState.value = UserRegistrationState.SUCCESS
            }, {
                userRegistrationState.value = UserRegistrationState.FAIL
            }, {
                Log.d(TAG, "saveUser: Used Saving Process Completed!")
            })
    }
    companion object{
        private const val TAG = "RegisterFragmentViewMod"
    }

}