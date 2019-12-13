package com.felipersn.itaurepositorylist.presentation.repositorylist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felipersn.itaurepositorylist.R
import com.felipersn.itaurepositorylist.common.extension.addText
import com.felipersn.itaurepositorylist.common.extension.inflate
import com.felipersn.itaurepositorylist.common.extension.loadImageFromURL
import com.felipersn.itaurepositorylist.data.model.Repository

class RepositoryListAdapter(private val repositoryListAdapterListener: RepositoryListAdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var repositoryList: ArrayList<Repository> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val repositoryListViewHolder = RepositoryListViewHolder(parent.inflate(R.layout.list_item_repository_list))

        repositoryListViewHolder.repositoryList_listItem_root.setOnClickListener {
            repositoryListAdapterListener.onRepositoryClicked()
        }

        return repositoryListViewHolder
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val itemToBind = repositoryList[position]
        bindItemViewHolder(viewHolder, itemToBind)
    }

    fun addList(repositoryList: List<Repository>) {
        val lastPosition = repositoryList.size
        this.repositoryList.addAll(repositoryList)
        notifyItemInserted(lastPosition)
    }

    fun clearList() {
        this.repositoryList.clear()
        notifyDataSetChanged()
    }

    private fun bindItemViewHolder(viewHolder: RecyclerView.ViewHolder, item: Repository) {
        val repositoryListViewHolder = viewHolder as RepositoryListViewHolder

        repositoryListViewHolder.repositoryList_listItem_repositoryName.addText(item.name)
        repositoryListViewHolder.repositoryList_listItem_repositoryDescription.addText(item.description)
        repositoryListViewHolder.repositoryList_listItem_repositoryForks.addText(item.forksCount.toString())
        repositoryListViewHolder.repositoryList_listItem_repositoryStars.addText(item.stargazersCount.toString())

        repositoryListViewHolder.repositoryList_listItem_repositoryOwnerUsername.addText(item.owner?.login)

        repositoryListViewHolder.repositoryList_listItem_repositoryOwnerPicture.loadImageFromURL(item.owner?.avatarURL)
    }

}