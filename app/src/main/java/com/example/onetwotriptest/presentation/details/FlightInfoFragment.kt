package com.example.onetwotriptest.presentation.details

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.onetwotriptest.R
import com.example.onetwotriptest.databinding.FragmentFlightInfoBinding
import com.example.onetwotriptest.presentation.BaseFragment
import com.example.onetwotriptest.presentation.utils.HandleAirportName
import com.example.onetwotriptest.presentation.utils.handleAirportName

class FlightInfoFragment : BaseFragment<FragmentFlightInfoBinding>(
    FragmentFlightInfoBinding::inflate
) {

    private val args by navArgs<FlightInfoFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            fromTextview.text = args.currentFlight.trips[0].from.handleAirportName()
            toTextview.text = args.currentFlight.trips.last().to.handleAirportName()
            priceTextView.text = getPriceText()
        }

        for (transferNumber in 0..args.currentFlight.trips.size - 1) {
            if (args.currentFlight.trips.size > 1) {
                val newHeaderTextView = TextView(requireContext(), null, R.style.headers)
                val headerText = "Рейс ${transferNumber + 1}"
                newHeaderTextView.text = headerText
                binding.transfersContainer.addView(newHeaderTextView)
            }
            val newTextView = TextView(requireContext(), null, R.style.transfers)
            newTextView.text = getTransferText(transferNumber)
            binding.transfersContainer.addView(newTextView)
        }
    }

    private fun getPriceText(): String {
        val flightTypeName = when (args.currentFlight.prices[args.chosenFlightType].type) {
            "economy" -> "эконом"
            "bussiness" -> "бизнес"
            else -> args.currentFlight.prices[args.chosenFlightType].type
        }
        return "Цена (${flightTypeName}-класс): ${args.currentFlight.prices[args.chosenFlightType].amount} р."
    }

    private fun getTransferText(flightNumber: Int): String {

        return buildString {
            if (args.currentFlight.trips.size - 1 > 0) {
                append("Вылет:\n")
                append(args.currentFlight.trips[flightNumber].from.handleAirportName())
                append("\nПрибытие:\n")
                append(args.currentFlight.trips[flightNumber].to.handleAirportName())
            } else {
                append("Без пересадок")
            }
        }
    }
}

