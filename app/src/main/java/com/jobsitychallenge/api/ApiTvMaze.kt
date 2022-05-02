package com.jobsitychallenge.api

import com.jobsitychallenge.model.Episodes
import com.jobsitychallenge.model.SearchResponse
import com.jobsitychallenge.model.Shows
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiTvMaze {
    @GET("shows")
    suspend fun getShows(@Query("page") page: Int
    ): Response<List<Shows>>

    @GET("search/shows")
    suspend fun getShowSearch(@Query("q") search: String
    ): Response<List<SearchResponse>>


    @GET("shows/{id}/episodes")
    suspend fun getShowEpisodes(@Path("id") id: Int
    ): Response<List<Episodes>>
}