package com.jobsitychallenge.ui.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jobsitychallenge.databinding.FragmentEpisodeBinding
import com.jobsitychallenge.model.Episodes
import com.jobsitychallenge.ui.utils.UIDisplay

class FragmentEpisode : BaseFragment() {
    private lateinit var mEpisode: Episodes
    private var mBinding: FragmentEpisodeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mEpisode = it.getSerializable(KEY_EPISODE) as Episodes
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mBinding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.let { binding ->
            UIDisplay.picasso(mEpisode.image, binding.imgPoster)
            UIDisplay.displaySeason(requireContext(), binding.txtSeason,
                mEpisode.season, mEpisode.number)
            binding.txtTitle.text = mEpisode.name
            binding.txtSummary.text = Html.fromHtml(mEpisode.summary, Html.FROM_HTML_MODE_COMPACT)
        }
        mBinding?.btnBack?.setOnClickListener { popBackStack() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    companion object {
        private const val KEY_EPISODE = "KEY_EPISODE"

        @JvmStatic
        fun newInstance(episodes: Episodes) = FragmentEpisode().apply {
            arguments = Bundle().apply {
                putSerializable(KEY_EPISODE, episodes)
            }
        }
    }
}