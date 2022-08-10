package org.android.ticco.presentation.onboarding

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import org.android.ticco.R
import org.android.ticco.data.datasource.remote.auth.AuthRequest
import org.android.ticco.databinding.FragmentOnboardingBinding
import org.android.ticco.presentation.base.BaseFragment

@AndroidEntryPoint
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>(R.layout.fragment_onboarding) {

    private val viewModel by viewModels<OnboardingViewModel>()

    override fun initView() {
        binding.apply {
            val viewPagerAdapter = ViewPagerAdapter(requireActivity())
            binding.viewPager.adapter = viewPagerAdapter
            binding.indicator.attachTo(binding.viewPager)
            setLoginButtonClickListener()
            collectFlow()
        }
    }

    private fun setLoginButtonClickListener() {
        binding.btnKakaoLogin.setOnClickListener {
            setKakaoLogin()
        }
    }

    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("login", "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i("login", "카카오계정으로 로그인 성공 ${token.accessToken}")
            viewModel.requestLogin(AuthRequest("KAKAO", token.accessToken))
        }
    }

    private fun setKakaoLogin() {
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                if (error != null) {
                    Log.e("login", "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
                } else if (token != null) {
                    Log.i("login", "카카오톡으로 로그인 성공 ${token.accessToken}")
                    viewModel.requestLogin(AuthRequest("KAKAO", token.accessToken))
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

    private fun collectFlow() {
        lifecycleScope.launchWhenCreated {
            viewModel.isLoggedIn.collect {
                Log.d("온보딩", it.toString())
            }
        }
    }
}