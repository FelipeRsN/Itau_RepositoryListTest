package com.felipersn.itaurepositorylist.presentation.repositorylist.adapter

import com.felipersn.itaurepositorylist.data.model.Repository

interface RepositoryListAdapterListener {
    fun onRepositoryClicked(repository: Repository)
}