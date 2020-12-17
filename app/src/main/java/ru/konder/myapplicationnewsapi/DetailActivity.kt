package ru.konder.myapplicationnewsapi

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val item = intent.getParcelableExtra<ItemOfList>("OBJECT INTENT")
        val imgSrc = findViewById<ImageView>(R.id._imgDetail)
        val imgTitle = findViewById<TextView>(R.id._imgTitle)
        val imgDescription = findViewById<TextView>(R.id._imgDescription)

        Picasso.get().load(item.ImgSrc).fit().placeholder(R.drawable.ic_launcher_foreground).into(imgSrc)
        imgTitle.text = item.ImgTitle
        imgDescription.text = item.ImgDescription
    }
}