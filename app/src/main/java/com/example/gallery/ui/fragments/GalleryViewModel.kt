package com.example.gallery.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.gallery.R

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    val liveData: MutableLiveData<List<String>> = MutableLiveData()
    var listOfPicture: ArrayList<String> = ArrayList()

    fun getPictureList() {
        listOfPicture = arrayListOf(
            (getApplication<Application>().resources.getString(R.string.picture1)),
            (getApplication<Application>().resources.getString(R.string.picture2)),
            (getApplication<Application>().resources.getString(R.string.picture3)),
            (getApplication<Application>().resources.getString(R.string.picture4)),
            (getApplication<Application>().resources.getString(R.string.picture5))
        )
        liveData.value = listOfPicture
    }

    fun setImage(url: String) {
        listOfPicture.add(url)
        liveData.value = listOfPicture
    }
}