package com.example.practice.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.R
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.presentation.news.NewsCounter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val newsCounter = NewsCounter.counter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
        handleNewsCounter()
    }

    private fun setupBottomNavigation() {
        val navView = binding.bottomNavigation
        val navFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navView.setupWithNavController(navFragment.navController)
        navView.setOnItemReselectedListener {}
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK + Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    private fun handleNewsCounter() {
        val disposable = newsCounter
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe { value ->
                val navView = binding.bottomNavigation
                navView.getOrCreateBadge(R.id.navigationNewsFragment).number = value
            }
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
