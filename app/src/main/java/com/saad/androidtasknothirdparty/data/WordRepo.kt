package com.saad.androidtasknothirdparty.data

import android.content.Context
import com.saad.androidtasknothirdparty.di.Injector
class WordRepo() {
    fun getWords(isConnected: Boolean): MutableMap<String, Int>? {
        return if (isConnected) {
            Injector.provideRemoteDataSource(Injector.provideContext()).getWordsFromNetwork()
        } else {
            Injector.provideLocalDataSource(Injector.provideContext()).getWordsFromDatabase()
        }
    }
}