package com.example.truckdriverdatarecorder.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.truckdriverdatarecorder.R
import com.example.truckdriverdatarecorder.databinding.FragmentDashBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardFragment : Fragment() {

    private lateinit var mBinding : FragmentDashBoardBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
      mBinding = FragmentDashBoardBinding.inflate(layoutInflater)

      mBinding.topAppBar.setNavigationOnClickListener {
          mBinding.drawerLayout.open()
      }

      mBinding.nav.setNavigationItemSelectedListener {
          when (it.itemId) {
              R.id.createRecord -> {
                  findNavController().navigate(R.id.action_dashBoardFragment_to_createNewRecordFragment)
                  true
              }
              R.id.showAll ->{
                  findNavController().navigate(R.id.action_dashBoardFragment_to_showAllRecordFragment)
                  true
              }
              R.id.about->{
                  findNavController().navigate(R.id.action_dashBoardFragment_to_aboutFragment)
                  true
              }
              else -> {
                  true
              }
          }
      }

        return mBinding.root
    }

}