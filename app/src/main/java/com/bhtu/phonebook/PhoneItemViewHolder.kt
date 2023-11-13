package com.bhtu.phonebook

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class PhoneItemViewHolder(itemView: View) : ViewHolder(itemView) {
    val imageView: ImageView;
    val nameView: TextView

    init {
        imageView = itemView.findViewById(R.id.imageView)
        nameView = itemView.findViewById(R.id.textView)
    }
}