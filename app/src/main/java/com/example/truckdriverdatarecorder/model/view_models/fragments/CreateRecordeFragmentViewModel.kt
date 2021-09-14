package com.example.truckdriverdatarecorder.model.view_models.fragments

import android.app.Application
import android.util.Log
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
class CreateRecordeFragmentViewModel @Inject constructor(
    app: Application,
    prefsController: PrefsController,
    repository: TruckDriverRepository
) : PapaViewModel(app, prefsController, repository){

//    enum class CreateDataState { IDLE, CREATING, CREATED,  FAILED }
//    val createDataState = MutableLiveData(CreateDataState.IDLE)
//
//
//    fun craeteData(driverName:String, phoneNum: String, address:String, licenceNum:String, truckNum: String ) {
//        createDataState.value = CreateDataState.CREATED
//        Observable.just(UserEntity(driverName, phoneNum, address, licenceNum, driverName, phoneNum, address, licenceNum, truckNum))
//            .filter {
//                repository.craeteNewData(it)
//                true
//            }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                createDataState.value = CreateDataState.CREATED
//            }, {
//                it.printStackTrace()
//                createDataState.value = CreateDataState.FAILED
//            }, {
//                Log.d(TAG, "createUser: Process Completed!")
//            })

}

//    companion object{
//        private const val TAG = "CreateRecordeFragmentView"
//    }
