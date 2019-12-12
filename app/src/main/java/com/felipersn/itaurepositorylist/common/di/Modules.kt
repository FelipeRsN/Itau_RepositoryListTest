package com.felipersn.itaurepositorylist.common.di

import com.felipersn.itaurepositorylist.presentation.repositorylist.adapter.RepositoryListAdapter
import com.felipersn.itaurepositorylist.presentation.repositorylist.adapter.RepositoryListAdapterListener
import org.koin.dsl.module

//module
val adapterModule = module {
    factory { (repositoryListAdapterListener: RepositoryListAdapterListener) -> RepositoryListAdapter(repositoryListAdapterListener = repositoryListAdapterListener) }
}

val networkModule = module { }

val serviceModule = module { }

val repositoryModule = module { }

val viewModelModule = module { }