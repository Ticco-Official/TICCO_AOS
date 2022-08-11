package org.android.ticco.presentation.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = FRAGMENTS.size

    override fun createFragment(position: Int): Fragment = FRAGMENTS[position]

    companion object {
        private val FRAGMENTS = listOf(
            OnboardingOneFragment(),
            OnboardingTwoFragment(),
            OnboardingThreeFragment()
        )
    }
}