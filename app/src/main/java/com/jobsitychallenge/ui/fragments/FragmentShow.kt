package com.jobsitychallenge.ui.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.google.android.material.chip.Chip
import com.jobsitychallenge.R
import com.jobsitychallenge.databinding.FragmentShowBinding
import com.jobsitychallenge.db.organizer.ShowOrganizer
import com.jobsitychallenge.model.Episodes
import com.jobsitychallenge.model.Schedule
import com.jobsitychallenge.model.Shows
import com.jobsitychallenge.ui.adapters.AdapterEpisodes
import com.jobsitychallenge.ui.adapters.IEpisodeAdapter
import com.jobsitychallenge.ui.utils.IListDropdown
import com.jobsitychallenge.ui.utils.UIDisplay
import com.jobsitychallenge.ui.utils.UIDisplay.Companion.createPopupWindowWithLayout
import com.jobsitychallenge.vm.ShowViewModel

class FragmentShow : BaseFragment(), IEpisodeAdapter, IListDropdown {
    private lateinit var mShow: Shows
    private val mEpisodesList = mutableListOf<Episodes>()
    private val mSeasonList = mutableListOf<Episodes>()
    private val mAdapterEpisodes = AdapterEpisodes(mSeasonList, this)
    private var mPopUpCollections: PopupWindow? = null
    private lateinit var mShowOrganizer: ShowOrganizer
    private val mViewModel by lazy { ShowViewModel() }
    private var mBinding: FragmentShowBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mShow = it.getSerializable(KEY_SHOW) as Shows
        }

        mViewModel.mOnGetEpisodes.observe(this) {
            if (it == null) {
                return@observe
            }
            mEpisodesList.addAll(it)
            createPopup()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = FragmentShowBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding?.let { binding ->
            mShowOrganizer = ShowOrganizer(requireContext(), binding.btnFavorite, mShow)
            UIDisplay.picasso(mShow.image, binding.imgPoster)
            binding.txtTitle.text = mShow.name
            binding.txtSummary.text = Html.fromHtml(mShow.summary, Html.FROM_HTML_MODE_COMPACT)
            displayGenres(mShow.genres)
            displaySchedule(mShow.schedule)
        }

        mBinding?.recEpisodes?.adapter = mAdapterEpisodes
        mBinding?.btnBack?.setOnClickListener { popBackStack() }
        mBinding?.btnSeasons?.setOnClickListener { mPopUpCollections?.showAsDropDown(it) }

        mViewModel.getShowEpisodes(mShow.id)
    }

    private fun displayGenres(genres: MutableList<String>) {
        for (genre in genres) {
            val chip = Chip(requireContext())
            chip.text = genre
            chip.isCloseIconVisible = false
            chip.rippleColor = ColorStateList.valueOf(Color.TRANSPARENT)
            mBinding?.chipGroupGenres?.addView(chip)
        }
    }

    private fun displaySchedule(schedule: Schedule) {
        val stringSchedule = StringBuilder()
        for (day in schedule.days) {
            stringSchedule.append("$day - ${schedule.time}\n")
        }
        mBinding?.txtOnTV?.text = stringSchedule.toString()
    }

    private fun createPopup() {
        val seasonsNumber = mutableListOf<Int>()
        val seasonsString = mutableListOf<String>()
        for (episode in mEpisodesList) {
            if (!seasonsNumber.contains(episode.season)) {
                seasonsNumber.add(episode.season)
                seasonsString.add("Season ${episode.season}")
            }
        }
        mPopUpCollections = createPopupWindowWithLayout(requireActivity(),
            seasonsString.toTypedArray(), this)
        updateRecEpisodes(1)
    }

    override fun adapterDropdownOnItemSelected(position: Int) {
        mPopUpCollections?.dismiss()
        updateRecEpisodes(position + 1)
    }

    private fun updateRecEpisodes(season: Int) {
        mBinding?.btnSeasons?.text = getString(R.string.season_x, season.toString())
        val latestSize = mSeasonList.size
        mSeasonList.clear()
        mAdapterEpisodes.notifyItemRangeRemoved(0, latestSize)
        for (episode in mEpisodesList) {
            if (episode.season == season) {
                mSeasonList.add(episode)
            }
        }
        mAdapterEpisodes.notifyItemRangeInserted(0, mSeasonList.size)
    }

    override fun selectEpisode(episode: Episodes) {
        changeFragment(FragmentEpisode.newInstance(episode))
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    companion object {
        private const val KEY_SHOW = "KEY_SHOW"

        @JvmStatic
        fun newInstance(show: Shows) = FragmentShow().apply {
            arguments = Bundle().apply {
                putSerializable(KEY_SHOW, show)
            }
        }
    }
}