package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        restListOfShoes()
    }

    fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
        _shoeList.value = _shoeList.value
    }

    private fun restListOfShoes() {
        _shoeList.value = mutableListOf(
            Shoe(
                "Shoe 1",
                37.0,
                "Best Shoes",
                "Comfortable",
                mutableListOf("https://64.media.tumblr.com/tumblr_m5rn20s2mq1rxprxro9_250.png")
            ),
            Shoe(
                "Shoe 2",
                37.0,
                "Best Shoes",
                "Comfortable",
                mutableListOf("https://64.media.tumblr.com/tumblr_m5rn20s2mq1rxprxro9_250.png")
            ),
            Shoe(
                "Shoe 3",
                37.0,
                "Best Shoes",
                "Comfortable",
                mutableListOf("https://64.media.tumblr.com/tumblr_m5rn20s2mq1rxprxro9_250.png")
            ),
            Shoe(
                "Shoe 4",
                37.0,
                "Best Shoes",
                "Comfortable",
                mutableListOf("https://64.media.tumblr.com/tumblr_m5rn20s2mq1rxprxro9_250.png")
            )
        )
    }
}