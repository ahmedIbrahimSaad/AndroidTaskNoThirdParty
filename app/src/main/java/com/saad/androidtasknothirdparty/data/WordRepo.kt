package com.saad.androidtasknothirdparty.data

import android.content.Context
import com.saad.androidtasknothirdparty.di.Injector
class WordRepo(private val context: Context) {
    fun getWords(isConnected: Boolean): MutableMap<String, Int>? {
        return if (isConnected) {
            Injector.provideRemoteDataSource(context).getWordsFromNetwork()
        } else {
            Injector.provideLocalDataSource(context).getWordsFromDatabase()
        }
    }
}