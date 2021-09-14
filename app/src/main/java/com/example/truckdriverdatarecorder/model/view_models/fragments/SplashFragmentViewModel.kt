package com.example.truckdriverdatarecorder.model.view_models.fragments

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.truckdriverdatarecorder.custom_fragment.viewModel.PapaViewModel
import com.example.truckdriverdatarecorder.repository.TruckDriverRepository
import com.example.truckdriverdatarecorder.utils.orefs_controller.PrefsController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    app: Application,
    prefsController: PrefsController,
    repository: TruckDriverRepository
) : PapaViewModel(app, prefsController, repository) {

    enum class UserLoginSession { IDLE, OK, NOT_OK }

    val userLoginSession = MutableLiveData(UserLoginSession.IDLE)

    fun checkSession() {
        if (prefsController.getUserId() == null) {
            userLoginSession.value = UserLoginSession.NOT_OK
        } else {
            userLoginSession.value = UserLoginSession.OK
        }
    }
}