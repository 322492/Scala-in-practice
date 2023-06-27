package orders

import pizzeria._

class Order(
    name: String,
    address: String,
    phone: String, // mandatory validated phone-number (hint: regex)
    pizzas: Option[List[Pizza]],
    drinks: Option[List[Drink]],
    discount: Option[Discount] = None, // optional value
    specialInfo: Option[String] = None // optional text, like: “Ring doesnt work, please knock”
) {
  require(phone.matches("""^\+?\d{6,18}"""))

  // pretty print the order
  override def toString() = {
    s""" Name: $name \n Address: $address \n Phone: $phone \n Pizzas:
        ${pizzas match {
        case Some(pizzasList) => pizzasList.mkString("\n        ")
        case None             => "---"
      }} \n Drinks: ${drinks match {
        case Some(drinksList) => drinksList.mkString(", ")
        case None             => "---"
      }} \n Discount: ${discount.getOrElse("none")} \n Special Info: ${specialInfo.getOrElse("none")}"""
  }

  def extraMeatPrice: Option[Double] = pizzas match {
    case Some(pizzaList) => Option(pizzaList.map(_.extraMeatCost).sum)
    case None            => None
  }

  def pizzasPrice: Option[Double] = pizzas match {
    case Some(pizzasList) => Option(pizzasList.map(_.price).sum)
    case None             => None
  }

  def drinksPrice: Option[Double] = drinks match {
    case Some(drinksList) => Option(drinksList.map(_.price).sum)
    case None             => None
  }

  // total price of all pizzas by type (Margarita, Pepperoni & Funghi)
  def priceByType(pizzaType: PizzaType): Option[Double] = pizzas match {
    case Some(pizzasList) => Option(pizzasList.filter(_.pizzaType == pizzaType).map(_.price).sum)
    case None             => None
  }

  // total price of order. When discount=student than price for all pizzas is reduced by -5% & if discount=senior than price for all pizzas & drinks is reduced by -7%
  val price: Double = {
    val pizzaPriceNoDiscount: Double = pizzasPrice.getOrElse(0.0)
    val drinksPriceNoDiscount: Double = drinksPrice.getOrElse(0.0)

    discount match {
      case Some(value) => value match {
        case Student => 0.95 * pizzaPriceNoDiscount + drinksPriceNoDiscount
        case Senior  => 0.93 * (pizzaPriceNoDiscount + drinksPriceNoDiscount)
        }
      case None => drinksPriceNoDiscount + pizzaPriceNoDiscount
    }
  }
}
