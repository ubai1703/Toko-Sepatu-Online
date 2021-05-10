package com.example.ubai_cops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_data_pembeli.*
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_struk_belanja.*

class Order : AppCompatActivity() {

    var totHarga:Int = 0
    var minteger:Int = 0
    var MIN_NUMBER = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        var intent = intent

        val aProduct = intent.getStringExtra("pProduct")
        val aPrice   = intent.getIntExtra("pPrice", 0)
        val aImg     = intent.getIntExtra("pImg", 0)

        actionBar.setTitle("Order" + aProduct)

        OrdProduct.text  = aProduct
        OrdPrice.text    = aPrice.toString()
        imageOrd.setImageResource(aImg)

        fun display(number: Int) {
            val displayInteger = findViewById<View>(R.id.JmlOrd) as TextView

            displayInteger.text = "" + number

            totHarga = OrdPrice.text.toString().toInt() *
                    displayInteger.text.toString().toInt()
            TotHarOrd.text = totHarga.toString()
        }

        decreaseOrd.setOnClickListener(){
            if(minteger == MIN_NUMBER){
                minteger = 0
            }
            else{
                minteger = minteger - 1
                display(minteger)
            }
        }

        increaseOrd.setOnClickListener(){
            minteger = minteger + 1
            display(minteger)
        }

        OrderLagi.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity :: class.java)
            startActivity(intent)
        }

        Bayar.setOnClickListener {
            // GO TO INTENT BAYAR
            val intent = Intent(this, dataPembeli :: class.java)

                intent.putExtra("name", aProduct)
                intent.putExtra("price", aPrice.toString().toInt())
                intent.putExtra("qty", JmlOrd.text.toString().toInt())
                intent.putExtra("totHrg", totHarga.toString().toInt())
                startActivity(intent)
                true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

