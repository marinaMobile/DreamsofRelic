package com.ParallelSpace.gam

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ParallelSpace.R
import com.ParallelSpace.databinding.ActivityGammBinding

class Gamm : AppCompatActivity() {
    private lateinit var adapter: Adapter
    private  lateinit var rvBoard: RecyclerView
    private lateinit var  tvNumPairs: TextView
    private lateinit var clRoot: ConstraintLayout
    private lateinit var game: Gamik
    private lateinit var bindGamm: ActivityGammBinding
    private var boardSize: Size = Size.EASY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindGamm = ActivityGammBinding.inflate(layoutInflater)
        setContentView(bindGamm.root)
        clRoot = bindGamm.constLayRoot
        rvBoard = bindGamm.rvBoard
        tvNumPairs = bindGamm.tvNumPairs
        setUpBoard()
    }

    private fun setUpBoard() {
        when(boardSize){
            Size.EASY->{
                tvNumPairs.text = "Pairs: 0 / 6"
            }
        }

        game = Gamik(boardSize)

        adapter =  Adapter(this, boardSize, game.cards, object : Adapter.CardClickListener{
            override fun onCardClicked(position: Int) {
                updateGameWithFlip(position)
            }

        })
        rvBoard.adapter = adapter
        rvBoard.setHasFixedSize(true);
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())

    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun updateGameWithFlip(position: Int) {

        if(game.flipperCard(position)){

            tvNumPairs.text = "Pairs: ${game.numPairsFound} / ${boardSize.getNumPairs()}"
            if(game.winGame()){
                startActivity(Intent(this, Winn::class.java))
                finish()
            }
        }

        adapter.notifyDataSetChanged()

    }
}