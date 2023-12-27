package com.arfsar.kmtestsuitmedia.ui.ThirdScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arfsar.kmtestsuitmedia.data.repository.UserRepository
import com.arfsar.kmtestsuitmedia.data.retrofit.response.DataItem

class ThirdScreenViewModel(userRepository: UserRepository) : ViewModel() {

    val users: LiveData<PagingData<DataItem>> = userRepository.getUsers().cachedIn(viewModelScope)
}
