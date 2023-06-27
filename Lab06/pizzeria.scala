package pizzeria

case class Pizza(
    pizzaType: PizzaType,
    size: Size,
    crust: Crust,
    extraMeat: Option[List[Meat]], // optional meat
    extraTopping: Option[List[Topping]] // optional topping
) {

  // pretty print the pizza
  override def toString() =
    size + " " + pizzaType + " on " + crust + " crust with " + extraMeat.getOrElse("no extra meat") + " and " + extraTopping.getOrElse("no extra toppings");

  val extraMeatCost: Double = extraMeat match {
    case Some(meat) => meat.map(_.price).sum
    case None       => 0.0
  }

  val extraToppingsCost: Double = extraTopping match {
    case Some(toppings) => toppings.map(_.price).sum
    case None           => 0.0
  }

  // calculated price for pizza. When pizzaType=small than price is 90% & if type=large than price is 150%
  val price: Double = size.multiplier * pizzaType.price + extraMeatCost + extraToppingsCost;
}

abstract class PizzaType(val price: Double)
case object Margarita extends PizzaType(5.0)
case object Pepperoni extends PizzaType(6.5)
case object Funghi extends PizzaType(7.0)

abstract class Size(val multiplier: Double)
case object Small extends Size(0.9)
case object Regular extends Size(1.0)
case object Large extends Size(1.5)

abstract class Crust
case object Thin extends Crust
case object Thick extends Crust

abstract class Topping(val price: Double)
case object Ketchup extends Topping(0.5)
case object Garlic extends Topping(0.5)

abstract class Meat(val price: Double)
case object Salami extends Meat(1.0)

abstract class Drink(val price: Double)
case object Lemonade extends Drink(2.0)

abstract class Discount
case object Student extends Discount
case object Senior extends Discount
