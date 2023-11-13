package com.bhtu.phonebook

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale
import kotlin.math.min

class PhoneAdapter(
    private val phoneItems: List<PhoneItem>,
    private val context: Context
): RecyclerView.Adapter<PhoneItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneItemViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.phone_item_in_list, parent, false)
        view.setOnClickListener(fun(v) {

        })
        return PhoneItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return phoneItems.size
    }

    override fun onBindViewHolder(holder: PhoneItemViewHolder, position: Int) {
        holder.nameView.text = phoneItems[position].fullName
        holder.imageView.setImageDrawable(phoneItems[position].avatar)
    }
}