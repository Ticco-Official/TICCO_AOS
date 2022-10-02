package org.android.ticco.presentation.join

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.android.ticco.R
import org.android.ticco.databinding.FragmentJoinBinding
import org.android.ticco.presentation.base.BaseFragment

@AndroidEntryPoint
class JoinFragment : BaseFragment<FragmentJoinBinding>(R.layout.fragment_join) {

    private val viewModel by viewModels<JoinViewModel>()

    override fun initView() {
        binding.vm = viewModel
        setProfileClickListener()
        initListener()
        collectFlow()
    }

    private fun setProfileClickListener() {
        binding.layoutProfile.setOnClickListener {
            //todo : open camera or gallery
        }
    }

    private fun initListener() {

        // 시작하기 버튼 활성화
        binding.etNickname.addTextChangedListener {
            viewModel.setNickname(it.toString())
        }

        // 뒤로가기 버튼
        binding.btnBack.setOnClickListener {  }

        // 시작하기 버튼
        binding.btnStart.setOnClickListener {
            viewModel.updateProfile()

        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isSuccess.collectLatest {
                    when(it) {
                        true -> {
                            // move to main
                        }
                        false -> {
                            // error dialog
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}