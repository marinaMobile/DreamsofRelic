package com.ParallelSpace.gam

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.ParallelSpace.R
import kotlin.math.min

class Adapter (
    private val context: Context,
    private val boardSize: Size,
    private val cards: List<Cards>,
    private val cardClickListener: CardClickListener
) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    companion object{
        private const val MARGIN_SIZE = 10
    }

    interface  CardClickListener {
        fun onCardClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardWidth = parent.width / boardSize.getWidth() - (2 * MARGIN_SIZE)
        val cardHeight = parent.height / boardSize.getHeight() - (2 * MARGIN_SIZE)
        val cardSideLength = min(cardWidth, cardHeight)
        val view =  LayoutInflater.from(context).inflate(R.layout.crd, parent, false)
        val layoutParams  =  view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = cardSideLength
        layoutParams.height = cardSideLength
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        return ViewHolder(view)
    }

    override fun getItemCount() = boardSize.numCards

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    };
    inner  class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private  val imageButton =  itemView.findViewById<ImageButton>(R.id.imageButton)

        fun bind(position: Int) {
            val memoryCard = cards[position]
            imageButton.setImageResource(if (cards[position].isFaceUp) cards[position].identifier else R.drawable.bac)
            val colorStateList =  if(memoryCard.isMatched) ContextCompat.getColorStateList(context,
                R.color.black
            ) else null
            ViewCompat.setBackgroundTintList(imageButton, colorStateList)
            imageButton.setOnClickListener{
                cardClickListener.onCardClicked(position)
            }
        }
    }
}