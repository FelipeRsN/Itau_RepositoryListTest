package com.felipersn.itaurepositorylist.presentation.repositorylist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felipersn.itaurepositorylist.R
import com.felipersn.itaurepositorylist.common.extension.inflate

class RepositoryListAdapter(private val repositoryListAdapterListener: RepositoryListAdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var repositoryList: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val repositoryListViewHolder = RepositoryListViewHolder(parent.inflate(R.layout.list_item_repository_list))

        repositoryListViewHolder.repositoryList_listItem_cardView.setOnClickListener {
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

    fun setList(repositoryList: List<String>) {
        this.repositoryList = ArrayList(repositoryList)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.repositoryList.clear()
    }

    private fun bindItemViewHolder(viewHolder: RecyclerView.ViewHolder, item: String) {
        val repositoryListViewHolder = viewHolder as RepositoryListViewHolder

        repositoryListViewHolder.repositoryList_listItem_repositoryName.text = item
    }

}