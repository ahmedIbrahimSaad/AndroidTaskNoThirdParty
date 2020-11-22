package com.saad.androidtasknothirdparty.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.saad.androidtasknothirdparty.NetWorkUtil
import com.saad.androidtasknothirdparty.StringUtil
import com.saad.androidtasknothirdparty.data.WordLocalDataSource
import com.saad.androidtasknothirdparty.data.WordRemoteDataSource
import com.saad.androidtasknothirdparty.data.WordRepo
import com.saad.androidtasknothirdparty.presentation.MainViewModel

class Injector {
    companion object {
        lateinit var context: Context

        fun provideRepo(): WordRepo {
            return WordRepo()
        }

        fun provideRemoteDataSource(): WordRemoteDataSource {
            return WordRemoteDataSource()
        }

        fun provideLocalDataSource(): WordLocalDataSource {
            return WordLocalDataSource()
        }

        fun provideMainViewModel(lifecycleOwner: ViewModelStoreOwner): MainViewModel {

            return ViewModelProvider(lifecycleOwner).get(MainViewModel::class.java)
        }

        fun provideNetworkUtil():NetWorkUtil{
            return NetWorkUtil()
        }
        fun provideContext():Context{
            return context
        }

    }

}