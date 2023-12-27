package com.arfsar.kmtestsuitmedia.ui.SecondScreen

import androidx.lifecycle.ViewModel
import com.arfsar.kmtestsuitmedia.data.repository.UserRepository

class SecondScreenViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun detailUser(id: Int) = userRepository.getSingleUser(id)
}