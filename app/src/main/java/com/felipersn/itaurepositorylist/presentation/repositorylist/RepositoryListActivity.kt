package com.felipersn.itaurepositorylist.presentation.repositorylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.felipersn.itaurepositorylist.R
import com.felipersn.itaurepositorylist.common.utils.Resource
import com.felipersn.itaurepositorylist.presentation.repositorylist.adapter.RepositoryListAdapter
import com.felipersn.itaurepositorylist.presentation.repositorylist.adapter.RepositoryListAdapterListener
import kotlinx.android.synthetic.main.activity_repository_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RepositoryListActivity : AppCompatActivity(), RepositoryListAdapterListener {

    //injections
    private val repositoryListAdapter: RepositoryListAdapter by inject { parametersOf(this) }
    private val repositoryListViewModel: RepositoryListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)

        initView()

        repositoryListViewModel.getRepositoryList()
    }

    //init methods, variables and requests
    private fun initView() {
        setupListeners()
        setupAdapters()
        setupObservers()
    }

    private fun setupListeners() {
        repositoryList_swipeRefresh?.setOnRefreshListener {
            repositoryListViewModel.getRepositoryList(true)
            repositoryListAdapter.clearList()
        }
    }

    private fun setupAdapters() {
        repositoryList_recyclerView?.adapter = repositoryListAdapter
    }

    private fun setupObservers() {
        repositoryListViewModel.repositoryListLiveData.observe(this, Observer { singleLiveEvent ->
            singleLiveEvent.getContentIfNotHandled()?.let { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        toggleSwipeRefresh(false)
                        val response = resource.data
                        repositoryListAdapter.addList(response?.items!!)
                    }
                    Resource.Status.LOADING -> {
                        toggleSwipeRefresh(true)
                    }
                    Resource.Status.ERROR -> {
                        toggleSwipeRefresh(false)
                    }
                }
            }
        })

    }

    override fun onRepositoryClicked() {

    }

    private fun toggleSwipeRefresh(show: Boolean) {
        when (show) {
            true -> {
                if (!repositoryList_swipeRefresh?.isRefreshing!!) {
                    repositoryList_swipeRefresh?.isRefreshing = true
                }
            }
            false -> {
                if (repositoryList_swipeRefresh?.isRefreshing!!) {
                    repositoryList_swipeRefresh?.isRefreshing = false
                }
            }
        }
    }
}
