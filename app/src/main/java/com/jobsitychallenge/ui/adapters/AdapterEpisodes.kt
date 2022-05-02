package com.jobsitychallenge.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jobsitychallenge.R
import com.jobsitychallenge.model.Episodes
import com.jobsitychallenge.ui.utils.UIDisplay

class AdapterEpisodes(
    private val mEpisodesList: List<Episodes>,
    private val iEpisodeAdapter: IEpisodeAdapter
) : RecyclerView.Adapter<AdapterEpisodes.EpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.rec_episodes, parent, false))
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        UIDisplay.displaySeason(holder.itemView.context,
            holder.txtSeason,
            mEpisodesList[position].season,
            mEpisodesList[position].number)
        holder.txtTitle.text = mEpisodesList[position].name
        UIDisplay.picasso(mEpisodesList[position].image, holder.imgFolder)
        holder.itemView.setOnClickListener {
            iEpisodeAdapter.selectEpisode(mEpisodesList[position])
        }
    }

    override fun getItemCount(): Int {
        return mEpisodesList.size
    }

    class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFolder: ImageView = itemView.findViewById(R.id.imgPoster)
        val txtSeason: TextView = itemView.findViewById(R.id.txtSeason)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
    }
}