package com.example.stockapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.stock_item.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val selectedSymbol = intent.getStringExtra("Symbol")
        val jsonString =
            resources.openRawResource(R.raw.sp500).bufferedReader().use { it.readText() }
        val stockArray = Stock.parseStockJson(jsonString)
        val stock=stockArray.find { it.symbol==selectedSymbol }

        item_image.setImageResource(R.drawable.ic_launcher_foreground)
        name_text.text = stock?.name
        price_text.text = stock?.price.toString()
        information_text.text = "Earnings: ${stock?.earning}\n" +
                "Low: ${stock?.low}\n" +
                "High: ${stock?.high}"
    }
}
