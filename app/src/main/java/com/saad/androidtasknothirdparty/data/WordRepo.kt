package com.saad.androidtasknothirdparty.data

import android.content.Context
import com.saad.androidtasknothirdparty.di.Injector


class WordRepo(val context: Context) {
    fun getWords(isConnected: Boolean): MutableMap<String, Int>? {
        if (isConnected) {
            return Injector.provideRemoteDataSource(context).getWordsFromNetwork()
        } else {
            return Injector.provideLocalDataSource(context).getWordsFromDatabase()
        }
    }


}