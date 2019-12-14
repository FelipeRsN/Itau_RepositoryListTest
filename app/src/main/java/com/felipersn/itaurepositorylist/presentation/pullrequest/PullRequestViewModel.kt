package com.felipersn.itaurepositorylist.presentation.pullrequest

import androidx.lifecycle.MutableLiveData
import com.felipersn.itaurepositorylist.common.utils.Resource
import com.felipersn.itaurepositorylist.common.utils.SingleLiveEvent
import com.felipersn.itaurepositorylist.data.model.PullRequestsResponse
import com.felipersn.itaurepositorylist.data.remote.repository.GenericRepository
import com.felipersn.itaurepositorylist.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class PullRequestViewModel(private val genericRepository: GenericRepository) : BaseViewModel() {

    var repositoryName: String = ""
    var ownerLogin: String = ""

    val pullRequestsLiveData = MutableLiveData<SingleLiveEvent<Resource<ArrayList<PullRequestsResponse?>>>>()

    fun getPullRequests() {
        pullRequestsLiveData.postValue(SingleLiveEvent(Resource.loading()))

        uiScope.launch {
            genericRepository.getPullRequests(ownerLogin, repositoryName)?.let {
                pullRequestsLiveData.postValue(SingleLiveEvent(Resource.success(it)))
            } ?: run {
                pullRequestsLiveData.postValue(SingleLiveEvent(Resource.error()))
            }
        }
    }
}