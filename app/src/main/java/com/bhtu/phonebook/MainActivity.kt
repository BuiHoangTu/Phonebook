package com.bhtu.phonebook

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Random
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private val phoneItems: MutableList<PhoneItem> = ArrayList();

    init {
        val phoneOwners = ArrayList(
            listOf(
                "John Wick",
                "Robert J",
                "James Gunn",
                "Ricky Tales",
                "Micky Mouse",
                "Pick War"
            )
        );
        val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        for (char in charPool) {
            phoneOwners.add("Human $char")
        }

        val random = Random();
        for (phoneOwner in phoneOwners) {
            val phoneStr = abs(random.nextInt()).toString()
            val email = "${phoneOwner.replace(' ', '.', true)}@gmail.com";
            phoneItems.add(
                PhoneItem(
                    phoneOwner,
                    PhoneNumber(phoneStr),
                    email
                )
            )
            Log.v("TAG", "New phone $phoneOwner-${PhoneNumber(phoneStr).number} added")
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Log.v("TAG", "pressed item")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.phone_item_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PhoneAdapter(this.phoneItems, this)

        // press and hold will trigger context menu
        registerForContextMenu(recyclerView)


    }

    // #region: define menu when you click and hold
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        this.menuInflater.inflate(R.menu.phone_item_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val idx = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        val selectedEntry = this.phoneItems[idx]

        when (item.itemId) {
            R.id.item_call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + selectedEntry.phoneNumber.number)
                startActivity(intent)
            }

            R.id.item_sms -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.fromParts("sms:", selectedEntry.phoneNumber.number, null)
                intent.putExtra("sms_body", "This is the message")
                startActivity(intent)
            }

            R.id.item_email -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"

                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(selectedEntry.email)) // recipients
                intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                intent.putExtra(Intent.EXTRA_TEXT, "Email message text")
                startActivity(intent)
            }
        }

        return super.onContextItemSelected(item)
    }
    // #endregion

}