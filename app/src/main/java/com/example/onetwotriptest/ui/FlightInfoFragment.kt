package com.example.onetwotriptest.ui

import android.os.Bundle
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
import dagger.hilt.android.AndroidEntryPoint

class FlightInfoFragment : Fragment() {

    lateinit var binding: FragmentFlightInfoBinding
    private val args by navArgs<FlightInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightInfoBinding.inflate(inflater, container, false)

        binding.fromAndToTextview.text = getFromAndToText()
        binding.priceTextView.text = getPriceText()

        for (transferNumber in 0..args.currentFlight.trips.size - 1) {
            val newTextView = TextView(requireContext())
            newTextView.text = getTransferText(transferNumber)
            binding.transfersContainer.addView(newTextView)
        }

        return binding.root
    }


    fun getFromAndToText(): String {
        return buildString {
            append(args.currentFlight.trips[0].from)
            append(" -> ")
            append(args.currentFlight.trips.last().to)
        }
    }

    fun getPriceText(): String {
        return "Цена билета ${args.currentFlight.prices[args.chosenFlightType].type}-класса: ${args.currentFlight.prices[args.chosenFlightType].amount} р."
    }

    fun getTransferText(flightNumber: Int): String {

        return buildString {
            if (args.currentFlight.trips.size - 1 > 0) {
                append(args.currentFlight.trips[flightNumber].from)
                append(" -> ")
                append(args.currentFlight.trips[flightNumber].to)
            } else {
                append("Без пересадок")
            }
        }
    }
}