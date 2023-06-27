package cards

class Card(color: String, value: String){
    require(List("Clubs", "Diamonds", "Hearts", "Spades").contains(color) &&
            List("Ace", "Jack", "Queen", "King", "2", "3", "4", "5", "6", "7", "8", "9", "10").contains(value))

    def this(color: String, value: Int) = this(color, value.toString)
    def getColor: String = color
    def getValue: String = value

    def description: Unit = println("Card is " + value + " of " + color)
    def shortDescription: String = value + " of " + color
}

