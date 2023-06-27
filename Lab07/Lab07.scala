object Lab07 {
  def main(args: Array[String]): Unit = {
    import money._

    val sum1: Money = 100.01(USD) + 200(EUR)
    println(sum1)

    val sum2: Money = 100.01(zł) + 200($)
    println(sum2)

    val sum3: Money = 5(zł) + 3(PLN) + 20.5(USD)
    println(sum3)

    val sub: Money = 300.01(USD) - 200(EUR)
    println(sub)

    val mult1: Money = 30(zł) * 20
    println(mult1)

    val mult2: Money = 20($) * 11
    println(mult2)

    val conv1: Money = 150.01(USD) as PLN
    println(conv1)

    val conv2: Money = 120.01(USD) as `€`
    println(conv2)

    val compare1: Boolean = 300.30(USD) > 200(`€`)
    println(compare1)

    val compare2: Boolean = 300.30($) < 200(EUR)
    println(compare2)
  }
}
