package com.sablania.googleimages.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sablania.googleimages.pojos.GoogleImageResp
import com.sablania.googleimages.repositories.GoogleImageRepository

class GoogleImageViewModel(application: Application) : AndroidViewModel(application) {
    private val googleImageRepo = GoogleImageRepository(application)

    fun getGoogleImageLiveData(): LiveData<GoogleImageResp> =
        googleImageRepo.getGoogleImageLiveData()

    fun searchGoogleImage(searchStr: String?) {
        googleImageRepo.searchGoogleImage(searchStr)
    }
}
