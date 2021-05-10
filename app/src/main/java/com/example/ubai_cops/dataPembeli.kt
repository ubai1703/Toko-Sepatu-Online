package com.example.ubai_cops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_data_pembeli.*
import kotlinx.android.synthetic.main.activity_order.*

class dataPembeli : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_pembeli)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        var intent = intent

        val totHrg     = intent.getIntExtra("totHrg", 0)
        val aProduct   = intent.getStringExtra("name")
        val aPrice     = intent.getIntExtra("price",0)
        val aQty       = intent.getIntExtra("qty", 0)


        actionBar.setTitle("Order" +aProduct)

        tot_byr.text = totHrg.toString()

        btn_proses.setOnClickListener {
            val intent = Intent ( this, strukBelanja::class.java)

            intent.putExtra("name", aProduct)
            intent.putExtra("price", aPrice.toString().toInt())
            intent.putExtra("qty", aQty.toString().toInt())
            intent.putExtra("totHrg", tot_byr.text.toString())
            intent.putExtra("nampem", namPmbl.text.toString())
            intent.putExtra("nohp", noHp.text.toString())
            intent.putExtra("alamat", alPmbl.text.toString())

            startActivity(intent)
            true
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
