package com.saad.androidtasknothirdparty.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.*
import com.saad.androidtasknothirdparty.di.Injector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var occurrences  = HashMap<String, Int>()
    private var liveOccurrences= MutableLiveData<MutableMap<String,Int>>()
    private val scope = CoroutineScope(Job() + Dispatchers.IO)
    fun getWords(context: Context):MutableLiveData<MutableMap<String,Int>>?{

        scope.launch {occurrences=
            Injector.provideRepo(context).getWords(checkNetworkConnection(context)) as HashMap<String, Int>
            liveOccurrences.postValue(occurrences)
        }
        return liveOccurrences
    }
    private fun checkNetworkConnection(context: Context): Boolean {
        val cm: ConnectivityManager =
           context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = cm.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }


}