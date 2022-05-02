package com.jobsitychallenge.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class ImageEntity(
    @ColumnInfo(name = "showId") val showId: Int,
    @ColumnInfo(name = "medium") val medium: String?,
    @ColumnInfo(name = "original") val original: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}