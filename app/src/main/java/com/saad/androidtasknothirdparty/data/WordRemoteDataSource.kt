package com.saad.androidtasknothirdparty.data

import android.content.Context
import android.util.Log
import com.saad.androidtasknothirdparty.R
import com.saad.androidtasknothirdparty.db.WordsControl
import com.saad.androidtasknothirdparty.di.Injector
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WordRemoteDataSource() {
    private var occurrences = HashMap<String, Int>()
    fun getWordsFromNetwork(): MutableMap<String, Int>? {
        Injector.provideNetworkUtil().httpGet(Injector.provideContext().getString(R.string.url))?.let { occurrences =
            Injector.provideStringUtiles().splitTextBySpace(it) as HashMap<String, Int>
        }
        WordsControl(Injector.provideContext()).create(occurrences)
        return occurrences
    }

}