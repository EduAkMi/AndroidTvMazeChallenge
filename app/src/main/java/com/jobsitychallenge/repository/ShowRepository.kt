package com.jobsitychallenge.repository

import com.jobsitychallenge.api.APIService
import com.jobsitychallenge.api.ResponseApi

class ShowRepository {
    suspend fun getShowEpisodes(showId: Int): ResponseApi {
        return try {
            val responseGetEpisodes = APIService.tvMazeAPI.getShowEpisodes(showId)
            if (responseGetEpisodes.isSuccessful) {
                ResponseApi.Success(responseGetEpisodes.body())
            } else {
                ResponseApi.Error(responseGetEpisodes.errorBody().toString())
            }
        } catch (e: Exception) {
            ResponseApi.Error(e.message.toString())
        }
    }
}