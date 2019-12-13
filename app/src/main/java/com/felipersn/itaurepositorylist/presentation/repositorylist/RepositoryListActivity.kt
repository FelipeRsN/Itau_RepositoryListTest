package com.felipersn.itaurepositorylist.presentation.repositorylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.felipersn.itaurepositorylist.R
import com.felipersn.itaurepositorylist.common.utils.EndlessScrollEventListener
import com.felipersn.itaurepositorylist.common.utils.EndlessScrollEventListener.OnLoadMoreListener
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

    private lateinit var endlessScrollEventListener: EndlessScrollEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)

        initView()

        updateNumberOfResultsLabel(0)
        repositoryListViewModel.getRepositoryList()
    }

    //init methods, variables and requests
    private fun initView() {
        setupRecyclerView()
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        repositoryList_swipeRefresh?.setOnRefreshListener {
            repositoryListViewModel.getRepositoryList(true)
            repositoryListAdapter.clearList()
            endlessScrollEventListener.reset()
            updateNumberOfResultsLabel(0)
        }

        endlessScrollEventListener.mOnLoadMoreListener = object : OnLoadMoreListener {
            override fun loadMore() {
                repositoryListViewModel.getRepositoryList()
            }
        }
    }

    private fun setupRecyclerView() {
        endlessScrollEventListener = EndlessScrollEventListener((repositoryList_recyclerView.layoutManager as LinearLayoutManager))

        repositoryList_recyclerView?.adapter = repositoryListAdapter
        repositoryList_recyclerView?.addOnScrollListener(endlessScrollEventListener)
    }

    private fun updateNumberOfResultsLabel(results: Int) {
        repositoryList_listStatus?.text =
            String.format(getString(R.string.repositoryList_numberOfResults), results)
    }

    private fun setupObservers() {
        repositoryListViewModel.repositoryListLiveData.observe(this, Observer { singleLiveEvent ->
            singleLiveEvent.getContentIfNotHandled()?.let { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        toggleSwipeRefresh(false)
                        endlessScrollEventListener.isLoading = false
                        val response = resource.data
                        repositoryListAdapter.addList(response?.items!!)
                        updateNumberOfResultsLabel(repositoryListAdapter.itemCount)
                    }
                    Resource.Status.LOADING -> {
                        toggleSwipeRefresh(true)
                        endlessScrollEventListener.isLoading = true
                    }
                    Resource.Status.ERROR -> {
                        toggleSwipeRefresh(false)
                        endlessScrollEventListener.isLoading = false
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
