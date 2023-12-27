package com.arfsar.kmtestsuitmedia.ui.SecondScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arfsar.kmtestsuitmedia.data.Result
import com.arfsar.kmtestsuitmedia.data.retrofit.response.Data
import com.arfsar.kmtestsuitmedia.databinding.ActivitySecondScreenBinding
import com.arfsar.kmtestsuitmedia.ui.ThirdScreen.ThirdScreen
import com.arfsar.kmtestsuitmedia.ui.ThirdScreen.ThirdScreen.Companion.EXTRA_DETAIL
import com.arfsar.kmtestsuitmedia.ui.ViewModelFactory

class SecondScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding

    private val viewModel by viewModels<SecondScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("saved_name", "")

        binding.username.text = savedName

        binding.chooseUserButton.setOnClickListener {
            startActivity(Intent(this, ThirdScreen::class.java))
        }

        val selectedId = intent.getIntExtra(EXTRA_DETAIL, 0)
        if (selectedId != 0) {
            viewModel.detailUser(selectedId).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            showLoading(true)
                        }

                        is Result.Success -> {
                            getViewModel(result.data.data)
                            showLoading(false)
                        }

                        is Result.Error -> {
                            showLoading(false)
                            Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun getViewModel(user: Data) {
        binding.selectedUser.text = user.firstName
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

}