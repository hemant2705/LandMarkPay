package com.example.landmarkpay.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.landmarkpay.R
import com.example.landmarkpay.database.DatabaseHelper
import com.example.landmarkpay.database.DatabaseHelper1
import com.example.landmarkpay.database.Recipient

class SendMoneyFragment : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_send_money, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseHelper = DatabaseHelper1(requireContext())

        val sendButton = view.findViewById<Button>(R.id.sendButton)
        val recipientAccountNumberEditText = view.findViewById<EditText>(R.id.recipientAccountNumberEditText)
        val recipientNameEditText = view.findViewById<EditText>(R.id.recipientNameEditText)
        val countryEditText = view.findViewById<EditText>(R.id.countryEditText)
        val bicEditText = view.findViewById<EditText>(R.id.bicEditText)

        sendButton.setOnClickListener {
            val recipientAccountNumber = recipientAccountNumberEditText.text.toString()
            val recipientName = recipientNameEditText.text.toString()
            val country = countryEditText.text.toString()
            val bic = bicEditText.text.toString()

          //  val userId = databaseHelper.getColumnValue("TABLE_ENTERPRISES", "id")


            val recipient = Recipient(
                id = 0, // Auto-generated ID in SQLite
               // userId = userId,
                recipientAccountNumber = recipientAccountNumber,
                recipientName = recipientName,
                country = country,
                bic = bic
            )

            databaseHelper.insertRecipient(recipient)

            // Show success message or perform other actions
        }
    }




}


