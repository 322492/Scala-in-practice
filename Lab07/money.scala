package object money {

  trait Currency
  case object USD extends Currency
  case object PLN extends Currency
  case object EUR extends Currency

  def $ = USD
  def zł = PLN
  def `€` = EUR

  //map with constants (EUR => PLN, PLN => USD, USD => EUR) representing conversion rates between currencies. Put any values or use real ones from the past.
  val conversion: Map[(Currency, Currency), BigDecimal] = Map(
    (EUR, PLN) -> 4.63,
    (EUR, USD) -> 1.11,
    (USD, PLN) -> 4.16,
    (USD, EUR) -> 0.90,
    (PLN, EUR) -> 0.22,
    (PLN, USD) -> 0.24,
    (PLN, PLN) -> 1.00,
    (USD, USD) -> 1.00,
    (EUR, EUR) -> 1.00
  )

  implicit class numberToMoney(val value: Double) {
    def apply(currency: Currency): Money = currency match {
      case cur: Currency => Money(value, currency)(CurrencyConverter(conversion))
      case _             => throw new Exception("Currency error")
    }
  }

  case class CurrencyConverter(conversion: Map[(Currency, Currency), BigDecimal]) {
    def convert(from: Money, to: Money): BigDecimal =
      from.amount * this.conversion((from.currency, to.currency))
  }

  case class Money(amount: BigDecimal, currency: Currency)(implicit currencyConverter: CurrencyConverter) {

    def +(m: Money): Money = Money(amount + (currencyConverter.convert(m, this)), currency)

    def -(m: Money): Money = Money(amount - (currencyConverter.convert(m, this)), currency)

    def *(x: BigDecimal): Money = Money(amount * x, currency)

    def as(c: Currency): Money = Money(currencyConverter.convert(this, Money(0.0, c)), c)

    def >(m: Money): Boolean = amount > currencyConverter.convert(m, this)

    def <(m: Money): Boolean = amount < currencyConverter.convert(m, this)

    override def toString: String = amount + " " + currency
  }
}
