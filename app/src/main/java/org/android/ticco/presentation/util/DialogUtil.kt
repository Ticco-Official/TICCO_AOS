package org.android.ticco.presentation.util

import android.app.Dialog
import android.os.Bundle
import android.system.Os.remove
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.SnapshotApplyResult.Success.check
import androidx.fragment.app.DialogFragment
import org.android.ticco.R
import org.android.ticco.databinding.DialogUtilBinding
import java.lang.System.exit

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
        _binding = DialogUtilBinding.inflate(inflater,container,false)
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
            IMAGE_PERMISSION -> "사진 권한 설정"
            TICKET_DELETE -> "티켓을 삭제하시겠어요?"
            else -> throw IllegalStateException()
        }
    }

    private fun setDescription() {
        binding.tvDescription.text = when (dialogMode) {
            IMAGE_PERMISSION -> "이미지를 저장하기 위해선\n" + "사진 접근 권한이 필요해요"
            TICKET_DELETE -> "삭제된 티켓은 다시 복구되지 않으니\n" + "신중하게 결정해주세요!"

            else -> throw IllegalStateException()
        }
    }

    private fun setConfirmText() {
        binding.btnSelected.text = when (dialogMode) {
            IMAGE_PERMISSION -> "설정"
            TICKET_DELETE -> "삭제"
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

    companion object{
        const val IMAGE_PERMISSION = 0
        const val TICKET_DELETE = 1
    }
}