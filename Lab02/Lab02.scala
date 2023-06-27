object Lab02 {

    def main(args: Array[String]) {
        import numbers._
        import figures._

        val test1 = new Rational(21, 37)
        val test2 = new Rational(17, 3)
        val test4 = new Rational(-1, 2)
        val test5 = new Rational(5, -3)
        val test6 = new Rational(-1, -1)
        val test7 = new Rational(17)
        // val test8 = new Rational(1234, 0)
        val test9 = Rational.zero()

        println(test1)
        println(test4)
        println(test6)
        println(test5)
        println(test7)
        println(test1 + test4)
        println(test2 - test1)
        println(test4 * test5 * test4)
        println(test6 / test7)
        println(test9)
        println(test9 * test1)

        println()

        val p1 = new Point(new Rational(1), new Rational(17))
        val p2 = new Point(new Rational(17), new Rational(17))
        val p3 = new Point(1, 1)
        val p4 = new Point(17, 1)
        val p5 = new Point((21, 1))
        val p6 = new Point((21, 17))
        val p7 = new Point((21, 37))

        val t1 = new Triangle(p3, p5, p7)
        val t2 = new Triangle((0, 0), (3, 3), (7, 1))
        val r = new Rectangle(p1, p6, p5, p3)
        val s1 = new Square(p1, p2, p3, p4)
        val s2 = new Square(new Point(1, 0), new Rational(2))

        println(t1.description)
        println(t1.area)
        println(r.description)
        println(r.area)
        println(s2.description)
        println(s2.area)

        val figList1 = List(s2, s2)
        val figList2 = List(t1, r, s2)

        println(Figure.areaSum(figList1))
        println(Figure.areaSum(figList2))
        Figure.printAll(figList1)
        Figure.printAll(figList2)
    }
}