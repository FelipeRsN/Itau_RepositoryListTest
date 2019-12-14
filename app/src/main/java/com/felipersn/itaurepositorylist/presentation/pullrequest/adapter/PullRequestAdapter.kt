package com.felipersn.itaurepositorylist.presentation.pullrequest.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felipersn.itaurepositorylist.R
import com.felipersn.itaurepositorylist.common.extension.addText
import com.felipersn.itaurepositorylist.common.extension.inflate
import com.felipersn.itaurepositorylist.common.extension.loadImageFromURL
import com.felipersn.itaurepositorylist.data.model.PullRequestsResponse

class PullRequestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var repositoryList: ArrayList<PullRequestsResponse?> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PullRequestViewHolder(parent.inflate(R.layout.list_item_pull_request))
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val itemToBind = repositoryList[position]
        bindItemViewHolder(viewHolder, itemToBind)
    }

    fun addList(repositoryList: ArrayList<PullRequestsResponse?>) {
        val lastPosition = repositoryList.size
        this.repositoryList.addAll(repositoryList)
        notifyItemInserted(lastPosition)
    }

    fun clearList() {
        this.repositoryList.clear()
        notifyDataSetChanged()
    }

    private fun bindItemViewHolder(viewHolder: RecyclerView.ViewHolder, item: PullRequestsResponse?) {
        val pullRequestViewHolder = viewHolder as PullRequestViewHolder

        pullRequestViewHolder.pullRequest_listItem_title.addText(item?.title)
        pullRequestViewHolder.pullRequest_listItem_description.addText(item?.body)
        pullRequestViewHolder.pullRequest_listItem_userUsername.addText(item?.user?.login)
        pullRequestViewHolder.pullRequest_listItem_userPicture.loadImageFromURL(item?.user?.avatarURL)
    }
}