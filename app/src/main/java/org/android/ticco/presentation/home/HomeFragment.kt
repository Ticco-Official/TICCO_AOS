package org.android.ticco.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.android.ticco.R
import org.android.ticco.databinding.FragmentHomeBinding
import org.android.ticco.presentation.base.BaseFragment


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val ticketPagingAdapter by lazy { TicketPagingAdapter() }

    override fun initView() {
        binding.vm = homeViewModel

        setTicketCategoryListener()
        setMoveToMyPageFragment()
        setTicketSortListener()
        initTicketViewPager()
        initTicketCount()
        setTicketAdapterClickLister()
    }

    override fun onResume() {
        super.onResume()
        initTicketData()
        initTicketObserver()
    }

    private fun setTicketCategoryListener() {
        binding.tvCategory.setOnClickListener { setTicketCategoryFragment() }
        binding.ivCategory.setOnClickListener { setTicketCategoryFragment() }
    }

    private fun setMoveToMyPageFragment() {
        binding.ivMypage.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_myPageFragment) }

    }

    private fun setTicketSortListener() {
        binding.tvSort.setOnClickListener {
            setTicketSortFragment()
        }
        binding.ivSort.setOnClickListener {
            setTicketSortFragment()
        }
    }

    private fun setTicketSortFragment() {
        val bottomSheet = TicketSortFragment()
        bottomSheet.setSortFragmentCallback(object : OnSendFromBottomSheetDialog {
            override fun sendValue(value: String) {
                homeViewModel.ticketSort.value = value
                initTicketData()
                initTicketObserver()
            }

        })
        bottomSheet.show(childFragmentManager,HomeFragment().tag)
    }

    private fun setTicketCategoryFragment() {
        val bottomSheet = TicketCategoryFragment()
        bottomSheet.setCallback(object : OnSendFromBottomSheetDialog {
            override fun sendValue(value: String) {
                homeViewModel.ticketCategory.value = value
                initTicketData()
                initTicketObserver()
            }
        })
        bottomSheet.show(childFragmentManager, HomeFragment().tag)
    }

    private fun initTicketData() {
        homeViewModel.requestTickets()
    }

    private fun initTicketObserver() {
        lifecycleScope.launch  {
            homeViewModel.requestTickets().collectLatest {
                ticketPagingAdapter.submitData(it)
            }
        }
    }

    private fun initTicketCount() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                ticketPagingAdapter.loadStateFlow.collect {
                    homeViewModel.isEmptyTicket.postValue(ticketPagingAdapter.itemCount == 0)
                }
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

    private fun setTicketAdapterClickLister() {
        ticketPagingAdapter.setItemSetClickListener(object :
            TicketPagingAdapter.OnItemSetClickListener {
            override fun onSetClick(v: View, id: Int, image:String, position: Int) {
                val args = Bundle().apply {
                    putInt("id", id)
                }
                val bottomSheet = TicketEtcFragment(image) { deleteTicket(id) }.apply {
                    arguments = args
                }
                bottomSheet.show(childFragmentManager, HomeFragment().tag)
            }

        })
    }

    fun deleteTicket(id: Int) {
        homeViewModel.requestDeleteTicket(id)
        initTicketData()
        initTicketObserver()
    }

}