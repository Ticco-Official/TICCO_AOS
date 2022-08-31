package org.android.ticco.presentation.mypage

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.databinding.FragmentMyPageBinding
import org.android.ticco.presentation.base.BaseFragment

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun initView() {
        binding.vm = myPageViewModel
        setUserInfo()
        setListeners()
    }

    private fun setUserInfo() {
        myPageViewModel.setUserInfo()
    }

    private fun setListeners() {
        binding.ivMore.setOnClickListener { findNavController().navigate(R.id.action_myPageFragment_to_myPageEditInfoFragment) }
        binding.btnService.setOnClickListener { findNavController().navigate(R.id.action_myPageFragment_to_myPageServiceFragment)}
        binding.ivBack.setOnClickListener { findNavController().popBackStack() }
        binding.ivVersion.setOnClickListener { findNavController().navigate(R.id.action_myPageFragment_to_myPageVersionFragment) }
        binding.ivTos.setOnClickListener { findNavController().navigate(R.id.action_myPageFragment_to_myPageTosFragment) }

    }

}