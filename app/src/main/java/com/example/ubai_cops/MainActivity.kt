package com.example.ubai_cops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    //Initialise the DrawerLayout, NavigationView and ToggleBar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navDrawerView: NavigationView

    private lateinit var bottomNavigation : BottomNavigationView

    var myAdapter : productAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayItem = ArrayList<productModel>()
        arrayItem.add(productModel("Puma", "Puma cocok untuk kaki anda", R.drawable.sepatu1,500000))
        arrayItem.add(productModel("Nike", "Nike cocok untuk kaki anda", R.drawable.sepatu2,400000))
        arrayItem.add(productModel("New Balance", "New Balance cocok untuk kaki anda", R.drawable.sepatu3,250000))
        arrayItem.add(productModel("Diadora", "Diadora cocok untuk kaki anda", R.drawable.sepatu4,300000))
        arrayItem.add(productModel("Convers", "Convers cocok untuk kaki anda", R.drawable.sepatu5,150000))
        arrayItem.add(productModel("Brodo", "Brodo cocok untuk kaki anda", R.drawable.sepatu6,200000))
        arrayItem.add(productModel("Adidas", "Adidas cocok untuk kaki anda", R.drawable.sepatu7,350000))
        arrayItem.add(productModel("Wakai", "Wakai cocok untuk kaki anda", R.drawable.sepatu8,450000))
        arrayItem.add(productModel("Vans", "Vans cocok untuk kaki anda", R.drawable.sepatu9,150000))
        arrayItem.add(productModel("Reebok", "Reebok cocok untuk kaki anda", R.drawable.sepatu10,450000))

        myAdapter = productAdapter( this)
        myAdapter!!.setData(arrayItem)

        Product_RecycleView.layoutManager = LinearLayoutManager(this)
        Product_RecycleView.adapter       = myAdapter

        bottomNavigation = findViewById(R.id.navBottom)
        bottomNavigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.naviration_home -> {
                    val  intent = Intent(applicationContext, MainActivity :: class.java)
                    startActivity(intent)
                    true
                }
                R.id.history -> {
                    Toast.makeText(this, "Go To history transaction", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }

        //Call findViewById on the DrawerLayout
        drawerLayout= findViewById(R.id.drawer)

        //Pass the ActionBarToggle action into the drawerListener
        actionBarToggle= ActionBarDrawerToggle(this, drawerLayout,
            0,
            0 )

        drawerLayout.addDrawerListener(actionBarToggle)

        //Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Call syncState() on the action bar so it'll automatically
        //Mengubah tombol kembali jika drawer layout dibuka
        actionBarToggle.syncState()

        //Memanggil navigationView
        navDrawerView= findViewById(R.id.navDrawer)

        //Call setNavigationItemSelectedLisntener on the navigationView
        //Untuk mendeteksi jika item di klik
        navDrawerView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.myPropile -> {
                    val intent = Intent (applicationContext, profile ::class.java)
                    startActivity (intent)
                    true
                }
                R.id.myContact -> {
                    Toast.makeText(this, "Go to My Contact", Toast.LENGTH_SHORT ).show()
                    true
                }
                R.id.myHelp -> {
                    Toast.makeText(this, "Go to My Help", Toast.LENGTH_SHORT ).show()
                    true
                }
               else -> {
                   false
               }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)){
            this.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            this.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        val searchItem = menu?.findItem(R.id.Search)
        if (searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.maxWidth = Int.MAX_VALUE
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(filterString: String?): Boolean {
                    myAdapter!!.filter.filter(filterString)
                    return true
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.shoping){
            Toast.makeText(this, "View Shoping Chart", Toast.LENGTH_SHORT).show()
            return true
        }

       else if(id == R.id.account){
            Toast.makeText(this, "Account Cliked", Toast.LENGTH_SHORT).show()
            return true
        }

       else if(id == R.id.logout){
            Toast.makeText(this, "Logout and go to login form", Toast.LENGTH_SHORT).show()
            val intent = Intent (this, Login::class.java)
            Toast.makeText(this, "You Logout", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

