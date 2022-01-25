package com.example.practice.presentation.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.R
import com.example.practice.databinding.ActivityLoginBinding
import com.example.practice.presentation.MainActivity
import com.example.practice.utils.setupToolbar
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    private val binding by viewBinding(ActivityLoginBinding::bind)
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setupToolbar(binding.toolbar)

        val emailDispose = binding.email.textChanges()
        val passwordDispose = binding.password.textChanges()

        val dispose = Observable.combineLatest(emailDispose, passwordDispose, { email, pass ->
            binding.login.isEnabled = (email.length >= SYMBOL_COUNT && pass.length >= SYMBOL_COUNT)
        }).subscribe()

        compositeDisposable.addAll(dispose)
        binding.login.setOnClickListener { MainActivity.start(this) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK + Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        private const val SYMBOL_COUNT = 6
    }
}
