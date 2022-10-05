package org.android.ticco.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.window.Dialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.databinding.FragmentTicketCategoryBinding
import org.android.ticco.databinding.FragmentTicketEtcBinding
import org.android.ticco.presentation.util.DialogUtil

@AndroidEntryPoint
class TicketEtcFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTicketEtcBinding? = null
    val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketEtcBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setBottomSheetHeight()
        setListeners()
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

    private fun setListeners() {
        binding.clDownload.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.action_homeFragment_to_ticketSaveFragment)
        }
        binding.ivDelete.setOnClickListener {
            DialogUtil(1,::deleteTicket).show(childFragmentManager,TicketEtcFragment().tag)
        }
    }

    private fun deleteTicket() {
        homeViewModel.requestDeleteTicket(arguments?.getInt("id") ?: 0)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }


}