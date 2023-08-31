package com.example.landmarkpay.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "EnterpriseDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_ENTERPRISES = "enterprises"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_ADDRESS = "address"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_TIN = "tin"
        private const val COLUMN_PASSWORD = "password"

        private const val TABLE_RECIPIENTS = "recipients"
        private const val COLUMN_RECIPIENT_ID = "id"
        private const val COLUMN_ACCOUNT_NUMBER = "account_number"
        private const val COLUMN_RECIPIENT_NAME = "recipient_name"
        private const val COLUMN_COUNTRY = "country"
        private const val COLUMN_BIC = "bic"
    }




    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = ("CREATE TABLE $TABLE_ENTERPRISES ($COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_NAME TEXT, $COLUMN_ADDRESS TEXT, $COLUMN_EMAIL TEXT, $COLUMN_TIN TEXT, $COLUMN_PASSWORD TEXT)")
        db.execSQL(createTableQuery)

        val createRecipientTableQuery = ("CREATE TABLE IF NOT EXISTS $TABLE_RECIPIENTS ($COLUMN_RECIPIENT_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_USER_ID TEXT, $COLUMN_ACCOUNT_NUMBER TEXT, $COLUMN_RECIPIENT_NAME TEXT, " +
                "$COLUMN_COUNTRY TEXT, $COLUMN_BIC TEXT)")
        db.execSQL(createRecipientTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ENTERPRISES")
        onCreate(db)
    }

    fun insertEnterprise(enterprise: Enterprise) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
          //  put(COLUMN_USER_ID, enterprise.userId)
            put(COLUMN_NAME, enterprise.name)
            put(COLUMN_ADDRESS, enterprise.address)
            put(COLUMN_EMAIL, enterprise.email)
            put(COLUMN_TIN, enterprise.tin)
            put(COLUMN_PASSWORD, enterprise.password)
        }
        db.insert(TABLE_ENTERPRISES, null, values)
        db.close()
    }

    fun checkLoginCredentials(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_ENTERPRISES,
            arrayOf(COLUMN_ID),
            "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(email, password),
            null,
            null,
            null
        )

        val isValid = cursor.count > 0
        cursor.close()
        return isValid
    }

    fun insertRecipient(recipient: Recipient) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
          //  put(COLUMN_USER_ID, recipient.userId)
            put(COLUMN_ACCOUNT_NUMBER, recipient.recipientAccountNumber)
            put(COLUMN_RECIPIENT_NAME, recipient.recipientName)
            put(COLUMN_COUNTRY, recipient.country)
            put(COLUMN_BIC, recipient.bic)
        }
        db.insert(TABLE_RECIPIENTS, null, values)
        db.close()
    }

    fun getColumnValue(tableName: String, columnName: String, selection: String? = null): String {
        val db = this.readableDatabase
        var value = ""

        val query = "SELECT $columnName FROM $tableName ${selection ?: ""}"
        db.rawQuery(query, null).use { cursor ->
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(columnName)
                if (columnIndex != -1) {
                    value = cursor.getString(columnIndex)
                }
            }
        }

        return value
    }


    // Add other database operations like fetching enterprises, checking login credentials, etc.
}
