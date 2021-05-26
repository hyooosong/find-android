package find.ui.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import find.ui.R
import find.ui.databinding.DialogTwoButtonBinding
import find.ui.util.autoCleared

class TwoButtonDialog(private val from: Int, private val onClick: () -> Unit) : DialogFragment() {
    var binding by autoCleared<DialogTwoButtonBinding>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogTwoButtonBinding.inflate(requireActivity().layoutInflater)
        return activity?.let {
            val dialog = AlertDialog.Builder(it).create()
            dialog.setView(binding.root)
            dialog
        } ?: throw IllegalStateException()
    }

    override fun onStart() {
        super.onStart()
        whereFrom()
        setClickListener()
    }

    override fun onResume() {
        super.onResume()
        requireNotNull(dialog).apply {
            requireNotNull(window).apply {
                setLayout(
                    (resources.displayMetrics.widthPixels * 0.9).toInt(),
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setBackgroundDrawableResource(R.drawable.border_white_fill_round_10)
            }
            setCancelable(false)
        }
    }

    private fun whereFrom() {
        when (from) {
            0 -> setText(
                requireContext().getString(R.string.would_you_like_to_save),
                ""
            )
            1 -> setText(
                requireContext().getString(R.string.dialog_disconnect_title),
                requireContext().getString(R.string.dialog_disconnect_content)
            )
        }
    }

    private fun setText(title: String, content: String) {
        binding.tvTwoButtonTitle.text = title
        binding.tvTwoButtonContent.text = content
    }

    private fun setClickListener() {
        binding.btnTwoButtonYes.setOnClickListener {
            onClick()
            requireNotNull(dialog).dismiss()
        }

        binding.btnTwoButtonNo.setOnClickListener {
            requireNotNull(dialog).dismiss()
        }
    }

    companion object {
        const val TAG = "TWO_BUTTON_DIALOG"
    }
}
