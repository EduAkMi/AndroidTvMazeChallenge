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

class AdapterFavorites(
    private val mShowsList: List<Shows>,
    private val iFavAdapter: IFavAdapter
) : RecyclerView.Adapter<AdapterFavorites.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.rec_favorites, parent, false))
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.txtTitle.text = mShowsList[position].name
        UIDisplay.picasso(mShowsList[position].image, holder.imgFolder)
        holder.btnDelete.setOnClickListener {
            iFavAdapter.deleteFavorite(mShowsList[position].id, position)
        }
        holder.itemView.setOnClickListener { iFavAdapter.selectShow(mShowsList[position]) }
    }

    override fun getItemCount(): Int {
        return mShowsList.size
    }

    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnDelete: MaterialButton = itemView.findViewById(R.id.btnDelete)
        val imgFolder: ImageView = itemView.findViewById(R.id.imgPoster)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
    }
}