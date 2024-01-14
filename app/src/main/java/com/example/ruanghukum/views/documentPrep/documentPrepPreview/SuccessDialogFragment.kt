package com.example.ruanghukum.views.documentPrep.documentPrepPreview

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.ruanghukum.R

class SuccessDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.dialog_success_generate_document, null)

        val btnLanjut = view.findViewById<Button>(R.id.btnLanjut)
        btnLanjut.setOnClickListener {
            dismiss()
        }

        val builder = AlertDialog.Builder(activity)
        builder.setView(view)

        return builder.create()
    }
}