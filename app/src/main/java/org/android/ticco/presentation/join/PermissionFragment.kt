package org.android.ticco.presentation.join

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.databinding.FragmentPermissionBinding
import org.android.ticco.presentation.base.BaseFragment
import org.android.ticco.presentation.util.isGranted

@AndroidEntryPoint
class PermissionFragment : BaseFragment<FragmentPermissionBinding>(R.layout.fragment_permission) {

    private val action: NavDirections = PermissionFragmentDirections.actionPermissionFragmentToOnboardingFragment()

    override fun initView() {
        setButtonClickListener()

        if (requireContext().isGranted(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )) {
            requireView().findNavController().navigate(action)
        }
    }

    fun setButtonClickListener() {
        binding.btnConfirm.setOnClickListener {
            requireView().findNavController().navigate(action)
        }
    }
}