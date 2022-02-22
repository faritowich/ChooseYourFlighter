package com.example.onetwotriptest.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onetwotriptest.R

class ChoiceDialogFragment : DialogFragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()
    private val args by navArgs<ChoiceDialogFragmentArgs>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        return activity?.let {
            var chosenFlightType = ""
//            sharedViewModel.chosenFlightType.value = null
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выберите класс перелета")
                .setSingleChoiceItems(
                    R.array.choice_array, -1
                ) { dialog, which ->
                    chosenFlightType = resources.getStringArray(R.array.choice_array)[which]
//                    sharedViewModel.chosenFlightType.value = which
                }
                .setPositiveButton(
                    "Выбрать"
                ) { dialog, id ->
//                    if (sharedViewModel.chosenFlightType.value != null) {
                    if (!chosenFlightType.isEmpty()) {
                        val action =
                            ChoiceDialogFragmentDirections
                                .actionChoiceDialogFragmentToFlightInfoFragment(args.currentFlight, chosenFlightType)
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
}