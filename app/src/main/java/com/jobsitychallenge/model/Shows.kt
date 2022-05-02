package com.jobsitychallenge.model

import java.io.Serializable

class Shows(
    val id: Int,
    val name: String,
    val genres: MutableList<String>,
    val schedule: Schedule,
    val image: Image,
    val summary: String
) : Serializable {

}