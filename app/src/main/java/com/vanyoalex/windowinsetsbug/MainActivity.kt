package com.vanyoalex.windowinsetsbug

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.core.view.WindowInsetsCompat.Type.systemBars
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import kotlinx.android.synthetic.main.activity_main.view

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val rect = Rect()
            v.getWindowVisibleDisplayFrame(rect)

            Log.d("MainActivity", "getWindowVisibleDisplayFrame: $rect")
            Log.d("MainActivity", "insets.getInsets(ime()): ${insets.getInsets(ime())}")
            Log.d("MainActivity", "insets.systemWindowInsetBottom: ${insets.systemWindowInsetBottom}")

            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                updateMargins(
                    top = insets.getInsets(systemBars()).top,
                    bottom = insets.getInsets(ime() or systemBars()).bottom
                )
            }

            insets
        }

        view.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
