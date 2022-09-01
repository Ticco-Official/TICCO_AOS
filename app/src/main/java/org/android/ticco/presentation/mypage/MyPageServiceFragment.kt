package org.android.ticco.presentation.mypage

import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.databinding.FragmentMyPageServiceBinding
import org.android.ticco.presentation.base.BaseFragment

@AndroidEntryPoint
class MyPageServiceFragment :
    BaseFragment<FragmentMyPageServiceBinding>(R.layout.fragment_my_page_service) {
    override fun initView() {
        setListeners()
    }

    private fun setListeners() {
        binding.ivBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnInquire.setOnClickListener { }
    }

}