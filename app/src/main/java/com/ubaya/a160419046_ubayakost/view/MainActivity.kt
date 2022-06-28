package com.ubaya.a160419046_ubayakost.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.ubaya.a160419046_ubayakost.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as
                NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(navView,navController)

        //hubungkan antara button nav view dengan nav controller
        bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_,destination,_->
            when(destination.id){
                R.id.formAddKostFragment->hideBottomNav()
                else-> showBottomNav()
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayout)||super.onSupportNavigateUp()
    }

    fun showBottomNav(){
        bottomNav.visibility = View.VISIBLE
    }
    fun hideBottomNav(){
        bottomNav.visibility = View.GONE
    }
}