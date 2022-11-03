package com.ParallelSpace.gam

class Gamik(private val boardSize: Size){
    val cards: List <Cards>
    var numPairsFound = 0
    private var numCardFlips = 0
    private var indexOfSingleSelectedCard: Int? = null
    init{
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages ).shuffled()
        cards =  randomizedImages.map {Cards(it)}
    }

    fun flipperCard(position: Int): Boolean {
        numCardFlips++
        val card: Cards = cards[position]

        var foundMatch = false
        if(indexOfSingleSelectedCard == null){
            restoreCards()
            indexOfSingleSelectedCard = position
        }else{
            foundMatch =  checkerForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        return  foundMatch
    }

    private fun checkerForMatch(position1: Int, position2: Int): Boolean {
        if(cards[position1].identifier != cards[position2].identifier){
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    private fun restoreCards() {
        for (card : Cards in cards){
            if(!card.isMatched){
                card.isFaceUp = false
            }
        }
    }

    fun winGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return  cards[position].isFaceUp
    }
}