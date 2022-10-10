package org.android.ticco.presentation.home

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.databinding.FragmentTicketEtcBinding
import org.android.ticco.presentation.util.DialogUtil

@AndroidEntryPoint
class TicketEtcFragment(
    private val imageUrl : String,
    private var onDeleteListener: (() -> Unit)? = null
) : BottomSheetDialogFragment() {

    private var _binding: FragmentTicketEtcBinding? = null
    val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketEtcBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setBottomSheet()
        setListeners()
    }

    private fun setBottomSheet() {
        (dialog as BottomSheetDialog).behavior.apply {
            state = BottomSheetBehavior.STATE_EXPANDED // 높이 고정
            skipCollapsed = true
            isHideable = false
            isDraggable = false
        }
    }

    private fun setListeners() {
        binding.clDownload.setOnClickListener {
            if (checkImagePermission()) {
                dismiss()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTicketSaveFragment(imageUrl))
            } else DialogUtil(0, ::setImagePermission).show(
                childFragmentManager,
                this.tag
            )
        }
        binding.ivDelete.setOnClickListener {
            DialogUtil(1, ::deleteTicket).show(childFragmentManager, this.tag)
        }
    }

    private fun checkImagePermission(): Boolean {
        val writePermission = ActivityCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (writePermission == PackageManager.PERMISSION_GRANTED) return true
        return false
    }

    private fun setImagePermission() {
        val permissionStorage = arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        ActivityCompat.requestPermissions(requireActivity(), permissionStorage, 1)
    }

    private fun deleteTicket() {
        dismiss()
        onDeleteListener?.invoke()
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

}