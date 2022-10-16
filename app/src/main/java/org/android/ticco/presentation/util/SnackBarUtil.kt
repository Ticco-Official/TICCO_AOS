package org.android.ticco.presentation.util

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import org.android.ticco.R
import org.android.ticco.databinding.CustomSnackbarBinding

class SnackBarUtil(view: View) {

    companion object {

        fun make(view: View) = SnackBarUtil(view)
    }

    private val context = view.context
    private val snackbar = Snackbar.make(view, "", 2000)
    private val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

    private val inflater = LayoutInflater.from(context)
    private val snackbarBinding: CustomSnackbarBinding = DataBindingUtil.inflate(inflater, R.layout.custom_snackbar, null, false)

    init {
        initView()
    }

    private fun initView() {
        with(snackbarLayout) {
            removeAllViews()
            setPadding(0, 0, 0, 20)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(snackbarBinding.root, 0)
        }
    }


    fun show() {
        snackbar.show()
    }
}