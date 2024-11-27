package com.dicoding.cinemood

import android.content.ContentValues
import android.content.Context

class UserRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun addUser(name: String, email: String, password: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAMA, name)
            put(DatabaseHelper.COLUMN_EMAIL, email)
            put(DatabaseHelper.COLUMN_PASSWORD, password)
        }
        return db.insert(DatabaseHelper.TABLE_USER, null, values)
    }

    fun getUser(email: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_USER,
            null,
            "${DatabaseHelper.COLUMN_EMAIL} = ? AND ${DatabaseHelper.COLUMN_PASSWORD} = ?",
            arrayOf(email, password),
            null,
            null,
            null
        )
        val exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }
}
