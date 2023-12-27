package com.arfsar.kmtestsuitmedia.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arfsar.kmtestsuitmedia.R
import com.arfsar.kmtestsuitmedia.databinding.ActivityMainBinding
import com.arfsar.kmtestsuitmedia.ui.SecondScreen.SecondScreen

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("user", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        nameAction()

        val savedName = sharedPreferences.getString("saved_name", "")
        binding.inputName.setText(savedName)
    }

    private fun setupAction() {
        binding.checkButton.setOnClickListener {
            val inputPalindrome = binding.inputPalindrome.text.toString()
            if (inputPalindrome.isEmpty()) {
                showToast(getString(R.string.empty_input))
            } else {
                val isPalindrome = isPalindrome(inputPalindrome)
                if (isPalindrome) {
                    showToast(getString(R.string.is_palindrome))
                } else {
                    showToast(getString(R.string.not_palindrome))
                }
            }
        }
    }

    private fun nameAction() {
        binding.nextButton.setOnClickListener {
            val inputName = binding.inputName.text.toString()
            if (inputName.isEmpty()) {
                showToast(getString(R.string.empty_input))
            } else {
                saveName(inputName)
                val intent = Intent(this, SecondScreen::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isPalindrome(text: String): Boolean {
        val cleanInput = text.replace("[^A-Za-z0-9]".toRegex(), "").lowercase()
        val reversedInput = cleanInput.reversed()
        return cleanInput == reversedInput
    }

    private fun saveName(name: String) {
        sharedPreferences.edit().apply {
            putString("saved_name", name)
            apply()
        }
    }

}