package org.android.ticco.presentation.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.databinding.FragmentPermissionBinding
import org.android.ticco.presentation.base.BaseFragment

@AndroidEntryPoint
class PermissionFragment : BaseFragment<FragmentPermissionBinding>(R.layout.fragment_permission) {

    private val action: NavDirections = PermissionFragmentDirections.actionPermissionFragmentToOnboardingFragment()

    override fun initView() {
        setButtonClickListener()
    }

    fun setButtonClickListener() {
        binding.btnConfirm.setOnClickListener {
            requireView().findNavController().navigate(action)
        }
    }
}