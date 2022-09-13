package com.ahr.therickandmorty.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.therickandmorty.data.TheRickAndMortyRepository
import com.ahr.therickandmorty.domain.Response
import com.ahr.therickandmorty.domain.entity.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val theRickAndMortyRepository: TheRickAndMortyRepository) :
    ViewModel() {

    private val _characterList = MutableLiveData<Response<List<Character>>>()
    val characterList: LiveData<Response<List<Character>>> get() = _characterList

    fun getCharacterList(species: String?) {
        viewModelScope.launch {
            theRickAndMortyRepository.getCharacterList(species)
                .collect {
                    _characterList.postValue(it)
                }
        }
    }
}