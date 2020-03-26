package com.example.newsapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examples.coding.newsapp.datasource.remote.API_KEY
import com.examples.coding.newsapp.datasource.remote.NewsApiService
import com.examples.coding.newsapp.model.news.NewsApiResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsListViewModel : ViewModel() {
    val newsList = MutableLiveData<NewsApiResponse>()
    init {
        NewsApiService
            .getNewsApiService()
            .getTopHeadlines("us", API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { result -> newsList.postValue(result) }
            .subscribe()
    }
}