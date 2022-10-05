package org.android.ticco.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.android.ticco.R
import org.android.ticco.TiccoApplication.Companion.preferences
import org.android.ticco.databinding.FragmentHomeBinding
import org.android.ticco.presentation.base.BaseFragment


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val ticketPagingAdapter = TicketPagingAdapter()

    override fun initView() {
        binding.vm = homeViewModel
        setListeners()
        initTicketViewPager()
        initTicketObserver()
        setTicketAdapterClickLister()
        Log.d("TAG", "initView: ${preferences.accessToken}")
    }

    override fun onResume() {
        super.onResume()
        initTicketData()
    }

    private fun setListeners() {
        binding.ivMypage.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_myPageFragment) }
        binding.tvCategory.setOnClickListener {setTicketCategoryFragment()}
        binding.ivCategory.setOnClickListener {setTicketCategoryFragment()}
        binding.tvSort.setOnClickListener { TicketSortFragment().show(childFragmentManager,HomeFragment().tag) }
    }

    private fun setTicketCategoryFragment() {
        val bottomSheet = TicketCategoryFragment()
        bottomSheet.setCallback(object : TicketCategoryFragment.OnSendFromBottomSheetDialog {
            override fun sendValue(value: String) {
                homeViewModel.ticketCategory.value = value.lowercase()
                homeViewModel.requestTickets()
            }
        })
        bottomSheet.show(childFragmentManager,HomeFragment().tag)
    }

    private fun initTicketData() {
        homeViewModel.requestTickets()
    }

    private fun initTicketObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.requestTickets().collectLatest {
                ticketPagingAdapter.submitData(it)
                Log.d("TAG", "asdfasdfas : $it")
            }
            ticketPagingAdapter.loadStateFlow.collect {
                homeViewModel.isEmptyTicket.postValue(ticketPagingAdapter.snapshot().size==0)
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
        ticketPagingAdapter.setItemSetClickListner(object : TicketPagingAdapter.OnItemSetClickListener{
            override fun onSetClick(v: View, id: Int) {
                val args = Bundle()
                args.putInt("id", id)
                val bottomSheet =TicketEtcFragment()
                bottomSheet.arguments = args
                bottomSheet.show(childFragmentManager,HomeFragment().tag)
            }

        })
    }

}