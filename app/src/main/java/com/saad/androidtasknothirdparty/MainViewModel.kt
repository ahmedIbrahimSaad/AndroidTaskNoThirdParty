package com.saad.androidtasknothirdparty

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.saad.androidtasknothirdparty.di.Injector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    var occurrences  = HashMap<String, Int>()
    private var liveOccures= MutableLiveData<MutableMap<String,Int>>()
    private val scope = CoroutineScope(Job() + Dispatchers.IO)
    fun getWords(context: Context):MutableLiveData<MutableMap<String,Int>>?{

        scope.launch {occurrences=
            Injector.provideRepo(context).getWords(checkNetworkConnection(context)) as HashMap<String, Int>
            liveOccures.postValue(occurrences)
        }
        return liveOccures
    }
    private fun checkNetworkConnection(context: Context): Boolean {
        val cm: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = if(networkInfo != null) networkInfo.isConnected() else false
        return isConnected;
    }


}