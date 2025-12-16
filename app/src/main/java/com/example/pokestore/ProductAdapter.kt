package com.example.pokestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load // Importante: Coil

class ProductAdapter(private var productList: List<Product>) : // var para poder actualizar
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ... ViewHolder se mantiene igual ...
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProduct: ImageView = view.findViewById(R.id.imgProduct)
        val txtName: TextView = view.findViewById(R.id.txtProductName)
        val txtPrice: TextView = view.findViewById(R.id.txtProductPrice)
        // ... botones ...
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.txtName.text = product.name
        holder.txtPrice.text = "$ ${product.price.toInt()}" // Formato simple

        // Usar Coil para cargar la imagen desde URL
        holder.imgProduct.load(product.imageUrl) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery) // Imagen mientras carga
            error(android.R.drawable.stat_notify_error) // Imagen si falla
        }

        // ... listeners de botones ...
    }

    override fun getItemCount() = productList.size

    // Método para actualizar datos cuando llegan de la API
    fun updateData(newProducts: List<Product>) {
        productList = newProducts
        notifyDataSetChanged()
    }
}