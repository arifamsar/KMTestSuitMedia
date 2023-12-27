package com.arfsar.kmtestsuitmedia.ui.ThirdScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arfsar.kmtestsuitmedia.adapter.UserListAdapter
import com.arfsar.kmtestsuitmedia.data.retrofit.response.DataItem
import com.arfsar.kmtestsuitmedia.databinding.ActivityThirdScreenBinding
import com.arfsar.kmtestsuitmedia.ui.SecondScreen.SecondScreen
import com.arfsar.kmtestsuitmedia.ui.ViewModelFactory

class ThirdScreen : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding

    private val viewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val mAdapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        getDataUser()

        binding.swipeRefresh.setOnRefreshListener {
            getDataUser()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        val mLayoutManager = LinearLayoutManager(this)
        binding.rvListUsers.apply {
            layoutManager = mLayoutManager
            setHasFixedSize(true)
            adapter = mAdapter
        }

        mAdapter.setOnItemClickCallback(object : UserListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                getDetailUser(data)
            }

        })
    }

    private fun getDataUser() {
        binding.rvListUsers.adapter = mAdapter
        viewModel.users.observe(this) { users ->
            mAdapter.submitData(lifecycle, users)
        }
    }

    private fun getDetailUser(data: DataItem) {
        val intent = Intent(this, SecondScreen::class.java)
        intent.putExtra(EXTRA_DETAIL, data.id)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}