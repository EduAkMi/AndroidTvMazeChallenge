package com.jobsitychallenge.ui.utils

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView

class CollectionsDropdown(private val iCollectionsDropdown: IListDropdown) :
    AdapterView.OnItemClickListener {
    override fun onItemClick(arg0: AdapterView<*>?, v: View, position: Int, id: Long) {
        val fadeInAnimation = AnimationUtils.loadAnimation(v.context, android.R.anim.fade_in)
        fadeInAnimation.duration = 10
        v.startAnimation(fadeInAnimation)

        iCollectionsDropdown.adapterDropdownOnItemSelected(position)
    }
}