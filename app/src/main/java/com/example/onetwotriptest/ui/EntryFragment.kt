
package com.example.onetwotriptest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.onetwotriptest.R
import com.example.onetwotriptest.databinding.FragmentEntryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EntryFragment : Fragment() {

    lateinit var binding: FragmentEntryBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntryBinding.inflate(inflater, container, false)
        viewModel.getFlightList()

//        binding.textView1.text = viewModel.flightList.value
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.textView1.text = "12"

        lifecycleScope.launch {
            delay(1000)
            binding.textView1.text = viewModel.flightList.value.toString()
        }
    }
}