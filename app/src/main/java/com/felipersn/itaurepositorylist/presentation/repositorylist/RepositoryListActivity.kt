package com.felipersn.itaurepositorylist.presentation.repositorylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.felipersn.itaurepositorylist.R
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
    }

    //init methods, variables and requests
    private fun initView() {
        setupAdapters()
        setupObservers()
    }

    private fun setupAdapters() {
        repositoryList_recyclerView?.adapter = repositoryListAdapter
    }

    private fun setupObservers() {
        val listOfRepos = listOf<String>(
            "Teste1",
            "Teste1",
            "Teste1",
            "Teste1",
            "Teste1",
            "Teste1",
            "Teste1",
            "Teste1",
            "Teste1",
            "Teste1"
        )

        repositoryListAdapter.setList(listOfRepos)
    }

    override fun onRepositoryClicked() {

    }
}
