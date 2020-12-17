package ru.konder.myapplicationnewsapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list: ArrayList<ItemOfList> = arrayListOf()
        UpdateBtn.setOnClickListener {
            val request = Request.Builder()
                    .url("http://newsapi.org/v2/everything?q=apple&from=2020-10-13&to=2020-10-13&sortBy=popularity&apiKey=277c34091e6c4eae94d700ac2b21663f")
                    .build()
            OkHttpClient().newCall(request).enqueue(object : Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.e("Error", e.toString())
                }

                override fun onResponse(call: okhttp3.Call, response: Response) {
                    var res = response
                    var str = response!!.body()!!.string()
                    val status = (JSONObject(str).get("status")).toString()
                    if (status.trim() == "ok") {
                        var news = (JSONObject(str).getJSONArray("articles"))
                        for (i in 0 until (news.length() - 1)) {
                            var itemOfList: ItemOfList = ItemOfList((news.getJSONObject(i).get("urlToImage")).toString(),
                                    (news.getJSONObject(i).get("title")).toString(),
                                    (news.getJSONObject(i).get("description")).toString(),
                                    (news.getJSONObject(i).get("content")).toString())
                            list.add(itemOfList)
                        }
                    }
                    list = list
                    this@MainActivity.runOnUiThread {
                        val recyclerView = findViewById<RecyclerView>(R.id._NewsRecyclerView)
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        recyclerView.setHasFixedSize(true)
                        recyclerView.adapter = ItemsAdapter(this@MainActivity, list) {
                            val intent = Intent(this@MainActivity, DetailActivity::class.java)
                            intent.putExtra("OBJECT INTENT", it)
                            startActivity(intent)
                        }
                    }
                }
            }
            )
        }
    }
}