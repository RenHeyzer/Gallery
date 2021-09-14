package com.example.gallery.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class GalleryViewModel : ViewModel() {

    val liveData: MutableLiveData<List<String>> = MutableLiveData()
    var listOfPicture: ArrayList<String> = ArrayList()

    fun getPictureList() {
        listOfPicture = arrayListOf(
            "https://p4.wallpaperbetter.com/wallpaper/116/880/876/anime-anime-girls-marumoru-vertical-simple-background-hd-wallpaper-preview.jpg",
            "https://p4.wallpaperbetter.com/wallpaper/523/98/463/digital-art-anime-anime-girls-yae-sakura-portrait-display-hd-wallpaper-preview.jpg",
            "https://p4.wallpaperbetter.com/wallpaper/227/583/604/anime-anime-girls-digital-art-artwork-2d-hd-wallpaper-preview.jpg",
            "https://p4.wallpaperbetter.com/wallpaper/123/200/904/portrait-display-vertical-landscape-wallpaper-preview.jpg",
            "https://p4.wallpaperbetter.com/wallpaper/367/257/149/anime-anime-girls-digital-art-artwork-2d-hd-wallpaper-preview.jpg"
        )
        liveData.value = listOfPicture
    }

    fun setImage(url: String) {
        listOfPicture.add(url)
        liveData.value = listOfPicture
    }

    fun randomAddImage() = listOfPicture[Random().nextInt(listOfPicture.size)]
}