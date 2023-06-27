package figures

import numbers._

    class Point(val x: Rational, val y: Rational) {
        def distance(other: Point): Double = {
            scala.math.sqrt(((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y)).toDouble)
        }
        def this(xy: (Int, Int)) = this(new Rational(xy._1), new Rational(xy._2))
        def this(x: Int, y: Int) = this(new Rational(x), new Rational(y))
    }

    abstract class Figure {
        def area: Double
        def description: String
    }

    // I hope I don't have to validate user input ...

    class Triangle(a: Point, b: Point, c: Point) extends Figure {
        def area: Double = (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)).toDouble / 2.0
        def description: String = "Triangle"

        def this(a: (Int, Int), b: (Int, Int), c: (Int, Int)) = {
            this(new Point(a), new Point(b), new Point(c))
        }
    }

    class Rectangle(a: Point, b: Point, c: Point, d: Point) extends Figure {
        def area: Double = a.distance(b) * a.distance(c)
        def description: String = "Rectangle"

        def this(a: (Int, Int), b: (Int, Int), c: (Int, Int), d: (Int, Int)) = {
            this(new Point(a), new Point(b), new Point(c), new Point(d))
        }
    }

    class Square(a: Point, b: Point, c: Point, d: Point) extends Figure {
        def area: Double = a.distance(b) * a.distance(c)
        def description: String = "Square"

        def this(a: (Int, Int), b: (Int, Int), c: (Int, Int), d: (Int, Int)) = {
            this(new Point(a), new Point(b), new Point(c), new Point(d))
        }
        def this(center: Point, size: Rational) = {
            this(new Point(center.x - size / new Rational(2), center.y + size / new Rational(2)), new Point(center.x + size / new Rational(2), center.y + size / new Rational(2)),
                 new Point(center.x - size / new Rational(2), center.y - size / new Rational(2)), new Point(center.x + size / new Rational(2), center.y - size / new Rational(2)))
        }
    }

    object Figure {
        def areaSum(figs: List[Figure]): Double = {
            (for (f <- figs) yield f.area).sum
        }

        def printAll(figs: List[Figure]): Unit = {
            for (f <- figs) println(f.description)
        }
    }

