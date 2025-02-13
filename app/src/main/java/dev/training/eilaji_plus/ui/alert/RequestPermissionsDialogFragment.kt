package dev.training.eilaji_plus.ui.alert

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import dev.training.eilaji_plus.databinding.FragmentRequestPermissionsDialogBinding

class RequestPermissionsDialogFragment : DialogFragment() {
    private var requestPermissionsListener: RequestPermissionsListener? = null
    private lateinit var binding: FragmentRequestPermissionsDialogBinding

    // To Set Different Title
    private var title: String? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            requestPermissionsListener = parentFragment as RequestPermissionsListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context
                    .toString() + " must implement ChangeSoundListener Exception: " + e)
            )
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRequestPermissionsDialogBinding.inflate(
            layoutInflater
        )
        val args = arguments
        if (args != null) {
            title = args.getString(KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.isCancelable = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set the title for the dialog
        binding.requestPermissionsTitle.text = title
        // set the click logic
        setupListeners()
    }

    private fun setupListeners() {
        binding.allowPermissions.setOnClickListener { view: View? ->
            if (requestPermissionsListener != null) {
                requestPermissionsListener!!.onAllowClicked()
                dismiss()
            }
        }
        binding.denyPermissions.setOnClickListener { view: View? ->
            if (requestPermissionsListener != null) {
                requestPermissionsListener!!.onDenyClicked()
                dismiss()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        requestPermissionsListener = null
    }

    interface RequestPermissionsListener {
        fun onAllowClicked()

        fun onDenyClicked()
    }

    companion object {
        private const val KEY = "DialogTitleKey"

        // Todo check the Riddle me this
        fun newInstance(title: String?): RequestPermissionsDialogFragment {
            val args = Bundle()
            args.putString(KEY, title)
            val fragment = RequestPermissionsDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
