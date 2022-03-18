package com.rojas.androidtestkotlin.domain

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.rojas.androidtestkotlin.R

class DialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.error_data))
                .setPositiveButton(getString(R.string.positive)) { _,_ -> }
                .create()

    companion object {
        const val TAG = "ErrorDataSource"
    }
}
