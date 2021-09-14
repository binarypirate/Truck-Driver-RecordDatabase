package com.example.truckdriverdatarecorder.views.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.truckdriverdatarecorder.R
import com.example.truckdriverdatarecorder.databinding.FragmentSplashBinding
import com.example.truckdriverdatarecorder.model.view_models.fragments.SplashFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val mViewModel: SplashFragmentViewModel by viewModels()
    private lateinit var mBinding: FragmentSplashBinding

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment\
       mBinding = FragmentSplashBinding.inflate(layoutInflater)

       mViewModel.userLoginSession.observe(viewLifecycleOwner) {
           if (it == SplashFragmentViewModel.UserLoginSession.OK) findNavController().navigate(R.id.action_splashFragment_to_dashBoardFragment)
           if (it == SplashFragmentViewModel.UserLoginSession.NOT_OK) findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
       }

       Handler(Looper.getMainLooper()).postDelayed({
           mViewModel.checkSession()
       }, 3000)

       return mBinding.root
   }
}