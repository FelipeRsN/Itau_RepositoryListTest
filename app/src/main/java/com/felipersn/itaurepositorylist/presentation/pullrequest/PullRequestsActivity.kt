package com.felipersn.itaurepositorylist.presentation.pullrequest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.felipersn.itaurepositorylist.R
import com.felipersn.itaurepositorylist.common.extension.longToast
import com.felipersn.itaurepositorylist.common.utils.Resource
import com.felipersn.itaurepositorylist.presentation.pullrequest.adapter.PullRequestAdapter
import kotlinx.android.synthetic.main.activity_pull_requests.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PullRequestsActivity : AppCompatActivity() {

    companion object {

        private const val INTENT_REPOSITORY_NAME = "INTENT_REPOSITORY_NAME"
        private const val INTENT_REPOSITORY_OWNER_LOGIN = "INTENT_REPOSITORY_OWNER_LOGIN"

        fun providePullRequestIntent(context: Context, repositoryName: String?, ownerLogin: String?): Intent {
            val pullRequestIntent = Intent(context, PullRequestsActivity::class.java)
            repositoryName?.let { pullRequestIntent.putExtra(INTENT_REPOSITORY_NAME, repositoryName) }
            ownerLogin?.let { pullRequestIntent.putExtra(INTENT_REPOSITORY_OWNER_LOGIN, ownerLogin) }
            return pullRequestIntent
        }

    }

    //injections
    private val pullRequestAdapter: PullRequestAdapter by inject()
    private val pullRequestViewModel: PullRequestViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        initView()

        showPullRequestCounts(0, 0)
        pullRequestViewModel.getPullRequests()
    }

    private fun initView() {
        retrieveIntentData()
        setupToolbar()
        setupRecyclerView()
        setupObservers()
        setupListeners()
    }

    //retrieve received intent from previous activity
    private fun retrieveIntentData() {
        if (intent.hasExtra(INTENT_REPOSITORY_NAME)) pullRequestViewModel.repositoryName = intent.getStringExtra(INTENT_REPOSITORY_NAME)!!
        if (intent.hasExtra(INTENT_REPOSITORY_OWNER_LOGIN)) pullRequestViewModel.ownerLogin = intent.getStringExtra(INTENT_REPOSITORY_OWNER_LOGIN)!!
    }

    //setup actionBar proprieties
    private fun setupToolbar() {
        setSupportActionBar(pullRequest_toolbar)
        supportActionBar?.title = pullRequestViewModel.repositoryName
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showPullRequestCounts(opened: Int, closed: Int){
        supportActionBar?.subtitle = String.format(getString(R.string.pullRequest_pullRequestCount), opened, closed)
    }

    private fun setupRecyclerView() {
        pullRequest_list?.adapter = pullRequestAdapter
    }

    private fun setupObservers() {
        pullRequestViewModel.pullRequestsLiveData.observe(this, Observer { singleLiveEvent ->
            singleLiveEvent.getContentIfNotHandled()?.let { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        val response = resource.data
                        if (!response.isNullOrEmpty()) {
                            pullRequestAdapter.addList(response)
                            pullRequest_emptyMessage?.visibility = View.GONE

                            var openedCount = 0
                            var closedCount = 0

                            response.forEach {
                                when(it?.state){
                                    "open" -> openedCount++
                                    else -> closedCount++
                                }
                            }

                            showPullRequestCounts(openedCount, closedCount)
                        } else {
                            pullRequest_emptyMessage?.visibility = View.VISIBLE
                        }
                        toggleSwipeRefresh(false)
                    }
                    Resource.Status.LOADING -> {
                        toggleSwipeRefresh(true)
                        pullRequest_emptyMessage?.visibility = View.GONE
                        showPullRequestCounts(0, 0)
                    }
                    Resource.Status.ERROR -> {
                        toggleSwipeRefresh(false)
                        pullRequest_emptyMessage?.visibility = View.VISIBLE
                        longToast(getString(R.string.pullRequest_error))
                    }
                }
            }
        })
    }

    private fun setupListeners() {
        pullRequest_swipeRefresh?.setOnRefreshListener {
            pullRequestViewModel.getPullRequests()
            pullRequestAdapter.clearList()
        }
    }

    private fun toggleSwipeRefresh(show: Boolean) {
        when (show) {
            true -> {
                if (!pullRequest_swipeRefresh?.isRefreshing!!) {
                    pullRequest_swipeRefresh?.isRefreshing = true
                }
            }
            false -> {
                if (pullRequest_swipeRefresh?.isRefreshing!!) {
                    pullRequest_swipeRefresh?.isRefreshing = false
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finishAfterTransition()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
