package com.ParallelSpace.gam

enum class Size(val numCards: Int) {
    EASY( 12);
    fun getWidth(): Int{
        return when(this){
            EASY -> 3
        }
    }
    fun getHeight(): Int {
        return numCards / getWidth()
    }
    fun getNumPairs(): Int{
        return numCards / 2;
    }
}