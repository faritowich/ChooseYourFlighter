package com.example.onetwotriptest.ui

import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.onetwotriptest.R
import com.example.onetwotriptest.databinding.FragmentFlightInfoBinding
import com.example.onetwotriptest.databinding.FragmentFlightListBinding
import com.example.onetwotriptest.util.Constants
import dagger.hilt.android.AndroidEntryPoint

class FlightInfoFragment : Fragment() {

    lateinit var binding: FragmentFlightInfoBinding
    private val args by navArgs<FlightInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightInfoBinding.inflate(inflater, container, false)

        binding.fromTextview.text = Constants.handleAirportName(args.currentFlight.trips[0].from)
        binding.toTextview.text = Constants.handleAirportName(args.currentFlight.trips[0].to)
        binding.priceTextView.text = getPriceText()

        for (transferNumber in 0..args.currentFlight.trips.size - 1) {
            if (args.currentFlight.trips.size > 1) {
                val newHeaderTextView = TextView(requireContext(),null, R.style.headers)
                val headerText =  "Рейс ${transferNumber + 1}"
                newHeaderTextView.text = headerText
                binding.transfersContainer.addView(newHeaderTextView)
            }
            val newTextView = TextView(requireContext(),null, R.style.transfers)
            newTextView.text = getTransferText(transferNumber)
            binding.transfersContainer.addView(newTextView)
        }
        return binding.root
    }

    fun getPriceText(): String {
        val flightTypeName = when (args.currentFlight.prices[args.chosenFlightType].type) {
            "economy" -> "эконом"
            "bussiness" -> "бизнес"
            else -> args.currentFlight.prices[args.chosenFlightType].type
        }
        return "Цена (${flightTypeName}-класс): ${args.currentFlight.prices[args.chosenFlightType].amount} р."
    }

    fun getTransferText(flightNumber: Int): String {

        return buildString {
            if (args.currentFlight.trips.size - 1 > 0) {
                append("Вылет:\n")
                append(Constants.handleAirportName(args.currentFlight.trips[flightNumber].from))
                append("\nПрибытие:\n")
                append(Constants.handleAirportName(args.currentFlight.trips[flightNumber].to))
            } else {
                append("Без пересадок")
            }
        }
    }
}