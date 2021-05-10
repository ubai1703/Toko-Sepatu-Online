package com.example.ubai_cops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun loginMasuk(view: View) {
        val Uname = usnam.text.toString()
        val Pswod = pswd.text.toString()

        if (Uname.isEmpty() || Pswod.isEmpty()) {
            Toast.makeText(this, "Isikan Username atau Password Dulu !", Toast.LENGTH_SHORT).show()
        } else if (Uname.toLowerCase() == "ubai" && Pswod.toLowerCase() == "250909") {
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true
        } else {
            Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
