package ru.amaderu.musictabs

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //val navController = findNavController(R.id.navgraph)
        val navBar = findViewById<BottomNavigationView>(R.id.bottomNav)
        navBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_music -> {
                    navController.navigate(R.id.musicFragment); true
                }
                R.id.menu_books -> {
                    navController.navigate(R.id.booksFragment); true
                }
                R.id.menu_news -> {
                    navController.navigate(R.id.newsFragment); true
                }
                else -> false
            }
        }

        val badge = navBar.getOrCreateBadge(R.id.menu_news)
        badge.number++
        badge.isVisible = true

        val timer = Timer
        timer.badgeDrawable = badge
        timer.start()

    }

}

object Timer: CountDownTimer(60_000, 2_000){

    lateinit var badgeDrawable: BadgeDrawable
    override fun onTick(millisUntilFinished: Long) {
        badgeDrawable.number++
    }
    override fun onFinish() {
        // Вызывается через 60 секунд
    }
}