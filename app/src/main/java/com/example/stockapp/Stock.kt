package com.example.stockapp

import org.json.JSONArray

class Stock(
    var name: String,
    var symbol: String,
    var price: Double,
    var low: Double,
    var high: Double,
    var earning: Double,
    var sector: String
) {


    companion object {

        //Parse the json file to a Stock object
        fun parseStockJson(json: String): Array<Stock> {
            val data = JSONArray(json)
            val dataArray = Array(data.length()) { Stock("", "", 0.0, 0.0, 0.0, 0.0, "") }
            for (i in 0 until dataArray.size) {
                val stockObject = data.getJSONObject(i)
                dataArray[i].name = stockObject.getString("Name")
                dataArray[i].symbol = stockObject.getString("Symbol")
                dataArray[i].sector = stockObject.getString("Sector")

                dataArray[i].earning = stockObject.getDouble("Earnings/Share")
                dataArray[i].high = stockObject.getDouble("52 Week High")
                dataArray[i].low = stockObject.getDouble("52 Week Low")
                dataArray[i].price = stockObject.getDouble("Price")
            }
            return dataArray
        }
    }
}