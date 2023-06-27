object Utils {

    def isSorted(as: List[Int], ordering: (Int, Int) => Boolean): Boolean =
        as match {
            case Nil => true
            case x::Nil => true
            case x::y::as => ordering(x, y) && isSorted(y::as, ordering)
        }

    def isAscSorted(as: List[Int]) = isSorted(as, _<_) // (as == as.sorted)

    def isDescSorted(as: List[Int]) = isSorted(as, _>_)

    // applies binary operator to a start value and all elements of l, going left to right.
    def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B =
        l match {
            case Nil => z
            case x::l => foldLeft(l, f(z, x))(f)
        }

    def sum(l: List[Int]) = foldLeft(l, 0)(_+_) // l.sum

    def length[A](l: List[A]) = foldLeft(l, 0)((currSize: Int, _) => currSize + 1) // l.length

    //compose two unary functions:compose(f,g)(x) = f(g(x)).
    def compose[A, B, C](f: B => C, g: A => B)(x: A) = f(g(x))

    // takes unary function f with integer n & returns the n-th repeated application of the function. For example: repeated(f, 3) = f(f(f(3)))
    // WARN: I deleted "[A, B]"! Because n is Int so f should also get Int and if we want to repeat it then f also should return Int
    def repeated(f: Int => Int, n: Int) = {
        def myRepeated(f: Int => Int, n: Int, x: Int): Int = {
            n match{
                case 0 => x
                case _ => f(myRepeated(f, n-1, x))
            }
        }
        myRepeated(f, n, n)
    }

    // converts a binary function f of two arguments into a function of one argument that partially applies f.
    // For example, when def add(a: Int, b: Int) = a + b, than curry(add)(1)(1) == add(1, 1)
    def curry[A, B, C](f: (A, B) => C) = (a: A) => (b: B) => f(a, b)

    //reverse of curry function. For example, uncurry(f)(1, 1)(1) == f(1)(1)
    def uncurry[A, B, C](f: A => B => C) = (a: A, b: B) => f(a)(b)

    // catch any exception, log the error & throw the ex exception instead
    def unSafe[T](ex: Exception)(block: => T) = {
        try{
            block
        } catch {
            case err : Exception => println(err); throw ex
        }
    }

    def main(args: Array[String]) {
        def cmp(x: Int, y: Int): Boolean = x < y
        val lst = List(2, 1, 3, 7)
        val lstAsc = List(1, 2, 3, 7)
        val listDesc = List(7, 3, 2, 1)

        println(isSorted(lst, cmp))
        println(isAscSorted(lst))
        println(isAscSorted(lstAsc))
        println(isDescSorted(lst))
        println(isDescSorted(listDesc))

        val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        println(foldLeft(numbers, 0)(_+_))
        println(sum(numbers))
        println(length(numbers))
        def f(x: Int): List[Int] = List(0, 0, x, 0, 0)
        def g(x: String): Int = x.toInt
        println( compose(f, g)("17") )

        def pow(x: Int): Int = x * x
        println(repeated(pow, 3), "= 81 * 81 = pow(81) = pow(pow(9)) = pow(pow(pow(3)))")

        def add(a: Int, b: Int) = a + b
        println(curry(add)(1)(1) == add(1, 1))

        // the line below is copy paste from https://stackoverflow.com/questions/38243530/custom-exception-in-scala
        final case class MyException(private val message: String = "", private val cause: Throwable = None.orNull) extends Exception(message, cause)

        //unSafe(MyException("0 DIVISION!!!")){val xd = 0/0}
        def XD() = throw new RuntimeException
        unSafe(MyException("MY ANOTHER ERROR")) {println(XD())}
    }
}
