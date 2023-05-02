package com.exalt.post.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exalt.post.viewobjects.Comment
import com.example.post.R

class CommentPostAdapter(
    private val context: Context
): ListAdapter<Comment, CommentPostAdapter.CommentViewHolder>(CommentDiffCall()) {
    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comment: Comment) {
            with(itemView) {
                findViewById<ImageView>(R.id.imageUser).let {
                    Glide.with(itemView)
                        .load(comment.ownerPictureUrl)
                        .circleCrop()
                        .into(it)
                }

                findViewById<TextView>(R.id.textNameUser).text = comment.ownerName
                findViewById<TextView>(R.id.textComment).text = comment.message
            }
        }
    }

    private class CommentDiffCall: DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean =
            oldItem.id == newItem.id &&
                oldItem.ownerName == newItem.ownerName &&
                oldItem.post == newItem.post &&
                oldItem.publishDate == newItem.publishDate &&
                oldItem.message == newItem.message &&
                oldItem.ownerId == newItem.ownerId &&
                oldItem.ownerPictureUrl == newItem.ownerPictureUrl

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_comment_post, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}