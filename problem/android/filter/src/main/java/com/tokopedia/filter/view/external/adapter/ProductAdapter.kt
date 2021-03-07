package com.tokopedia.filter.view.external.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tokopedia.filter.R
import com.tokopedia.filter.view.data.model.Product
import com.tokopedia.filter.view.external.utils.IntegerUtil
import kotlinx.android.synthetic.main.layout_product_item.view.*
import java.util.zip.Inflater

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    val products: MutableList<Product> = mutableListOf()

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(product: Product) {
            val discount : RelativeLayout = view.findViewById(R.id.discount)
            if (product.hasDiscount) {
                discount.visibility = View.VISIBLE
                val textPriceSlashed = view.findViewById<TextView>(R.id.tv_product_price_discount)
                textPriceSlashed.apply {
                    paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    text = IntegerUtil.convertIntToRupiahFormat(product.slashedPrice)
                }
                view.findViewById<TextView>(R.id.tv_product_price_discount_pct).text = "${product.discountPercentage}%"
            }
            view.findViewById<TextView>(R.id.tv_product_name).text = if (product.name.length > 31) product.name.substring(0, 30) + "..." else product.name
            view.findViewById<TextView>(R.id.tv_product_price).text = IntegerUtil.convertIntToRupiahFormat(product.price)
            view.findViewById<TextView>(R.id.tv_merchant_location).text = product.merchant.city
            val productImage = view.findViewById<ImageView>(R.id.iv_product_image)
            Glide.with(view)
                    .load(product.imageUrl)
                    .into(productImage)
        }

    }

    fun setProducts(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, null))
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }
}