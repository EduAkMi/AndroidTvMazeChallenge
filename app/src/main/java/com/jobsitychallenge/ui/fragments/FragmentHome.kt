package com.jobsitychallenge.ui.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jobsitychallenge.databinding.FragmentHomeBinding
import com.jobsitychallenge.model.Shows
import com.jobsitychallenge.ui.adapters.AdapterShows
import com.jobsitychallenge.ui.adapters.IShowAdapter
import com.jobsitychallenge.vm.HomeViewModel

class FragmentHome : BaseFragment(), IShowAdapter {
    private val mShowsList = mutableListOf<Shows>()
    private val mAdapterShows = AdapterShows(mShowsList, this)
    private val mViewModel by lazy { HomeViewModel() }
    private var mBinding: FragmentHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.mOnGetShows.observe(this) {
            mBinding?.progressLoading?.visibility = View.GONE
            if (it == null) {
                return@observe
            }
            clearRecycler()
            updateRecyclerAndSetOnScroll(it)
        }
        mViewModel.mOnGetSearch.observe(this) {
            if (it == null) {
                return@observe
            }
            val showsList = mutableListOf<Shows>()
            for (searchResponse in it) {
                showsList.add(searchResponse.show)
            }
            clearRecycler()
            updateRecycler(showsList)
        }
        mViewModel.mOnGetMoreShows.observe(this) {
            mBinding?.progressLoading?.visibility = View.GONE
            if (it == null) {
                return@observe
            }
            updateRecyclerAndSetOnScroll(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.recShows?.layoutManager = GridLayoutManager(requireContext(), 2)
        mBinding?.recShows?.adapter = mAdapterShows

        mBinding?.edtSearch?.setOnEditorActionListener(searchResult())
        mBinding?.btnFavs?.setOnClickListener { changeFragment(FragmentFavorites()) }

        if (mShowsList.isEmpty()) {
            mViewModel.getShows()
        }
    }

    private fun searchResult(): TextView.OnEditorActionListener {
        return object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (p0?.text.toString().isEmpty()) {
                        mViewModel.getShows()
                    } else {
                        mViewModel.searchShows(p0?.text.toString())
                    }
                    return true
                }
                return false
            }
        }
    }

    private fun clearRecycler() {
        val latestSize = mShowsList.size
        mShowsList.clear()
        mAdapterShows.notifyItemRangeRemoved(0, latestSize)
    }

    private fun updateRecycler(showsList: MutableList<Shows>) {
        val posStart = mShowsList.size
        mShowsList.addAll(showsList)
        mAdapterShows.notifyItemRangeInserted(posStart, mShowsList.size)
        clearOnScrollFromRecycler()
    }

    private fun updateRecyclerAndSetOnScroll(showsList: MutableList<Shows>) {
        updateRecycler(showsList)
        if (showsList.size > 200) {
            setOnScrollForRecycler()
        }
    }

    private fun setOnScrollForRecycler() {
        mBinding?.recShows?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    mViewModel.getMoreShows()
                    mBinding?.progressLoading?.visibility = View.VISIBLE
                    clearOnScrollFromRecycler()
                }
            }
        })
    }

    private fun clearOnScrollFromRecycler() {
        mBinding?.recShows?.clearOnScrollListeners()
    }

    override fun selectShow(show: Shows) {
        changeFragment(FragmentShow.newInstance(show))
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}