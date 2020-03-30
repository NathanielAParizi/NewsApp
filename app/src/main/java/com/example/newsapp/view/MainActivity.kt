package com.example.newsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.viewmodel.NewsListViewModel
import com.examples.coding.newsapp.model.news.Article
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnNewsItemClickListener {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : NewsListViewModel
    var list = ArrayList<Article>()
    val adapter = NewsListAdapter( list,this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
        rvNewsList.layoutManager = LinearLayoutManager(this)
        rvNewsList.adapter = adapter
        viewModel.newsListLiveData.observe(this, Observer { adapter.setNewsList(it.articles) })
        binding.viewmodel = viewModel
    }

    override fun onItemClicked(article: Article, position: Int) {

        Toast.makeText(this, article.url,LENGTH_LONG).show()
    }


}
