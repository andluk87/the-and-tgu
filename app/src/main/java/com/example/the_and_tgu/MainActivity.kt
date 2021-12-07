package com.example.the_and_tgu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val LAST_SELECTED_ITEM = "item"
private val PIZAZZ_FRAGMENT = PizzaFragment().javaClass.name
private val ABOUT_FRAGMENT = AboutFragment().javaClass.name
private val HOME_FRAGMENT = HomeFragment().javaClass.name

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.pizza_table -> {
                    fragment = PizzaFragment()
                }
                R.id.about -> {
                    fragment = AboutFragment()
                }
                R.id.home -> {
                    fragment = if (savedInstanceState != null)
                        supportFragmentManager.getFragment(
                            savedInstanceState,
                            HOME_FRAGMENT)?: HomeFragment()
                    else HomeFragment()
                }
                else-> fragment = PizzaFragment()
            }
            replaceFragment(fragment!!)
            true
        }

//        bottomNavigationView.selectedItemId =
//            savedInstanceState?.getInt(LAST_SELECTED_ITEM) ?: R.id.home
            bottomNavigationView.selectedItemId =
            savedInstanceState?.getInt(LAST_SELECTED_ITEM) ?: R.id.pizza_table
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(LAST_SELECTED_ITEM, bottomNavigationView.selectedItemId)

        val currentFragment = supportFragmentManager.fragments.last()
        supportFragmentManager.putFragment(
            outState,
            currentFragment.javaClass.name,
            currentFragment
        )

    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}