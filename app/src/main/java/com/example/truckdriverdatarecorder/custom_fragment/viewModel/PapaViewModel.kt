package com.example.truckdriverdatarecorder.custom_fragment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.truckdriverdatarecorder.model.room_database.entities.UserEntity
import com.example.truckdriverdatarecorder.repository.TruckDriverRepository
import com.example.truckdriverdatarecorder.utils.orefs_controller.PrefsController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class PapaViewModel @Inject constructor(
    app: Application,
    val prefsController: PrefsController,
    val repository: TruckDriverRepository
): AndroidViewModel(app){

    fun getLoggedInUser(): LiveData<UserEntity> {
        return repository.getUserWithId(prefsController.getUserId()!!)
    }
}