package com.felipersn.itaurepositorylist.presentation.pullrequest.adapter

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.felipersn.itaurepositorylist.R
import de.hdodenhof.circleimageview.CircleImageView

class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val pullRequest_listItem_root by lazy { itemView.findViewById<ConstraintLayout>(R.id.pullRequest_listItem_root) }
    val pullRequest_listItem_title by lazy { itemView.findViewById<TextView>(R.id.pullRequest_listItem_title) }
    val pullRequest_listItem_description by lazy { itemView.findViewById<TextView>(R.id.pullRequest_listItem_description) }
    val pullRequest_listItem_userPicture by lazy { itemView.findViewById<CircleImageView>(R.id.pullRequest_listItem_userPicture) }
    val pullRequest_listItem_userUsername by lazy { itemView.findViewById<TextView>(R.id.pullRequest_listItem_userUsername) }
}