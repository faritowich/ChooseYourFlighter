package com.example.onetwotriptest.ui.list

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onetwotriptest.databinding.FragmentFlightListBinding
import com.example.onetwotriptest.ui.FlightApiStatus
import com.example.onetwotriptest.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightListFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: FragmentFlightListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FlightListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightListBinding.inflate(inflater, container, false)
        setRecyclerView()
        viewModel.getFlightList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.flightList.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { adapter.setData(it) }
            }
        })

        viewModel.status.observe(this, Observer {
            when (it) {
                FlightApiStatus.ERROR -> {
                    binding.icConnectionErrorImage.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "Нет интернет-соединения", Toast.LENGTH_SHORT).show()
                }
                FlightApiStatus.LOADING -> {
                    Toast.makeText(requireContext(), "Загрузка рейсов...", Toast.LENGTH_SHORT).show()
                }
                FlightApiStatus.DONE -> {
                    Toast.makeText(requireContext(), "Рейсы загружены!", Toast.LENGTH_SHORT).show()
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