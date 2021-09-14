package com.example.truckdriverdatarecorder.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.truckdriverdatarecorder.R
import com.example.truckdriverdatarecorder.custom_fragment.fragment.PapaFragment
import com.example.truckdriverdatarecorder.databinding.FragmentCreateNewRecordBinding
import com.example.truckdriverdatarecorder.model.view_models.fragments.CreateRecordeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateNewRecordFragment : PapaFragment() {
    private val mViewModel: CreateRecordeFragmentViewModel by viewModels()
    private lateinit var mBinding: FragmentCreateNewRecordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding= FragmentCreateNewRecordBinding.inflate(layoutInflater)

        mViewModel.createDataState.observe(viewLifecycleOwner){
            mBinding.nameEdittxt.isEnabled = it == CreateRecordeFragmentViewModel.CreateDataState.IDLE
            mBinding.phoneNoEditTxt.isEnabled = it == CreateRecordeFragmentViewModel.CreateDataState.IDLE
            mBinding.licenceNoEditTxt.isEnabled = it == CreateRecordeFragmentViewModel.CreateDataState.IDLE
            mBinding.addressEditTxt.isEnabled = it == CreateRecordeFragmentViewModel.CreateDataState.IDLE
            mBinding.truckNoEditTxt.isEnabled = it == CreateRecordeFragmentViewModel.CreateDataState.IDLE

            mBinding.saveBtn.isEnabled = it == CreateRecordeFragmentViewModel.CreateDataState.IDLE
        }

        mBinding.saveBtn.setOnClickListener{
            val driverName = mBinding.nameEdittxt.text.toString().trim()
            val phoneNumber = mBinding.phoneNoEditTxt.text.toString().trim()
            val licenceNumber = mBinding.licenceNoEditTxt.text.toString().trim()
            val address = mBinding.addressEditTxt.text.toString().trim()
            val truckNumber = mBinding.truckNoEditTxt.text.toString().trim()

            when{
                driverName.isEmpty()-> requestFocusWithError(mBinding.nameEdittxt,"Please enter your name")
                phoneNumber.isEmpty()-> requestFocusWithError(mBinding.phoneNoEditTxt, "Please enter your number")
                licenceNumber.isEmpty()-> requestFocusWithError(mBinding.licenceNoEditTxt,"Please enter your licence Number")
                address.isEmpty()-> requestFocusWithError(mBinding.addressEditTxt, "Please enter your address")
                truckNumber.isEmpty()-> requestFocusWithError(mBinding.truckNoEditTxt,"Please enter your Truck number")
                else -> {
                    mViewModel.craeteData(driverName, phoneNumber, licenceNumber, address,truckNumber)
                    Log.d("data", "onCreateView: DataBaseCreated")
                    findNavController().navigate(R.id.action_createNewRecordFragment_to_dashBoardFragment)
                }
            }
        }

        return mBinding.root
    }
}