package com.bhtu.phonebook

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PhoneItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phone_detail)

        try {
            val id = intent.getIntExtra("id", 0)
            val fullName = intent.getStringExtra("fullName")
            val phoneNumber = intent.getStringExtra("phoneNumber")
            val email = intent.getStringExtra("email")

            val detail = PhoneItem(id, fullName!!, PhoneNumber(phoneNumber!!), email!!)

            findViewById<ImageView>(R.id.avatar).setImageDrawable(detail.avatar)
            findViewById<TextView>(R.id.text_view_name).text = detail.fullName
            findViewById<TextView>(R.id.phone_id).text = detail.id.toString()
            findViewById<TextView>(R.id.phone_number).text = detail.phoneNumber.number
            findViewById<TextView>(R.id.email).text = detail.email
        } catch (e: Exception) {
            setResult(Activity.RESULT_CANCELED)
        }
    }
}