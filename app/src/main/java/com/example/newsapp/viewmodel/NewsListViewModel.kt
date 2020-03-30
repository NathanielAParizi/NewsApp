package com.example.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.datasource.remote.API_KEY
import com.example.newsapp.datasource.remote.NewsApiService
import com.example.newsapp.model.news.NewsApiResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsListViewModel : ViewModel() {
    private val newsListMutableLiveData = MutableLiveData<NewsApiResponse>()
    val newsListLiveData: LiveData<NewsApiResponse>
        get() = newsListMutableLiveData

    init {
        NewsApiService
            .getNewsApiService()
            .getTopHeadlines("us", API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { result -> newsListMutableLiveData.postValue(result) }
            .subscribe()
    }
}