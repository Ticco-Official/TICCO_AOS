package org.android.ticco.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.databinding.FragmentTicketSortBinding

@AndroidEntryPoint
class TicketSortFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTicketSortBinding? = null
    val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private var listener: OnSendFromBottomSheetDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketSortBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setBottomSheet()
        setListeners()
    }

    private fun setListeners() {
        binding.btnClose.setOnClickListener { dismiss() }
        binding.btnSelected.setOnClickListener {
            listener?.sendValue(homeViewModel.ticketSort.value ?: "")
            dismiss()
        }
    }

    private fun setBottomSheet() {
        (dialog as BottomSheetDialog).behavior.apply {
            state = BottomSheetBehavior.STATE_EXPANDED // 높이 고정
            skipCollapsed = true
            isHideable = false
            isDraggable = false
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    fun setSortFragmentCallback(listener: OnSendFromBottomSheetDialog) {
        this.listener = listener
    }

}