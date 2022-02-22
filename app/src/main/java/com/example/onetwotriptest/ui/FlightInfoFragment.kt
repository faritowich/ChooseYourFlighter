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
    private val sharedViewModel: MainViewModel by activityViewModels()

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

        var price = 0
        for (flightType in args.currentFlight.prices){
            if (args.chosenFlightType == "Эконом") {
                if (flightType.type == "economy") {
                    price = flightType.amount
                }
            } else if (args.chosenFlightType == "Бизнес") {
                if (flightType.type == "bussiness") {
                    price = flightType.amount
                }
            }
        }

        return if (args.currentFlight.prices.size > 1) {
            "Цена билета ${args.chosenFlightType.lowercase()}-класса: ${price} р."
        } else {
            "Цена билета ${args.chosenFlightType.lowercase()}-класса: ${args.currentFlight.prices[0].amount} р."
        }
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