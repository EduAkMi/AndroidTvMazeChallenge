package com.jobsitychallenge.db.organizer

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.jobsitychallenge.R
import com.jobsitychallenge.db.entities.*
import com.jobsitychallenge.model.Shows
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowOrganizer(
    context: Context,
    private val mBtn: MaterialButton,
    private val mShow: Shows
) : Organizer(context) {

    init {
        GlobalScope.launch {
            if (mDb.challengeDao().getShow(mShow.id) == null) {
                defineImageButton(0)
            } else {
                defineImageButton(1)
            }
        }
    }

    private suspend fun defineImageButton(int: Int) {
        val drawable: Drawable?
        when (int) {
            0 -> {
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_24)
                mBtn.setOnClickListener { addShow() }
            }
            1 -> {
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_favorite_24)
                mBtn.setOnClickListener { removeShow() }
            }
            else -> drawable = ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_24)
        }
        withContext(Dispatchers.Main) {
            mBtn.visibility = View.VISIBLE
            mBtn.icon = drawable
        }
    }

    private fun addShow() {
        GlobalScope.launch {
            val showEntity = ShowsEntity(mShow.id, mShow.name, mShow.summary)
            val imageEntity = ImageEntity(mShow.id, mShow.image.medium, mShow.image.original)
            val scheduleEntity = ScheduleEntity(mShow.id, mShow.schedule.time)
            mDb.challengeDao().addShow(showEntity)
            mDb.challengeDao().addImage(imageEntity)
            mDb.challengeDao().addSchedule(scheduleEntity)
            for (day in mShow.schedule.days) {
                mDb.challengeDao().addDay(DaysEntity(mShow.id, day))
            }
            for (genre in mShow.genres) {
                mDb.challengeDao().addGenre(GenreEntity(mShow.id, genre))
            }
            defineImageButton(1)
        }
    }

    private fun removeShow() {
        GlobalScope.launch {
            mDb.challengeDao().removeShow(mShow.id)
            mDb.challengeDao().removeShowGenres(mShow.id)
            mDb.challengeDao().removeShowImage(mShow.id)
            mDb.challengeDao().removeShowSchedule(mShow.id)
            mDb.challengeDao().removeShowScheduleDays(mShow.id)
            defineImageButton(0)
        }
    }
}