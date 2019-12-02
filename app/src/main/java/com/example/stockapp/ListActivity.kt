package com.example.stockapp

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val sector = intent.getStringExtra("sector")
        val jsonString =
            resources.openRawResource(R.raw.sp500).bufferedReader().use { it.readText() }
        val stockArray = Stock.parseStockJson(jsonString)
        val stockToShow = stockArray.filter { it.sector == sector }.toTypedArray()

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerViewAdapter(stockToShow){
            recyclerViewItemSelected(it)
        }
        stock_recyclerview.apply {
            this.layoutManager = viewManager
            this.adapter = viewAdapter
        }
    }

    fun recyclerViewItemSelected(stock: Stock){
        val intent= Intent(this, DetailActivity::class.java)
        intent.putExtra("symbol", stock.symbol)
        startActivity(intent)
    }

    lateinit var viewAdapter: RecyclerView.Adapter<*>
    lateinit var viewManager: RecyclerView.LayoutManager

    class RecyclerViewAdapter(
        val stockData: Array<Stock>,
        val clickListener: (Stock) -> Unit
    ) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
           val viewItem =
               LayoutInflater.
                   from(parent.context).
                   inflate(R.layout.stock_item, parent, false)
            return RecyclerViewHolder(viewItem)
        }

        override fun getItemCount(): Int {
           return stockData.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.bind(stockData[position], clickListener)
        }

        class RecyclerViewHolder(val viewItem:View):
            RecyclerView.ViewHolder(viewItem){

            fun bind(stock: Stock, clickListener: (Stock) -> Unit) {
                viewItem.findViewById<TextView>(R.id.item_name).text = stock.name
                viewItem.findViewById<TextView>(R.id.item_price).text = stock.price.toString()
                viewItem.setOnClickListener{clickListener(stock)}
            }
        }
    }


}
