package com.example.adam.nohome

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.*

class NoUiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_ui)

        hideUi()
    }

    override fun onPause() {
        val activityManager = applicationContext
            .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        activityManager.moveTaskToFront(taskId, 0)
        super.onPause()
    }

    fun goPreviousView(view: View) {
        val intent = Intent(this, NoUiActivity::class.java).apply { }
        startActivity(intent)
    }

    private fun hideUi() {
        window.statusBarColor = Color.TRANSPARENT

        setGestureDetector()
        hideSystemUI()
        setUiListener()
    }

    private fun setGestureDetector() {
        val contentView = window.decorView
        contentView.isClickable = true
        val clickDetector = GestureDetector(this,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    hideSystemUI()
                    return true
                }
            }
        )
        contentView.setOnTouchListener { _, motionEvent -> clickDetector.onTouchEvent(motionEvent) }
    }

        private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        //or View.SYSTEM_UI_FLAG_LOW_PROFILE
                        or View.SYSTEM_UI_FLAG_IMMERSIVE
                )
    }


//    private fun hideSystemUI() {
//        window.decorView.systemUiVisibility = (
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_IMMERSIVE
//                )
//    }

    private fun setUiListener() {
        window.decorView.setOnSystemUiVisibilityChangeListener { flags ->
            run {
                supportActionBar?.hide()
                hideSystemUI()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //handle the click on the back arrow click

        val result = when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
        return result
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideSystemUI()
    }

    // It recognize here only 'Back' button
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_MOVE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event)
    }
}
