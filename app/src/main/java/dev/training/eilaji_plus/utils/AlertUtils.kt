package dev.training.eilaji_plus.utils

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import dev.training.eilaji_plus.R

/**
 * `AlertUtils` is a utility object providing convenient methods for displaying various types of alerts
 * and dialogs in an Android application. It includes functionalities for showing Toasts, Snackbars,
 * AlertDialogs, and BottomSheetDialogs.
 */
object AlertUtils {

    // Toast
    fun showToast(context: Context, txt: String) =
        Toast.makeText(context, txt, Toast.LENGTH_SHORT).show()

    fun showSnackBar(view: View, txt: String) =
        Snackbar.make(view, txt, Snackbar.LENGTH_SHORT).show()
    
    fun showSnackBarWithAction(view: View, txt: String, actionTxt: String, action: () -> Unit) = 
        Snackbar.make(view, txt, Snackbar.LENGTH_SHORT)
            .setAction(actionTxt) { action() }.show() 

    fun showAlertDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        positiveAction: () -> Unit,
        negativeAction: () -> Unit
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(positiveButtonText) { dialog, _ ->
            positiveAction()
            dialog.dismiss()
        }
        builder.setNegativeButton(negativeButtonText) { dialog, _ ->
            negativeAction()
            dialog.dismiss()
        }
        builder.create().show()
    }
    
    fun showBottomSheetDialog(context: Context, layout: Int, onDismiss: (() -> Unit)? = null): BottomSheetDialog {
        val bottomSheetDialog = BottomSheetDialog(context, R.style.AppBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(layout)
        bottomSheetDialog.setOnDismissListener { onDismiss?.invoke() }
        bottomSheetDialog.show()
        return bottomSheetDialog
    }
}