package com.example.onetwotriptest.presentation.flightlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onetwotriptest.R
import com.example.onetwotriptest.databinding.FragmentFlightListBinding
import com.example.onetwotriptest.presentation.BaseFragment
import com.example.onetwotriptest.ui.FlightApiStatus
import com.example.onetwotriptest.ui.FlightsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightListFragment : BaseFragment<FragmentFlightListBinding>(
    FragmentFlightListBinding::inflate
) {

    private val viewModel: FlightsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FlightListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setRecyclerView()

    }

    private fun setObservers() {
        viewModel.flightList.observe(viewLifecycleOwner, Observer { response ->
            response.body()?.let { adapter.submitList(it) }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            when (it) {
                FlightApiStatus.ERROR -> {
                    binding.icConnectionErrorImage.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), getString(R.string.no_intenet_connection), Toast.LENGTH_SHORT).show()
                }
                FlightApiStatus.LOADING -> {
                    Toast.makeText(requireContext(), getString(R.string.schedule_loading), Toast.LENGTH_SHORT).show()
                }
                FlightApiStatus.DONE -> {
                    Toast.makeText(requireContext(), getString(R.string.schedule_loaded), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setRecyclerView() {
        adapter = FlightListAdapter()
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}