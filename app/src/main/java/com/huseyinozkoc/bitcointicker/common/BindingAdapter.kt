package com.huseyinozkoc.bitcointicker.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.huseyinozkoc.bitcointicker.R

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView).load(imageUrl).into(imageView)
    }
}

@BindingAdapter("priceChangePercentage24h")
fun priceChangePercentage24h(
    tvPercentage: MaterialTextView,
    price: Double
) {
    tvPercentage.text = String.format("%.2f", price) + "%"

    tvPercentage.setTextColor(
        if (price.toString().contains("-")) tvPercentage.context.getColor(R.color.md_red_700)
        else tvPercentage.context.getColor(R.color.md_green_700)
    )
}

@BindingAdapter("setIncreaseDecrease")
fun setIncreaseDecrease(imageView: ImageView, price: Double) {
    if (price.toString().contains("-")) imageView.setImageResource(R.drawable.decrease)
    else imageView.setImageResource(R.drawable.increase)
}

@BindingAdapter("currentPrice")
fun currentPrice(
    tvCurrentPrice: MaterialTextView,
    price: Double
) {
    tvCurrentPrice.text = "$" + String.format("%.3f", price)
}