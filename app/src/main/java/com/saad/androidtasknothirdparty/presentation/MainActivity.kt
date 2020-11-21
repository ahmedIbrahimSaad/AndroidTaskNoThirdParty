package com.saad.androidtasknothirdparty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.saad.androidtasknothirdparty.R
import com.saad.androidtasknothirdparty.di.Injector

class MainActivity : AppCompatActivity() {
    var occurrences = HashMap<String, Int>()
    private lateinit var viewModel: MainViewModel
    private lateinit var wordsAdapter: WordsAdapter
    private lateinit var rv_words: RecyclerView
    private lateinit var progress_bar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModel()
        setAdapte()
        getWords()

    }

    private fun getWords() {
        showLoading()
        viewModel.getWords(this@MainActivity)!!.observe(this, Observer { ocurrence ->

            wordsAdapter.setWordsList(ocurrence)
            hideLoading()
        })


    }

    fun hideLoading() {
        progress_bar.isIndeterminate = false
        progress_bar.visibility = View.GONE
    }

    fun showLoading() {
        progress_bar.isIndeterminate = true
        progress_bar.visibility = View.VISIBLE
    }

    fun initViewModel() {
        viewModel = Injector.provideMainViewModel(this)
    }

    fun setAdapte() {
        wordsAdapter = WordsAdapter(occurrences)
        rv_words.adapter = wordsAdapter
    }

    fun initViews() {
        rv_words = findViewById(R.id.rv_words)
        progress_bar = findViewById(R.id.progress_bar)
    }
}