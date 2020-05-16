package com.sablania.googleimages.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sablania.googleimages.R
import com.sablania.googleimages.databinding.ItemGoogleImageBinding
import com.sablania.googleimages.pojos.GoogleImage

class GoogleImageAdapter(val onItemClick : (GoogleImage) -> Unit) : RecyclerView.Adapter<GoogleImageAdapter.GoogleImageViewHolder>() {

    var list = ArrayList<GoogleImage>()
        set(value) {
            list.clear()
            list.addAll(value)
            notifyDataSetChanged()
        }

    inner class GoogleImageViewHolder(val binding: ItemGoogleImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GoogleImage) {
            binding.apply {
                tvTitle.text =
                    HtmlCompat.fromHtml(item.htmlTitle ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
                Glide.with(itemView.context)
                    .load(item.pagemap?.cse_thumbnail?.get(0)?.src)
                    .into(ivThumbnail)
                container.setOnClickListener{
                    onItemClick.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoogleImageViewHolder {
        return GoogleImageViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_google_image,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GoogleImageViewHolder, position: Int) {
        holder.bind(list.get(position))
    }
}
