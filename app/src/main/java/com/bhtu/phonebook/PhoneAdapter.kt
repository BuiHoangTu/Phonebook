package com.bhtu.phonebook

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PhoneAdapter(
    private val phoneItems: List<PhoneItem>,
    private val context: Context
): RecyclerView.Adapter<PhoneItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneItemViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.phone_item_in_list, parent, false)
        return PhoneItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return phoneItems.size
    }

    override fun onBindViewHolder(holder: PhoneItemViewHolder, position: Int) {
        val selected = phoneItems[position]
        holder.nameView.text = selected.fullName
        holder.imageView.setImageDrawable(selected.avatar)

        holder.itemView.setOnClickListener(fun(_) {
            val intent = Intent(context, PhoneItemDetailActivity::class.java)
            intent.putExtra("id", selected.id)
            intent.putExtra("fullName", selected.fullName)
            intent.putExtra("phoneNumber", selected.phoneNumber.number)
            intent.putExtra("email", selected.email)

            context.startActivity(intent)
        })
    }
}