object Lab06 {
  def main(args: Array[String]): Unit = {
    import pizzeria._
    import orders._

    val p1 =
      Pizza(Margarita, Large, Thick, None, None)
    val p2 = Pizza(Funghi, Small, Thin, None, Option(List(Garlic, Ketchup)))
    val p3 = Pizza(Pepperoni, Regular, Thin, Option(List(Salami, Salami)), Option(List(Garlic, Ketchup, Ketchup, Ketchup)))

    println(p1.toString() + ", price: " + p1.price)
    println(p2.toString() + ", price: " + p2.price)
    println(p3.toString() + ", price: " + p3.price)
    println("")

    val o1 = new Order(
      "Holmes",
      "221B Baker Street, London",
      "123456789",
      Option(List(p1, p2)),
      None,
      None,
      Option("Ring doesn't work, please knock")
    )
    val o2 = new Order(
      "Papaj",
      "Vatican",
      "213721372",
      Option(List(p1, p3, p3, p3)),
      Option(List(Lemonade, Lemonade)),
      Option(Senior),
      None
    )

    println(o1)
    println("")
    println(o2)
    println("")
    println("Extra meat O2: " + o2.extraMeatPrice.get)
    println("Pizzas price O1: " + o1.pizzasPrice.get)
    println("Drinks price O2: " + o2.drinksPrice.get)
    println("Margherita price O1: " + o1.priceByType(Margarita).get)
    println("Price O1: " + o1.price)
    println("Price O2: " + o2.price)
  }
}
