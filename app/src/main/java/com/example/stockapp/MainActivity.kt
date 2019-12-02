package com.example.stockapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        val intent = Intent(this, ListActivity::class.java)
        var sector = when(p0?.id) {
            tech_button.id->"Information Technology"
            financial_button.id->"Financials"
            industrial_button.id->"Industrials"
            health_care_button.id->"Health Care"
            else->"Information Technology"
        }
        intent.putExtra("sector", sector)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tech_button.setOnClickListener ( this )
        health_care_button.setOnClickListener ( this )
        industrial_button.setOnClickListener ( this )
        financial_button.setOnClickListener ( this )
    }
}
