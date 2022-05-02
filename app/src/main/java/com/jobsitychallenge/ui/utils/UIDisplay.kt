package com.jobsitychallenge.ui.utils

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.widget.*
import com.jobsitychallenge.R
import com.jobsitychallenge.model.Image
import com.squareup.picasso.Picasso

class UIDisplay {
    companion object {
        fun displaySeason(context: Context, txtView: TextView, season: Int, number: Int) {
            txtView.text = context.getString(
                R.string.season_episode, season.toString(), number.toString())
        }

        fun createPopupWindowWithLayout(activity: Activity, popUpContents: Array<String>,
            iListDropdown: IListDropdown): PopupWindow {
            val popupWindow = PopupWindow(activity)
            val child = activity.layoutInflater.inflate(R.layout.popup_listview, null)
            val listView = child.findViewById<ListView>(R.id.listView)
            listView.adapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1,
                popUpContents)
            listView.onItemClickListener = CollectionsDropdown(iListDropdown)
            popupWindow.isFocusable = true
            popupWindow.width = WindowManager.LayoutParams.MATCH_PARENT
            popupWindow.height = WindowManager.LayoutParams.WRAP_CONTENT
            popupWindow.setBackgroundDrawable(null)
            popupWindow.contentView = child
            return popupWindow
        }

        fun picasso(image: Image?, imgView: ImageView) {
            image?.let { it ->
                when {
                    it.original != null -> Picasso.get().load(image.original)
                        .fit().placeholder(R.drawable.no_img).into(imgView)
                    it.medium != null -> Picasso.get().load(image.medium)
                        .fit().placeholder(R.drawable.no_img).into(imgView)
                    else -> Picasso.get().load(R.drawable.no_img).into(imgView)
                }
            } ?: run {
                Picasso.get().load(R.drawable.no_img).into(imgView)
            }
        }
    }
}