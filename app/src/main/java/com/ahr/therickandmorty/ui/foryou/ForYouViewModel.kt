package com.ahr.therickandmorty.ui.foryou

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.therickandmorty.data.TheRickAndMortyRepository
import com.ahr.therickandmorty.domain.Response
import com.ahr.therickandmorty.domain.entity.ForYou
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(private val theRickAndMortyRepository: TheRickAndMortyRepository) :
    ViewModel() {

    private val _forYouContent = MutableLiveData<Response<Map<String, List<ForYou>>>>()
    val forYouContent: LiveData<Response<Map<String, List<ForYou>>>> get() = _forYouContent

    private val _isFirstLoad = MutableLiveData(true)
    val isFirstLoad: LiveData<Boolean> = _isFirstLoad

    fun getForYouContent() {
        viewModelScope.launch {
            theRickAndMortyRepository.getForYouContent()
                .collect {
                    _isFirstLoad.postValue(false)
                    _forYouContent.postValue(it)
                }
        }
    }
}