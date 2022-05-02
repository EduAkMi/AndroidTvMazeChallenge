package com.jobsitychallenge.db.organizer

import android.content.Context
import androidx.room.Room
import com.jobsitychallenge.db.ChallengeDB
import com.jobsitychallenge.utils.Constants.Companion.DB_NAME

abstract class Organizer(
    val context: Context
) {
    var mDb: ChallengeDB = Room.databaseBuilder(
        context,
        ChallengeDB::class.java, DB_NAME
    ).build()
}