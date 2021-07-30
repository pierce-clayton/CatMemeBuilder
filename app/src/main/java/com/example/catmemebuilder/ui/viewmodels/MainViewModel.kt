package com.example.catmemebuilder.ui.viewmodels

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
    val enteredText: LiveData<String> get() = _enteredText

    private val _selectedTextSize = MutableLiveData<String>()
    val selectedTextSize: LiveData<String> get() = _selectedTextSize

    private val _selectedColor = MutableLiveData<String>()
    val selectedColor: LiveData<String> get() = _selectedColor

    private val _selectedFilter = MutableLiveData<String>()
    val selectedFilter: LiveData<String> get() = _selectedFilter

    private val _isGif = MutableLiveData<Boolean>()
    val isGif: LiveData<Boolean> get() = _isGif

    fun getImage(){
        _response.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = CataasRepository.getImage()
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
        _selectedFilter.value = filter
    }
    fun updateTextSize(size: String){
        _selectedTextSize.value = size
    }
    fun isGif(checked: Boolean){
        _isGif.value = checked
    }
    fun createMeme(){
        /* no-op */
    }
}