package org.android.ticco.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch
import org.android.ticco.R
import org.android.ticco.databinding.FragmentHomeBinding
import org.android.ticco.presentation.base.BaseFragment
import org.android.ticco.presentation.util.Event

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val ticketPagingAdapter = TicketPagingAdapter()

    override fun initView() {
        binding.vm = homeViewModel
        setListeners()
        initTicketData()
        initTicketViewPager()
        initTicketObserver()
    }

    private fun setListeners() {
        binding.ivMypage.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_myPageFragment) }
    }

    private fun initTicketData() {
        homeViewModel.requestTickets()
    }

    private fun initTicketObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.requestTickets().collectLatest {
                ticketPagingAdapter.submitData(it)
            }
            ticketPagingAdapter.loadStateFlow.collect {
                homeViewModel.isEmptyTicket.postValue(if(ticketPagingAdapter.snapshot().size==0) Event(true) else Event(false))
            }
        }
    }

    private fun initTicketViewPager() {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerHeight = resources.getDimensionPixelOffset(R.dimen.pageHeight)
        val screenHeight =
            resources.displayMetrics.heightPixels - resources.getDimensionPixelOffset(R.dimen.screenHeight)
        val offsetPx = screenHeight - pagerHeight - pageMarginPx
        binding.vpTicket.apply {
            adapter = ticketPagingAdapter
            orientation = ViewPager2.ORIENTATION_VERTICAL
            binding.vpTicket.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            offscreenPageLimit = 1
            setPageTransformer { page, position ->
                page.translationY = position * -offsetPx
            }
        }
    }

}