package com.jobsitychallenge.db.organizer

import com.jobsitychallenge.model.Shows

interface IFavOrganizer {
    fun getAllShows(allShows: List<Shows>)
    fun onShowRemoved(position: Int)
}