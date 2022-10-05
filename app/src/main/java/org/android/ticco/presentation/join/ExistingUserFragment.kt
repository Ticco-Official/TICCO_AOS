package org.android.ticco.presentation.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.android.ticco.R
import org.android.ticco.databinding.FragmentExistingUserBinding
import org.android.ticco.presentation.base.BaseFragment

@AndroidEntryPoint
class ExistingUserFragment : BaseFragment<FragmentExistingUserBinding>(R.layout.fragment_existing_user) {

    override fun initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            val action = ExistingUserFragmentDirections.actionExistingUserFragmentToHomeFragment()
            requireView().findNavController().navigate(action)
        }
    }

}