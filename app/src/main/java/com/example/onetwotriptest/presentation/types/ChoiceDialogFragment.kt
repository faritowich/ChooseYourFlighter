package com.example.onetwotriptest.presentation.types

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onetwotriptest.R

class ChoiceDialogFragment : DialogFragment() {

    private val args by navArgs<ChoiceDialogFragmentArgs>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val arr = getFlightTypes()
            var chosenRadioButtonType: Int? = null
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.choose_flight_class)
                .setSingleChoiceItems(
                    arr, -1
                ) { dialog, which ->
                    chosenRadioButtonType = which
                }
                .setPositiveButton(
                    R.string.choose
                ) { dialog, id ->
                    if (chosenRadioButtonType != null) {
                        val action =
                            ChoiceDialogFragmentDirections.actionChoiceDialogFragmentToFlightInfoFragment(
                                args.currentFlight,
                                chosenRadioButtonType!!
                            )
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(requireContext(), R.string.choose_flight_class, Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton(
                    R.string.cancel
                ) { dialog, id ->
                    findNavController().navigate(R.id.action_choiceDialogFragment_to_flightListFragment)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun getFlightTypes(): Array<String> {
        val flightTypeList = mutableListOf<String>()
        for (type in args.currentFlight.prices) {
            flightTypeList.add(buildString {
                if (type.type == "economy"){
                    append(getString(R.string.economy_class))
                } else if (type.type == "bussiness"){
                    append(getString(R.string.business_class))
                }
                append(": ")
                append(type.amount)
                append(" р.")
            })
        }
        return flightTypeList.toTypedArray()
    }
}