package com.saad.androidtasknothirdparty.presentation


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.saad.androidtasknothirdparty.R
import com.saad.androidtasknothirdparty.di.Injector


class MainActivity : AppCompatActivity() {
    private var occurrences = HashMap<String, Int>()
    private lateinit var viewModel: MainViewModel
    private lateinit var wordsAdapter: WordsAdapter
    private lateinit var rvWords: RecyclerView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModel()
        setAdapter()
        getWords()

    }

    private fun getWords() {
        showLoading()
        viewModel.getWords(this@MainActivity)!!.observe(this, { occurrence ->

            wordsAdapter.setWordsList(occurrence)
            hideLoading()
        })


    }

    private fun hideLoading() {
        progressBar.isIndeterminate = false
        progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        progressBar.isIndeterminate = true
        progressBar.visibility = View.VISIBLE
    }

    private fun initViewModel() {
        viewModel = Injector.provideMainViewModel(this)
    }

    private fun  setAdapter() {
        wordsAdapter = WordsAdapter(occurrences)
        rvWords.adapter = wordsAdapter
    }

    private fun initViews() {
        rvWords = findViewById(R.id.rv_words)
        progressBar = findViewById(R.id.progress_bar)
    }
}