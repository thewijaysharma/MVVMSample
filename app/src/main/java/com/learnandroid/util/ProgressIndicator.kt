package com.learnandroid.util

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.learnandroid.R

class ProgressIndicator : DialogFragment(){

    var isCancellable : Boolean = false

    companion object{

        const val TAG = "ProgressIndicator"
        const val IS_CANCELABLE = "IS_CANCELABLE"

        fun createInstance(isCancelable : Boolean = false) : ProgressIndicator{
            val bundle = Bundle()
            bundle.putBoolean(IS_CANCELABLE, isCancelable)
            val progressIndicator = ProgressIndicator()
            progressIndicator.arguments = bundle
            return progressIndicator
        }

    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundDrawableResource(R.color.dialog_background)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_progress_indicator, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.apply { isCancelable = false }
        return dialog
    }



}