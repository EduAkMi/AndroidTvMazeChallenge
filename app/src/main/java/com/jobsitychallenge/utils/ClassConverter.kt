package com.jobsitychallenge.utils

import com.jobsitychallenge.db.entities.DaysEntity
import com.jobsitychallenge.db.entities.GenreEntity
import com.jobsitychallenge.db.entities.ImageEntity
import com.jobsitychallenge.db.entities.ScheduleEntity
import com.jobsitychallenge.model.Image
import com.jobsitychallenge.model.Schedule

class ClassConverter {
    companion object {
        fun genreEntityToGenre(genresEntity: List<GenreEntity>): MutableList<String> {
            val genresList = mutableListOf<String>()
            for (genreEntity in genresEntity) {
                genresList.add(genreEntity.name)
            }
            return genresList
        }

        fun imageEntityToImage(imageEntity: ImageEntity): Image {
            return Image(imageEntity.medium, imageEntity.original)
        }

        fun scheduleEntityToSchedule(scheduleEntity: ScheduleEntity,
            daysEntity: List<DaysEntity>): Schedule {
            val daysList = mutableListOf<String>()
            for (dayEntity in daysEntity) {
                daysList.add(dayEntity.day)
            }
            return Schedule(scheduleEntity.time, daysList)
        }
    }
}