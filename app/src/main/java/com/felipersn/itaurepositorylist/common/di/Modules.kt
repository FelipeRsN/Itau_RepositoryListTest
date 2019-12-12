package com.felipersn.itaurepositorylist.common.di

import com.felipersn.itaurepositorylist.data.remote.repository.GenericRepository
import com.felipersn.itaurepositorylist.data.remote.tools.RetrofitBuilder
import com.felipersn.itaurepositorylist.presentation.repositorylist.RepositoryListViewModel
import com.felipersn.itaurepositorylist.presentation.repositorylist.adapter.RepositoryListAdapter
import com.felipersn.itaurepositorylist.presentation.repositorylist.adapter.RepositoryListAdapterListener
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

//module
val adapterModule = module {
    factory { (repositoryListAdapterListener: RepositoryListAdapterListener) -> RepositoryListAdapter(repositoryListAdapterListener = repositoryListAdapterListener)}
}

val networkModule = module {
    single { RetrofitBuilder() }
}

val repositoryModule = module {
    single { GenericRepository(retrofitBuilder = get()) }
}

val viewModelModule = module {
    viewModel { RepositoryListViewModel(genericRepository = get()) }
}