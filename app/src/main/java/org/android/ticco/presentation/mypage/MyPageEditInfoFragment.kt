package org.android.ticco.presentation.mypage

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.databinding.FragmentMyPageEditInfoBinding
import org.android.ticco.presentation.base.BaseFragment

@AndroidEntryPoint
class MyPageEditInfoFragment :
    BaseFragment<FragmentMyPageEditInfoBinding>(R.layout.fragment_my_page_edit_info) {

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun initView() {
        binding.vm = myPageViewModel
        setUserInfo()
        setListeners()
        setTextWatcher()
        initIsSuccessObserver()
    }

    private fun setListeners() {
        binding.ivClear.setOnClickListener { myPageViewModel.nickname.value = "" }
        binding.ivBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnSave.setOnClickListener {
            myPageViewModel.editUserInfo()
        }
    }

    private fun setUserInfo() {
        myPageViewModel.setUserInfo()
    }

    private fun setTextWatcher() {
        binding.etNickname.addTextChangedListener {
            myPageViewModel.nickname.value = binding.etNickname.text.toString()
        }
    }

    private fun initIsSuccessObserver() {
        myPageViewModel.isSuccess.observe(viewLifecycleOwner) {
            if (it) findNavController().popBackStack()
        }
    }


}