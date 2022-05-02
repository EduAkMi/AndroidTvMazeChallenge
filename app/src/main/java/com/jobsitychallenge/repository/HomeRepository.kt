package com.jobsitychallenge.repository

import com.jobsitychallenge.api.APIService
import com.jobsitychallenge.api.ResponseApi
import com.jobsitychallenge.utils.Constants.Companion.CURRENT_PAGE

class HomeRepository {
    suspend fun getShows(): ResponseApi {
        return try {
            val responseGetShows = APIService.tvMazeAPI.getShows(CURRENT_PAGE)
            if (responseGetShows.isSuccessful) {
                ResponseApi.Success(responseGetShows.body())
            } else {
                ResponseApi.Error(responseGetShows.errorBody().toString())
            }
        } catch (e: Exception) {
            ResponseApi.Error(e.message.toString())
        }
    }

    suspend fun getSearch(search: String): ResponseApi {
        return try {
            val responseGetShows = APIService.tvMazeAPI.getShowSearch(search)
            if (responseGetShows.isSuccessful) {
                ResponseApi.Success(responseGetShows.body())
            } else {
                ResponseApi.Error(responseGetShows.errorBody().toString())
            }
        } catch (e: Exception) {
            ResponseApi.Error(e.message.toString())
        }
    }
}