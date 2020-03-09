package com.android.damir.moviedb.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.damir.moviedb.data.api.Category
import com.android.damir.moviedb.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesViewModel(
    private val movieRepository: MovieRepositoryImpl = MovieRepositoryImpl()
) : ViewModel() {

    private val categories = MutableLiveData<List<Category>>()

    fun getCategories(): LiveData<List<Category>>{
        viewModelScope.launch {
            val categories = withContext(Dispatchers.IO){
                movieRepository.getCategories()
            }
            this@CategoriesViewModel.categories.value = categories
        }
        return categories
    }


}