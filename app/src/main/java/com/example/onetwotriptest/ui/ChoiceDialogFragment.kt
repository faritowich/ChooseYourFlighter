package com.example.onetwotriptest.ui

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
            builder.setTitle("Выберите класс перелета")
                .setSingleChoiceItems(
                    arr, -1
                ) { dialog, which ->
                    chosenRadioButtonType = which
                }
                .setPositiveButton(
                    "Выбрать"
                ) { dialog, id ->
                    if (chosenRadioButtonType != null) {
                        val action =
                            ChoiceDialogFragmentDirections
                                .actionChoiceDialogFragmentToFlightInfoFragment(args.currentFlight,
                                    chosenRadioButtonType!!
                                )
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(requireContext(), "Выберите класс перелёта", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton(
                    "Отмена"
                ) { dialog, id ->
                    findNavController().navigate(R.id.action_choiceDialogFragment_to_flightListFragment)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun getFlightTypes(): Array<String>? {
        val flightTypeList = mutableListOf<String>()
        for (type in args.currentFlight.prices) {
            flightTypeList.add(buildString {
                if (type.type == "economy"){
                    append("Эконом-класс")
                } else if (type.type == "bussiness"){
                    append("Бизнес-класс")
                }
                append(": ")
                append(type.amount)
                append(" р.")
            })
        }
        return flightTypeList.toTypedArray()
    }
}