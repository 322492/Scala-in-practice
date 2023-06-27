package numbers

class Rational(n: Int, d: Int = 1){
    require(d != 0, "Denominator must be non-zero.")

    private def reduce(n: Int, d: Int): (Int, Int) = {
        def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a%b)

        val g = gcd(n.abs, d.abs)
        var (numer, denom) = (n/g, d/g)

        if(denom < 0) return (-numer, -denom)
        
        return (numer, denom)
    }

    val (numer, denom) = reduce(n, d)

    def +(other: Rational): Rational = {
      val resNumer: Int = other.numer * denom + other.denom * numer
      val resDenom: Int = other.denom * denom

      new Rational(resNumer, resDenom)
    }

    def -(other: Rational): Rational = {
      val resNumer = other.denom * numer - other.numer * denom
      val resDenom = other.denom * denom

      new Rational(resNumer, resDenom)
    }

    def *(other: Rational): Rational = {
      val resNumer = other.numer * numer
      val resDenom = other.denom * denom

      new Rational(resNumer, resDenom)
    }

    def /(other: Rational): Rational = {
      val resNumer = other.denom * numer
      val resDenom = other.numer * denom

      new Rational(resNumer, resDenom)
    }

    override def toString: String = {
      if (numer == 0) return "0"

      val resInt = numer.abs / denom
      val resRest = numer.abs - resInt * denom

      ((if(numer < 0) "-" else "") + (if(resInt > 0) s"$resInt" else "") + (if(resInt > 0 && resRest > 0) " " else "") + (if(resRest > 0) s"$resRest/$denom" else ""))
    }

    def toDouble: Double = {
      numer / denom
    }
}

object Rational {
  def zero(): Rational = new Rational(0)

  def one(): Rational = new Rational(1)
}