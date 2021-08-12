package com.example.catmemebuilder.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catmemebuilder.data.remote.models.CataasResponse
import com.example.catmemebuilder.data.remote.repository.CataasRepository
import com.example.catmemebuilder.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){
    private val _response = MutableLiveData<Resource<CataasResponse>>()
    val response: LiveData<Resource<CataasResponse>> get() = _response

    private val _catUrl = MutableLiveData<String>()
    val catUrl: LiveData<String> get() = _catUrl

    private var enteredText: String = ""

    private var selectedTextSize: String = ""

    private var selectedColor: String = ""

    private var selectedFilter: String = ""

    var isGif: Boolean = false

    var gotCat:Boolean = false

    private fun getImage(){
        _response.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = CataasRepository.getImage(selectedFilter)
            _response.postValue(response)
        }
    }
    private fun getGif(){
        _response.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = CataasRepository.getGif(selectedFilter)
            _response.postValue(response)
        }
    }
    private fun getTextImage(){
        _response.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = CataasRepository.getTextImage(enteredText,
                selectedColor, selectedTextSize,
                selectedFilter)
            _response.postValue(response)
        }
    }
    private fun getTextGif(){
        _response.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = CataasRepository.getTextGif(enteredText,
                selectedColor, selectedTextSize,
                selectedFilter)
            _response.postValue(response)
        }
    }
    fun updateText(text: String){
        enteredText = text
    }
    fun updateColor(color: String){
        selectedColor = color.lowercase()
    }
    fun updateFilter(filter: String){
        selectedFilter = filter.lowercase()
    }
    fun updateTextSize(size: String){
        selectedTextSize = size
    }
    fun isGif(checked: Boolean){
        isGif = checked
    }
    fun updateUrl(url: String){
        _catUrl.value = url
    }
    fun gotCat(cat : Boolean){
        gotCat = cat
    }
    fun createMeme(){
        if(isGif){
            if(enteredText.isBlank()){
                getGif()
            }else{
                getTextGif()
            }
        }else{
            if(enteredText.isBlank()){
                getImage()
            }else{
                getTextImage()
            }
        }
        gotCat(true)
    }
}