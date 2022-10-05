package org.android.ticco.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.android.ticco.R
import org.android.ticco.databinding.FragmentTicketCategoryBinding
import org.android.ticco.databinding.FragmentTicketSortBinding


class TicketSortFragment : BottomSheetDialogFragment() {


    private var _binding: FragmentTicketSortBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketSortBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetHeight()
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


}