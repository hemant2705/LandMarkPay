package com.example.landmarkpay.common

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.landmarkpay.R
import com.example.landmarkpay.fragments.Agreements
import com.example.landmarkpay.fragments.Discussion
import com.example.landmarkpay.fragments.ListingsFragment
import com.example.landmarkpay.fragments.SendMoneyFragment
import com.example.landmarkpay.fragments.Transaction_History

import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val navItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.menu_send_money -> SendMoneyFragment()
                R.id.menu_listings -> ListingsFragment()
                R.id.menu_discussions -> Discussion()
                R.id.menu_agreements -> Agreements()
                R.id.menu_transaction_history -> Transaction_History()
                else -> ListingsFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit()

            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navItemSelectedListener)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ListingsFragment())
            .commit()
    }

    override fun onBackPressed() {
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment is ListingsFragment) {
            super.onBackPressed()
        } else {
            findViewById<BottomNavigationView>(R.id.bottom_navigation)
                .selectedItemId = R.id.menu_listings
        }
    }
}