package com.jobsitychallenge.model

import java.io.Serializable

class Episodes(
    val id: String,
    val name: String,
    val season: Int,
    val number: Int,
    val image: Image,
    val summary: String
) : Serializable {
}