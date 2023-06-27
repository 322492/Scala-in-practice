package deck

import cards._

class Deck(cards: List[Card]) {

    def pull(): Deck = new Deck(cards.drop(1))

    def push(c: Card): Deck = new Deck(c :: cards)

    def push(color: String, value: String): Deck = new Deck(new Card(color, value) :: cards)
    def push(color: String, value: Int): Deck = new Deck(new Card(color, value) :: cards)

    val isStandard: Boolean = (cards.distinct.size == 52)

    def duplicatesOfCard(card: Card): Int = cards.count(_ == card)

    def amountOfColor(color: String): Int = {
        require(List("Clubs", "Diamonds", "Hearts", "Spades").contains(color))
        cards.count(_.getColor == color)
    }

    def amountOfNumerical(numerical: String): Int = {
        require(List("Ace", "Jack", "Queen", "King", "2", "3", "4", "5", "6", "7", "8", "9", "10").contains(numerical))
        cards.count(_.getValue == numerical)
    }

    val amountWithNumerical: Int =
        List("2", "3", "4", "5", "6", "7", "8", "9", "10").map(amountOfNumerical).sum

    def amountOfFace(face: String): Int = {
        require(List("King", "Queen", "Jack").contains(face))
        cards.count(_.getValue == face)
    }

    val amountWithFace: Int =
        List("Jack", "Queen", "King").map(numerical => cards.count(_.getValue == numerical)).sum

    def getCards: List[Card] = cards

    def description: Unit = println("Deck is: " + cards.map(card => card.shortDescription))
}

import scala.util.Random

object Deck {

  def apply(): Deck = {
    val colors = List("Clubs", "Diamonds", "Hearts", "Spades")
    val values = List("Ace", "Jack", "Queen", "King", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    val cards = for { c <- colors; v <- values } yield new Card(c, v)

    new Deck(Random.shuffle(cards))
  }
}
