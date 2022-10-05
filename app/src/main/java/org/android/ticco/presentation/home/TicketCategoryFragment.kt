package org.android.ticco.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.TiccoApplication
import org.android.ticco.databinding.FragmentTicketCategoryBinding

@AndroidEntryPoint
class TicketCategoryFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTicketCategoryBinding? = null
    val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private var listener: OnSendFromBottomSheetDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketCategoryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setBottomSheetHeight()
        setCloseClickListener()
    }

    private fun setCloseClickListener() {
        binding.btnClose.setOnClickListener { dismiss() }
        binding.btnSelected.setOnClickListener {
            Log.d("TAG", "initView22: ${homeViewModel.ticketCategory.value}")
            listener?.sendValue(homeViewModel.ticketCategory.value?.uppercase() ?: "")
            //homeViewModel.requestTickets()
            dismiss()
        }
    }

    private fun setBottomSheetHeight() {
        (dialog as BottomSheetDialog).behavior.apply {
            state = BottomSheetBehavior.STATE_EXPANDED // 높이 고정
            skipCollapsed = true
            isHideable = false
            isDraggable = false
        }
        /*binding.clCategory.layoutParams.height =
            (resources.displayMetrics.heightPixels * 0.76).toInt()*/
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    interface OnSendFromBottomSheetDialog {
        fun sendValue(value: String)
    }

    fun setCallback(listener: OnSendFromBottomSheetDialog) {
        this.listener = listener
    }

}