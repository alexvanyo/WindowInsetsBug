package com.vanyoalex.windowinsetsbug

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.core.view.WindowInsetsCompat.Type.systemBars
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val rect = Rect()
            v.getWindowVisibleDisplayFrame(rect)

            Log.d("SecondActivity", "getWindowVisibleDisplayFrame: $rect")
            Log.d("SecondActivity", "insets.getInsets(ime()): ${insets.getInsets(ime())}")
            Log.d("SecondActivity", "insets.systemWindowInsetBottom: ${insets.systemWindowInsetBottom}")

            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                updateMargins(
                    top = insets.getInsets(systemBars()).top,
                    bottom = insets.getInsets(ime() or systemBars()).bottom
                )
            }
            insets
        }

        view.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}
