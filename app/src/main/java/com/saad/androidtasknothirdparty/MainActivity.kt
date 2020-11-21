package com.saad.androidtasknothirdparty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rv_words: RecyclerView
    private lateinit var progress_bar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    fun initViews() {
        rv_words = findViewById(R.id.rv_words)
        progress_bar = findViewById(R.id.progress_bar)
    }
    fun hideLoading() {
        progress_bar.isIndeterminate = false
        progress_bar.visibility = View.GONE
    }

    fun showLoading() {
        progress_bar.isIndeterminate = true
        progress_bar.visibility = View.VISIBLE
    }
}