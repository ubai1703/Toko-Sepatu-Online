package com.example.ubai_cops

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_product.view.*

class productAdapter(val context: Context) :
                    RecyclerView.Adapter<productAdapter.ViewHolder>(), Filterable{

    var arrayList         =  ArrayList<productModel>()
    var ProductListFilter =  ArrayList<productModel>()

    fun setData(arrayList: ArrayList<productModel>){
        this.arrayList         = arrayList
        this.ProductListFilter = arrayList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(model : productModel){
            itemView.productName.text= model.nmProduct
            itemView.productDesc.text = model.dsProduct
            itemView.priceProd.text = model.priceProduct.toString()
            itemView.imgProduct.setImageResource(model.picProduct)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_product, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener(){
            val model = arrayList.get(position)

            var gProduct : String = model.nmProduct
            var gDesc    : String = model.dsProduct
            var gPrice   : Int    = model.priceProduct.toString().toInt()
            var gImg     : Int    = model.picProduct

            val intent = Intent(context, Order::class.java)
            intent.putExtra("pProduct", gProduct)
            intent.putExtra("pDesc", gDesc)
            intent.putExtra("pPrice", gPrice)
            intent.putExtra("pImg", gImg)

            context.startActivity(intent)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (charSequence == null || charSequence.length < 0){
                    filterResults.count = ProductListFilter.size
                    filterResults.values = ProductListFilter
                }else{
                    var searchChr = charSequence.toString()
                    val productSearch = ArrayList<productModel>()
                    for (item in ProductListFilter){
                        if (item.nmProduct.toLowerCase().contains(searchChr) || item.dsProduct.toLowerCase().contains(searchChr)){
                            productSearch.add(item)
                        }
                    }
                    filterResults.count = productSearch.size
                    filterResults.values= productSearch
                }
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                arrayList = filterResults!!.values as ArrayList<productModel>
                notifyDataSetChanged()
            }

        }
    }
}