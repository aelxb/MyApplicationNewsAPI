package ru.konder.myapplicationnewsapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ItemsAdapter(
    private val context: Context,
    private val imgs: List<ItemOfList>,
    val listener: (ItemOfList) -> Unit
) : RecyclerView.Adapter<ItemsAdapter.ImageViewHolder>() {
    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgSrc = view.findViewById<ImageView>(R.id._img)
        val title = view.findViewById<TextView>(R.id.title)
        val Img_description = view.findViewById<TextView>(R.id.title_description)
        fun bindView(image: ItemOfList, listener: (ItemOfList) -> Unit) {
            //imgSrc.setImageResource(image.ImgSrc)
            Picasso.get().load(image.ImgSrc).fit().placeholder(R.drawable.ic_launcher_foreground).into(imgSrc)
            title.text = image.ImgTitle
            Img_description.text = image.img_description
            itemView.setOnClickListener { listener(image) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = imgs.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(imgs[position], listener)
    }

}