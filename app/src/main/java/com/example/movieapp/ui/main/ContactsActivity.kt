package com.example.movieapp.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        checkPermission()
    }

    private val permissionResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            when {
                result -> getContacts()
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) ->
                    AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_contacts_48)
                        .setTitle("Запрашиваю доступ")
                        .setMessage("Разрешение нужно для корректной работы приложения")
                        .setPositiveButton("Разрешить") { _, _ -> checkPermission() }
                        .setNegativeButton("Не разрешать") { _, _ ->
                            startActivity(
                                Intent(
                                    this,
                                    MainActivity::class.java
                                )
                            )
                        }
                        .create()
                        .show()
                else -> startActivity(Intent(this, MainActivity::class.java))
            }
        }

    private fun checkPermission() {
        permissionResult.launch(Manifest.permission.READ_CONTACTS)
    }

    @SuppressLint("SetTextI18n")
    private fun getContacts() {
        val contentResolver: ContentResolver = contentResolver
        val cursor: Cursor? =
            contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )

        val contactText = findViewById<TextView>(R.id.text_contacts)

        cursor?.let {
            val columnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            if (columnIndex >= 0) {
                for (i in 0..cursor.count) {
                    if (cursor.moveToPosition(i)) {
                        val name = cursor.getString(columnIndex)
                        contactText.text = "${contactText.text} $name \n"
                    }
                }
            }
            cursor.close()
        }
    }

}