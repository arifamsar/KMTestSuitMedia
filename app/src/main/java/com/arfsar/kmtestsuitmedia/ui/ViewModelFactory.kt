package com.arfsar.kmtestsuitmedia.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arfsar.kmtestsuitmedia.data.repository.UserRepository
import com.arfsar.kmtestsuitmedia.di.Injection
import com.arfsar.kmtestsuitmedia.ui.SecondScreen.SecondScreenViewModel
import com.arfsar.kmtestsuitmedia.ui.ThirdScreen.ThirdScreenViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val userRepository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            return ThirdScreenViewModel(userRepository) as T
        } else if (modelClass.isAssignableFrom(SecondScreenViewModel::class.java)) {
            return SecondScreenViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }.also { instance = it }
    }
}