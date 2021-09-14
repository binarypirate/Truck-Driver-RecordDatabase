package com.example.truckdriverdatarecorder.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.truckdriverdatarecorder.R
import com.example.truckdriverdatarecorder.custom_fragment.fragment.PapaFragment
import com.example.truckdriverdatarecorder.databinding.FragmentLogInBinding
import com.example.truckdriverdatarecorder.model.view_models.fragments.LogInFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import java.util.*

@AndroidEntryPoint
class LogInFragment : PapaFragment() {
    private val mViewModel: LogInFragmentViewModel by viewModels()
    private lateinit var mBinding: FragmentLogInBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentLogInBinding.inflate(layoutInflater)

        mBinding.signUpTxt.setOnClickListener{
            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }

        mViewModel.loginUserState.observe(viewLifecycleOwner) {
            if (it == LogInFragmentViewModel.LoginUserState.SUCCESS) {
                findNavController().navigate(R.id.action_logInFragment_to_dashBoardFragment)
            }
            if (it == LogInFragmentViewModel.LoginUserState.INVALID_LOGIN) {
                shortToast("Invalid Login!")
            }
        }

        mBinding.logInBtn.setOnClickListener {
            val emailOrUserName = mBinding.emailEditTxt.text.toString().trim().lowercase(Locale.ENGLISH)
            val password = mBinding.passwordEditTxt.text.toString().trim()

            when {
                emailOrUserName.isEmpty() -> requestFocusWithError(mBinding.emailEditTxt, "Please Enter email or username")
                password.isEmpty() -> requestFocusWithError(mBinding.passwordEditTxt, "Please Enter your password")
                else -> mViewModel.loginUser(emailOrUserName, emailOrUserName, password)
            }
        }



        return mBinding.root
    }

}