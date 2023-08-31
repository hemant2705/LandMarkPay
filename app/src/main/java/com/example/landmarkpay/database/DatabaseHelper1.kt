package com.example.landmarkpay.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper1(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "EnterpriseDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_RECIPIENTS = "recipients"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_ACCOUNT_NUMBER = "account_number"
        private const val COLUMN_RECIPIENT_NAME = "recipient_name"
        private const val COLUMN_COUNTRY = "country"
        private const val COLUMN_BIC = "bic"
    }

    // Other methods as before...

    override fun onCreate(db: SQLiteDatabase) {
        // Previous code...

        val createRecipientTableQuery = ("CREATE TABLE IF NOT EXISTS $TABLE_RECIPIENTS ($COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_USER_ID TEXT, $COLUMN_ACCOUNT_NUMBER TEXT, $COLUMN_RECIPIENT_NAME TEXT, " +
                "$COLUMN_COUNTRY TEXT, $COLUMN_BIC TEXT)")
        db.execSQL(createRecipientTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${DatabaseHelper1.TABLE_RECIPIENTS}")
        onCreate(db)
    }

    // Other methods as before...

    fun insertRecipient(recipient: Recipient) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ACCOUNT_NUMBER, recipient.recipientAccountNumber)
            put(COLUMN_RECIPIENT_NAME, recipient.recipientName)
            put(COLUMN_COUNTRY, recipient.country)
            put(COLUMN_BIC, recipient.bic)
        }
        db.insert(TABLE_RECIPIENTS, null, values)
        db.close()
    }
}