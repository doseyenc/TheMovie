package com.doseyenc.common

import android.app.ProgressDialog
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private var progressDialog: ProgressDialog? = null

    open fun showProgress(isLoading: Boolean) {
        if (isLoading) {
            if (progressDialog == null) {
                progressDialog = ProgressDialog(requireContext()).apply {
                    setMessage("Loading, please wait...")
                    setCancelable(false)
                }
            }
            progressDialog?.show()
        } else {
            progressDialog?.dismiss()
        }
    }

    open fun showError(message: String?) {
        message?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}
