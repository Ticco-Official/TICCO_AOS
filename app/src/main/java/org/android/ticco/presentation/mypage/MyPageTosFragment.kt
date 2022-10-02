package org.android.ticco.presentation.mypage

import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.databinding.FragmentMyPageTosBinding
import org.android.ticco.presentation.base.BaseFragment

@AndroidEntryPoint
class MyPageTosFragment : BaseFragment<FragmentMyPageTosBinding>(R.layout.fragment_my_page_tos) {


    override fun initView() {
        setListeners()
    }

    private fun setListeners() {
        binding.btnConfirm.setOnClickListener { findNavController().popBackStack() }
    }

}