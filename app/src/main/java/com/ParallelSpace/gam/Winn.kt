package com.ParallelSpace.gam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ParallelSpace.R
import com.ParallelSpace.databinding.ActivityWinnBinding

class Winn : AppCompatActivity() {
    private lateinit var bindWin: ActivityWinnBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindWin = ActivityWinnBinding.inflate(layoutInflater)
        setContentView(bindWin.root)
        bindWin.btnAgain.setOnClickListener {
            startActivity(Intent(this, Gamm::class.java))
        }
    }
}