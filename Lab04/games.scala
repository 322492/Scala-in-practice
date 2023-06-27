package games

import cards._
import deck._
import scala.util.Random

class Blackjack(deck: Deck) {

    var aceValue = -1
    while(aceValue != 1 && aceValue != 11){
        println("Player can choose numerical value for Ace card. Pick a number 1 or 11:")
        aceValue = scala.io.StdIn.readLine().toInt
    }

    def points(card: Card): Int = {
        if(card.getValue == "Ace") aceValue
        else if (List("Jack", "Queen", "King").contains(card.getValue)) 10
        else card.getValue.toInt
    }

    def play(n: Int): Unit = {
        for (c <- deck.getCards.slice(0, n))
            println("Card: " + c.getValue + " of " + c.getColor + " for " + points(c) + " points")
        println("Sum of points: " + deck.getCards.slice(0, n).map(c => points(c)).sum)
    }

    def search(deck: Deck, expectedSum: Int): List[List[Card]] = {
        if(expectedSum == 0 || deck.getCards.isEmpty) List()
        val firstElem = deck.getCards.head
        var withFirst = List()
        var withoutFirst = List()
        for(lst <- search(deck.pull, expectedSum - points(firstElem))) (firstElem :: lst) :: withFirst
        for(lst <- search(deck.pull, expectedSum)) lst :: withoutFirst
        withFirst ::: withoutFirst
    }
    lazy val all21: List[List[Card]] = search(deck, 6)

    def firstNotGreater(cards: List[Card], number: Int): List[Card] = {
        if(number <= 0) List()
        var result = List()
        for(i <- 0 to cards.size - 1){
            val c = cards(i)
            val cPoints = points(c)
            if(cPoints <= number){
                val res = c :: firstNotGreater(cards.drop(i + 1), number - cPoints)
                if(res.map(points).sum == number - cPoints) result
            }
        }
        result
    }
    def first21(expectedSum: Int = 21): Unit = {
        val subsequence = firstNotGreater(deck.getCards, 21)
        if(subsequence.size == 0) println("No subsequence adds up to 21!")
        else{
            println("First subsequence that adds up to 21: ")
            for (c <- subsequence)
                println("Card: " + c.getValue + " of " + c.getColor)
        }
    }
}

object Blackjack {

  def apply(numOfDecks: Int): Blackjack = {
    val oneDeck = Deck.apply()
    val cards = List.fill(numOfDecks)(oneDeck.getCards).flatten

    new Blackjack(new Deck(Random.shuffle(cards)))
  }
}