package com.zcg.zapcompoc.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zcg.zapcompoc.R

object Utility {

    private lateinit var alertDialog: AlertDialog

    private fun getPositiveButtonText(
        context: Context
    ): String {
        return context.resources.getString(R.string.ok)
    }

    fun showAlert(context: Context, retryAction: () -> Unit, okAction: () -> Unit) {
        if (this::alertDialog.isInitialized && alertDialog.isShowing) {
            return
        }
        alertDialog = MaterialAlertDialogBuilder(context)
            .setMessage(context.resources.getString(R.string.unable_to_connect_with_internet))
            .setNegativeButton(getPositiveButtonText(context)) { dialog, _ ->
                run {
                    dialog.dismiss()
                    okAction.invoke()
                }
            }
            .setPositiveButton(context.resources.getString(R.string.retry)) { dialog, _ ->
                run {
                    dialog.dismiss()
                    retryAction.invoke()
                }

            }
            .setCancelable(false)
            .show()
    }
}