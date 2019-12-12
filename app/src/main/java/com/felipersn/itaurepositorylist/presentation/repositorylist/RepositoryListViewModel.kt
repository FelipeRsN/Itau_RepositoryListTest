package com.felipersn.itaurepositorylist.presentation.repositorylist

import androidx.lifecycle.MutableLiveData
import com.felipersn.itaurepositorylist.common.utils.Resource
import com.felipersn.itaurepositorylist.common.utils.SingleLiveEvent
import com.felipersn.itaurepositorylist.data.model.RepositoryListResponse
import com.felipersn.itaurepositorylist.data.remote.repository.GenericRepository
import com.felipersn.itaurepositorylist.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class RepositoryListViewModel(private val genericRepository: GenericRepository) : BaseViewModel() {

    val repositoryListLiveData =
        MutableLiveData<SingleLiveEvent<Resource<RepositoryListResponse>>>()
    var pageNumber = 1

    fun getRepositoryList(resetPageCount: Boolean = false) {
        if (resetPageCount) pageNumber = 1

        repositoryListLiveData.postValue(SingleLiveEvent(Resource.loading()))

        uiScope.launch {
            genericRepository.getRepositoryList(pageNumber)?.let {
                if (!it.items.isNullOrEmpty()) {
                    repositoryListLiveData.postValue(SingleLiveEvent(Resource.success(it)))
                } else {
                    repositoryListLiveData.postValue(SingleLiveEvent(Resource.error()))
                }
            } ?: run {
                repositoryListLiveData.postValue(SingleLiveEvent(Resource.error()))
            }

            pageNumber++
        }
    }


}