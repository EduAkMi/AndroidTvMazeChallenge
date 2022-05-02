package com.jobsitychallenge.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jobsitychallenge.api.ResponseApi
import com.jobsitychallenge.model.SearchResponse
import com.jobsitychallenge.model.Shows
import com.jobsitychallenge.repository.HomeRepository
import com.jobsitychallenge.utils.Constants
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val mOnGetShows: MutableLiveData<MutableList<Shows>> = MutableLiveData()
    val mOnGetMoreShows: MutableLiveData<MutableList<Shows>> = MutableLiveData()
    val mOnGetSearch: MutableLiveData<MutableList<SearchResponse>> = MutableLiveData()
    private val mHomeRepo by lazy { HomeRepository() }

    fun getShows() {
        Constants.CURRENT_PAGE = 0
        viewModelScope.launch {
            val data = mHomeRepo.getShows()
            if (data is ResponseApi.Success) {
                mOnGetShows.value = data.data as MutableList<Shows>
            } else {
                mOnGetShows.value = null
            }
        }
    }

    fun searchShows(search: String) {
        viewModelScope.launch {
            val data = mHomeRepo.getSearch(search)
            if (data is ResponseApi.Success) {
                mOnGetSearch.value = data.data as MutableList<SearchResponse>
            } else {
                mOnGetSearch.value = null
            }
        }
    }

    fun getMoreShows() {
        Constants.CURRENT_PAGE += 1
        viewModelScope.launch {
            val data = mHomeRepo.getShows()
            if (data is ResponseApi.Success) {
                mOnGetMoreShows.value = data.data as MutableList<Shows>
            } else {
                mOnGetMoreShows.value = null
            }
        }
    }
}