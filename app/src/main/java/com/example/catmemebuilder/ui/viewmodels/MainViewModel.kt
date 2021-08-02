package com.example.catmemebuilder.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catmemebuilder.data.remote.CataasManager.getImage
import com.example.catmemebuilder.data.remote.models.CataasResponse
import com.example.catmemebuilder.data.remote.repository.CataasRepository
import com.example.catmemebuilder.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){
    private val _response = MutableLiveData<Resource<CataasResponse>>()
    val response: LiveData<Resource<CataasResponse>> get() = _response

    private val _enteredText = MutableLiveData<String>()
    private val enteredText: LiveData<String> get() = _enteredText

    private val _selectedTextSize = MutableLiveData<String>()
    private val selectedTextSize: LiveData<String> get() = _selectedTextSize

    private val _selectedColor = MutableLiveData<String>()
    private val selectedColor: LiveData<String> get() = _selectedColor

    private val _selectedFilter = MutableLiveData<String>()
    private val selectedFilter: LiveData<String> get() = _selectedFilter

    private val _isGif = MutableLiveData<Boolean>()
    val isGif: LiveData<Boolean> get() = _isGif

    private val _catUrl = MutableLiveData<String>()
    val catUrl: LiveData<String> get() = _catUrl

    private val _gotCat = MutableLiveData(false)
    val gotCat: LiveData<Boolean> get() = _gotCat

    private fun getImage(){
        _response.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = CataasRepository.getImage(selectedFilter.value ?: "")
            _response.postValue(response)
        }
    }
    private fun getGif(){
        _response.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = CataasRepository.getGif(selectedFilter.value ?: "")
            _response.postValue(response)
        }
    }
    private fun getTextImage(){
        _response.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = CataasRepository.getTextImage(enteredText.value ?: "",
                selectedColor.value ?: "", selectedTextSize.value ?: "",
                selectedFilter.value ?: "")
            _response.postValue(response)
        }
    }
    private fun getTextGif(){
        _response.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = CataasRepository.getTextGif(enteredText.value ?: "",
                selectedColor.value ?: "", selectedTextSize.value ?: "",
                selectedFilter.value ?: "")
            _response.postValue(response)
        }
    }
    fun updateText(text: String){
        _enteredText.value = text
    }
    fun updateColor(color: String){
        _selectedColor.value = color.lowercase()
    }
    fun updateFilter(filter: String){
        _selectedFilter.value = filter.lowercase()
    }
    fun updateTextSize(size: String){
        _selectedTextSize.value = size
    }
    fun isGif(checked: Boolean){
        _isGif.value = checked
    }
    fun updateUrl(url: String){
        _catUrl.value = url
    }
    fun gotCat(cat : Boolean){
        _gotCat.value = cat
    }
    fun createMeme(){
        if(isGif.value == true){
            if(enteredText.value.isNullOrBlank()){
                getGif()
            }else{
                getTextGif()
            }
        }else{
            if(enteredText.value.isNullOrBlank()){
                getImage()
            }else{
                getTextImage()
            }
        }
        gotCat(true)
    }
}