package com.felipersn.itaurepositorylist.presentation.repositorylist.adapter

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.felipersn.itaurepositorylist.R
import de.hdodenhof.circleimageview.CircleImageView

class RepositoryListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val repositoryList_listItem_cardView by lazy { itemView.findViewById<CardView>(R.id.repositoryList_listItem_cardView) }
    val repositoryList_listItem_constraintLayout by lazy { itemView.findViewById<ConstraintLayout>(R.id.repositoryList_listItem_constraintLayout) }
    val repositoryList_listItem_repositoryName by lazy { itemView.findViewById<TextView>(R.id.repositoryList_listItem_repositoryName) }
    val repositoryList_listItem_repositoryDescription by lazy { itemView.findViewById<TextView>(R.id.repositoryList_listItem_repositoryDescription) }
    val repositoryList_listItem_repositoryStars by lazy { itemView.findViewById<TextView>(R.id.repositoryList_listItem_repositoryStars) }
    val repositoryList_listItem_repositoryForks by lazy { itemView.findViewById<TextView>(R.id.repositoryList_listItem_repositoryForks) }
    val repositoryList_listItem_repositoryOwnerPicture by lazy { itemView.findViewById<CircleImageView>(R.id.repositoryList_listItem_repositoryOwnerPicture) }
    val repositoryList_listItem_repositoryOwnerUsername by lazy { itemView.findViewById<TextView>(R.id.repositoryList_listItem_repositoryOwnerUsername) }
}