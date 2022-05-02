package com.jobsitychallenge.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jobsitychallenge.api.ResponseApi
import com.jobsitychallenge.model.Episodes
import com.jobsitychallenge.repository.ShowRepository
import kotlinx.coroutines.launch

class ShowViewModel : ViewModel() {
    val mOnGetEpisodes: MutableLiveData<MutableList<Episodes>> = MutableLiveData()
    private val mShowRepo by lazy { ShowRepository() }

    fun getShowEpisodes(showId: Int) {
        viewModelScope.launch {
            val data = mShowRepo.getShowEpisodes(showId)
            if (data is ResponseApi.Success) {
                mOnGetEpisodes.value = data.data as MutableList<Episodes>
            } else {
                mOnGetEpisodes.value = null
            }
        }
    }
}