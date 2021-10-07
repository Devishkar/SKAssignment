package com.posist.sk.data

import android.util.Log
import com.posist.sk.data.service.SKService

class DataManager(val skService: SKService) {

    suspend fun getSkData(): ResponseResult<SKData> {
        return safeApiCall { skService.getSKData()}
    }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> retrofit2.Response<T>): ResponseResult<T> {
        return try {
            val myResp = call.invoke()
            if (myResp.isSuccessful) {
                ResponseResult.Success(myResp.body()!!)
            } else {
                ResponseResult.Error(myResp.errorBody()?.toString() ?: "Something goes wrong")
            }

        } catch (e: Exception) {
            Log.v("Api Exception", e.localizedMessage!!)
            ResponseResult.Error(e.message ?: "Internet error")
        }
    }
}