package com.sablania.googleimages.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sablania.googleimages.pojos.GoogleImageResp


class GoogleImageRepository(val context: Application) {
    val TAG = GoogleImageRepository::class.java.simpleName

    val googleImageLiveData = MutableLiveData<GoogleImageResp>()

    fun getGoogleImageLiveData(): LiveData<GoogleImageResp> = googleImageLiveData

    fun searchGoogleImage(searchStr: String?) {
        var api =
            "https://www.googleapis.com/customsearch/v1?q=$searchStr&cx=011476162607576381860:ra4vmliv9ti&key=AIzaSyDoYFAxDR1NfAraumLZigWveB1s-zCYA5s"
        val request = JsonObjectRequest(
            Request.Method.GET,
            api,
            null,
            Response.Listener { response ->
                val resp: GoogleImageResp =
                    Gson().fromJson<GoogleImageResp>(
                        response.toString(), GoogleImageResp::class.java
                    )
                googleImageLiveData.postValue(resp)
                Log.i(TAG, "data fetched from api")
            },
            Response.ErrorListener {
                Log.i(TAG, "error")
//                errorLiveData.postValue(true)
            })
        Volley.newRequestQueue(context).add(request)
    }

}
