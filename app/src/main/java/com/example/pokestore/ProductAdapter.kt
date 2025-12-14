package com.example.pokestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProduct: ImageView = view.findViewById(R.id.imgProduct)
        val txtName: TextView = view.findViewById(R.id.txtProductName)
        val txtPrice: TextView = view.findViewById(R.id.txtProductPrice)
        val btnAdd: Button = view.findViewById(R.id.btnAddCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.txtName.text = product.name
        holder.txtPrice.text = product.price
        holder.imgProduct.setImageResource(product.imageResId)

        holder.btnAdd.setOnClickListener {
            // Usar Snackbar para personalizar colores
            val snackbar = Snackbar.make(holder.itemView, "Producto Agregado: ${product.name}", Snackbar.LENGTH_SHORT)
            
            // Personalizar colores
            snackbar.setBackgroundTint(holder.itemView.context.getColor(R.color.success_green))
            snackbar.setTextColor(holder.itemView.context.getColor(R.color.white))
            
            snackbar.show()
        }
    }

    override fun getItemCount() = productList.size
}
