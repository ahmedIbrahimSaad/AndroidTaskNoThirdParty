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
        fun provideRepo(context: Context): WordRepo {
            return WordRepo(context)
        }

        fun provideRemoteDataSource(context: Context): WordRemoteDataSource {
            return WordRemoteDataSource(context)
        }

        fun provideLocalDataSource(context: Context): WordLocalDataSource {
            return WordLocalDataSource(context)
        }

        fun provideMainViewModel(lifecycleOwner: ViewModelStoreOwner): MainViewModel {

            return ViewModelProvider(lifecycleOwner).get(MainViewModel::class.java)
        }

        fun provideStringUtiles():StringUtil{
            return StringUtil()
        }
        fun provideNetworkUtil():NetWorkUtil{
            return NetWorkUtil()
        }

    }

}