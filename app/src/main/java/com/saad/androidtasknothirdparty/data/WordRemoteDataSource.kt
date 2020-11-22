package com.saad.androidtasknothirdparty.data

import com.saad.androidtasknothirdparty.R
import com.saad.androidtasknothirdparty.StringUtil
import com.saad.androidtasknothirdparty.db.WordsControl
import com.saad.androidtasknothirdparty.di.Injector

class WordRemoteDataSource {
    private var occurrences = HashMap<String, Int>()
    fun getWordsFromNetwork(): MutableMap<String, Int>? {
        Injector.provideNetworkUtil().httpGet(Injector.provideContext().getString(R.string.url))?.let { occurrences =
            StringUtil.splitTextBySpace(it) as HashMap<String, Int>
        }
        WordsControl(Injector.provideContext()).create(occurrences)
        return occurrences
    }

}