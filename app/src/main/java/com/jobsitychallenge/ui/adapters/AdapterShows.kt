package com.jobsitychallenge.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.jobsitychallenge.R
import com.jobsitychallenge.model.Shows
import com.jobsitychallenge.ui.utils.UIDisplay

class AdapterShows(
    private val mShowsList: List<Shows>,
    private val iShowAdapter: IShowAdapter
) : RecyclerView.Adapter<AdapterShows.ShowsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsViewHolder {
        return ShowsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.rec_shows, parent, false))
    }

    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) {
        holder.txtTitle.text = mShowsList[position].name
        UIDisplay.picasso(mShowsList[position].image, holder.imgFolder)
        holder.btnDetails.setOnClickListener { iShowAdapter.selectShow(mShowsList[position]) }
        holder.itemView.setOnClickListener { iShowAdapter.selectShow(mShowsList[position]) }
    }

    override fun getItemCount(): Int {
        return mShowsList.size
    }

    class ShowsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFolder: ImageView = itemView.findViewById(R.id.imgPoster)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val btnDetails: MaterialButton = itemView.findViewById(R.id.btnDetails)
    }
}