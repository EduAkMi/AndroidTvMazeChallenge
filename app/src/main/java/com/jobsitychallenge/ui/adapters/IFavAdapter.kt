package com.jobsitychallenge.ui.adapters

import com.jobsitychallenge.model.Shows

interface IFavAdapter {
    fun selectShow(show: Shows)
    fun deleteFavorite(id: Int, position: Int)
}