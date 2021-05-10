package com.example.ubai_cops

import androidx.appcompat.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_struk_belanja.*

class strukBelanja : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_struk_belanja)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)
        var intent = intent

        val aProduct   = intent.getStringExtra("name")
        val aPrice     = intent.getIntExtra("price", 0)
        val aQty       = intent.getIntExtra("qty", 0)
        val atothrg    = intent.getStringExtra("totHrg")
        val anmPem     = intent.getStringExtra("nampem")
        val anoHp      = intent.getStringExtra("nohp")
        val aalmt      = intent.getStringExtra("alamat")

        actionBar.setTitle("Order" +aProduct)

        nmBrg.text        = aProduct
        hrgstn.text       = aPrice.toString()
        jmlstn.text       = aQty.toString()
        totByrStruk.text  = atothrg
        namPmblStruk.text = anmPem
        noHpStruk.text    = anoHp
        alPmblStruk.text  = aalmt
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}