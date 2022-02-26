package com.example.onetwotriptest.presentation.entry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.onetwotriptest.R
import com.example.onetwotriptest.databinding.FragmentEntryBinding
import com.example.onetwotriptest.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryFragment : BaseFragment<FragmentEntryBinding>(
    FragmentEntryBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goToFlightsButton.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_flightListFragment)
        }
    }
}