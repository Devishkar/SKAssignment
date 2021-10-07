package com.posist.sk.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.posist.sk.data.DataManager
import com.posist.sk.data.ResponseResult
import com.posist.sk.data.SKData
import com.posist.sk.data.SKDataItems
import com.posist.sk.data.service.SKService
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SKDataViewModel: ViewModel(){

    val dataList : MutableLiveData<List<SKDataItems>> = MutableLiveData<List<SKDataItems>>().apply {
        postValue(ArrayList())
    }
    private var gson = GsonBuilder()
        .setLenient()
        .create()
    private val skServiceRetrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
    private val okHttpClientBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)


    fun provideSkService(): SKService {
        return skServiceRetrofitBuilder
            .client(okHttpClientBuilder.build()).baseUrl("http://data.sportskeeda.com/")
            .build()
            .create(SKService::class.java)
    }
    val dataManager = DataManager(provideSkService())

    fun getSKData(){
        viewModelScope.launch {
            when(val retrofit = dataManager.getSkData()){
                is ResponseResult.Success -> {
                    dataList.postValue(retrofit.data.dataItems)
                }

                is ResponseResult.Error -> {}
            }

        }
    }
}