package com.jobsitychallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.jobsitychallenge.databinding.FragmentFavoritesBinding
import com.jobsitychallenge.db.organizer.FavoriteOrganizer
import com.jobsitychallenge.db.organizer.IFavOrganizer
import com.jobsitychallenge.model.Shows
import com.jobsitychallenge.ui.adapters.AdapterFavorites
import com.jobsitychallenge.ui.adapters.IFavAdapter

class FragmentFavorites : BaseFragment(), IFavAdapter, IFavOrganizer {
    private val mShowsList = mutableListOf<Shows>()
    private val mFavAdapter = AdapterFavorites(mShowsList, this)
    private var mFavOrganizer: FavoriteOrganizer? = null
    private var mBinding: FragmentFavoritesBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.recFavorites?.layoutManager = GridLayoutManager(requireContext(), 2)
        mBinding?.recFavorites?.adapter = mFavAdapter
        mBinding?.btnBack?.setOnClickListener { popBackStack() }

        if (mFavOrganizer == null) {
            mFavOrganizer = FavoriteOrganizer(requireContext(), this)
            mFavOrganizer?.getAllShows()
        }
    }

    override fun getAllShows(allShows: List<Shows>) {
        mShowsList.addAll(allShows)
        mShowsList.sortWith { ob1, ob2 -> ob1.name.lowercase().compareTo(ob2.name.lowercase()) }
        mFavAdapter.notifyItemRangeInserted(0, mShowsList.size)
    }

    override fun selectShow(show: Shows) {
        changeFragment(FragmentShow.newInstance(show))
    }

    override fun deleteFavorite(id: Int, position: Int) {
        mFavOrganizer?.removeShow(id, position)
    }

    override fun onShowRemoved(position: Int) {
        mShowsList.removeAt(position)
        mFavAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}