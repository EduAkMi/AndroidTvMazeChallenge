package com.jobsitychallenge.db.organizer

import android.content.Context
import com.jobsitychallenge.model.Shows
import com.jobsitychallenge.utils.ClassConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteOrganizer(
    context: Context,
    private val iFavOrganizer: IFavOrganizer
) : Organizer(context) {

    fun getAllShows() {
        GlobalScope.launch {
            val showEntityList = mDb.challengeDao().getAllShows()
            val showList = mutableListOf<Shows>()
            for (showEntity in showEntityList) {
                val genresEntity = mDb.challengeDao().getShowGenres(showEntity.showId)
                val imageEntity = mDb.challengeDao().getShowImage(showEntity.showId)
                val scheduleEntity = mDb.challengeDao().getShowSchedule(showEntity.showId)
                val daysEntity = mDb.challengeDao().getShowScheduleDays(showEntity.showId)
                val genres = ClassConverter.genreEntityToGenre(genresEntity)
                val image = ClassConverter.imageEntityToImage(imageEntity)
                val schedule = ClassConverter.scheduleEntityToSchedule(scheduleEntity, daysEntity)
                val show = Shows(showEntity.showId, showEntity.name,
                    genres, schedule, image, showEntity.summary)
                showList.add(show)
            }
            withContext(Dispatchers.Main) {
                iFavOrganizer.getAllShows(showList)
            }
        }
    }

    fun removeShow(id: Int, position: Int) {
        GlobalScope.launch {
            mDb.challengeDao().removeShow(id)
            mDb.challengeDao().removeShowGenres(id)
            mDb.challengeDao().removeShowImage(id)
            mDb.challengeDao().removeShowSchedule(id)
            mDb.challengeDao().removeShowScheduleDays(id)
            withContext(Dispatchers.Main) {
                iFavOrganizer.onShowRemoved(position)
            }
        }
    }
}