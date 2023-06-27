
object Lab04 {

    def main(args: Array[String]) {
        import cards._
        import deck._
        import games._

        val c1 = new Card("Hearts", "Queen")
        val c2 = new Card("Diamonds", "6")
        val c3 = new Card("Clubs", 7)
        //val c4 = new Card("Clubs", 2137)
        c1.description
        c2.description
        c3.description
        println(c1.getColor)

        var d1 = new Deck(List(c1, c2, c3, new Card("Spades", "Ace"), new Card("Spades", "King")))
        d1.description
        val d2 = d1.pull()
        d2.description
        d1 = d1.push(c1)
        d1 = d1.push("Hearts", "Queen")
        d1 = d1.push("Hearts", 5)
        d1.description
        println(d1.isStandard)
        println(d1.duplicatesOfCard(c1))
        println(d1.amountOfColor("Clubs"))
        println(d1.amountOfNumerical("Ace"))
        println(d1.amountWithNumerical)
        println(d1.amountOfFace("Jack"))
        println(d1.amountWithFace)

        val d3 = Deck.apply()
        d3.description
        println(d3.isStandard)

        val b1 = new Blackjack(d1)
        b1.play(7)
        val b2 = new Blackjack(d2)
        println(b2.all21)
        b2.first21()

        val b3 = Blackjack.apply(3)
        b3.play(110)
    }
}