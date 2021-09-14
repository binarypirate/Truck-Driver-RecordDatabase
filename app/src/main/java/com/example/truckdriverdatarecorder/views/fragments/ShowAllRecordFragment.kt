package com.example.truckdriverdatarecorder.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.truckdriverdatarecorder.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowAllRecordFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_all_record, container, false)
    }
}