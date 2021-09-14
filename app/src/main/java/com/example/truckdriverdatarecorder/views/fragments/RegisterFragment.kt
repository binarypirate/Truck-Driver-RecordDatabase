package com.example.truckdriverdatarecorder.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.truckdriverdatarecorder.custom_fragment.fragment.PapaFragment
import com.example.truckdriverdatarecorder.databinding.FragmentRegisterBinding
import com.example.truckdriverdatarecorder.model.room_database.entities.UserEntity
import com.example.truckdriverdatarecorder.model.view_models.fragments.RegisterFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.System.currentTimeMillis

@AndroidEntryPoint
class RegisterFragment : PapaFragment() {
    private val mViewModel: RegisterFragmentViewModel by viewModels()
    private lateinit var mBinding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentRegisterBinding.inflate(layoutInflater)

        mViewModel.userRegistrationState.observe(viewLifecycleOwner) {
            if (it == RegisterFragmentViewModel.UserRegistrationState.SUCCESS) {
                findNavController().navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToLogInFragment(
                    ))
            }
            if (it == RegisterFragmentViewModel.UserRegistrationState.FAIL) {
                shortToast("Fail!")
            }
        }

        mBinding.registerBtn.setOnClickListener {
            val email = mBinding.emailEditTxt.text.toString().trim()
            val password = mBinding.passwordEditTxt.text.toString().trim()
            val confirmPass = mBinding.confirmPassEditTxt.text.toString().trim()

            when {
                email.isEmpty() -> {
                    requestFocusWithError(mBinding.emailEditTxt, "Please enter your emial")
                }
                (!(email.contains("@"))) -> {
                    requestFocusWithError(
                        mBinding.emailEditTxt,
                        "Please enter a valid email address"
                    )
                }
                (!(email.contains(".com"))) -> {
                    requestFocusWithError(
                        mBinding.emailEditTxt,
                        "Please enter a valid email address"
                    )
                }
                password.isEmpty() -> {
                    requestFocusWithError(mBinding.passwordEditTxt, "Please enter your password")
                }
                password.length < 5 -> {
                    requestFocusWithError(
                        mBinding.passwordEditTxt,
                        "Please enter a strong password"
                    )
                }
                confirmPass.isEmpty() -> {
                    requestFocusWithError(
                        mBinding.confirmPassEditTxt,
                        "Please enter confirm password"
                    )
                }
                (!(confirmPass == password)) -> {
                    requestFocusWithError(mBinding.confirmPassEditTxt, "confirm password not match")
                }
                else -> {
                    mViewModel.getUserWithEmail(email).observe(viewLifecycleOwner) {
                        if (it == null) {
                            mViewModel.saveUser(
                                UserEntity(
                                    email,
                                    password,
                                    confirmPass,
                                    currentTimeMillis().toString()
                                )
                            )
                        } else {
                            shortToast("User Already Exist!")
                        }
                    }

                }
            }
        }
        return mBinding.root
    }
}