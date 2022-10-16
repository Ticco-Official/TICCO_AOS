package org.android.ticco.presentation.util

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.android.ticco.R
import org.android.ticco.databinding.DialogUtilBinding
import org.android.ticco.presentation.home.TicketCategoryFragment

class DialogUtil(private val dialogMode: Int, private val doAfterConfirm: () -> Unit) :
    DialogFragment() {

    private var _binding: DialogUtilBinding? = null
    val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext(), R.style.DisableDialogBackground)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogUtilBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
        setTitle()
        setDescription()
        setConfirmText()
        setCancelClickListener()
        setConfirmClickLister()
    }

    private fun setLayout() {
        requireNotNull(dialog).apply {
            requireNotNull(window).apply {
                setLayout(
                    resources.getDimensionPixelSize(R.dimen.dialogWidth),
                    resources.getDimensionPixelSize(R.dimen.dialogHeight)
                )
                setBackgroundDrawableResource(R.drawable.bg_rectangle_r12_white)
            }
        }
    }

    private fun setTitle() {
        binding.tvTitle.text = when (dialogMode) {
            IMAGE_PERMISSION -> getString(R.string.dialog_permission_title)
            TICKET_DELETE -> getString(R.string.dialog_delete_title)
            else -> throw IllegalStateException()
        }
    }

    private fun setDescription() {
        binding.tvDescription.text = when (dialogMode) {
            IMAGE_PERMISSION -> getString(R.string.dialog_permission_description)
            TICKET_DELETE -> getString(R.string.dialog_delete_description)
            else -> throw IllegalStateException()
        }
    }

    private fun setConfirmText() {
        binding.btnSelected.text = when (dialogMode) {
            IMAGE_PERMISSION -> getString(R.string.dialog_select)
            TICKET_DELETE -> getString(R.string.dialog_delete)
            else -> throw IllegalStateException()
        }
    }

    private fun setCancelClickListener() {
        binding.btnClose.setOnClickListener { dismiss() }
    }

    private fun setConfirmClickLister() {
        binding.btnSelected.setOnClickListener {
            doAfterConfirm()
            dismiss()
        }
    }

    companion object {
        const val IMAGE_PERMISSION = 0
        const val TICKET_DELETE = 1
    }
}