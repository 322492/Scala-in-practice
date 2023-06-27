object Lab01 {

    //scalar product of two vectors xs and ys
    def scalarUgly(xs: List[Int], ys: List[Int]) = {
        var res = 0
        var i = 0
        do{
            res += xs(i) * ys(i)
            i += 1
        }while(i < xs.size)
        res
    }
    def scalar(xs: List[Int], ys: List[Int]) = {
        (for(i <- 0 until xs.size) yield xs(i) * ys(i)).sum
    }

    //quicksort algorithm
    import scala.collection.mutable.ListBuffer
    def sortUgly(xs: List[Int]): List[Int] = {

        def partition(xs: List[Int], pivot: Int) = {
            var left = new ListBuffer[Int]()
            var right = new ListBuffer[Int]()
            var i = 1

            do{
                if(xs(i)<=pivot)
                    left += xs(i)
                else
                    right += xs(i)
                i += 1
            }while(i < xs.size)

            (left, right)
        }

        if(xs.size < 2)
            xs
        else
        {
            var (left, right) = partition(xs, xs(0))
            var res = sortUgly(left.toList) ++ (xs(0) :: sortUgly(right.toList))
            res
        }
    }
    def sort(xs: List[Int]): List[Int] = {
        xs match{
            case Nil => Nil
            case x::xs =>{
                val left = sort(xs.filter(_ < x))
                val right = sort(xs.filter(_ >= x))
                left ++ (x :: right)
            }
        }
    }

    //checks if n is prime
    import scala.math.sqrt
    def isPrimeUgly(n: Int): Boolean = {
        if(n <= 2)
            return true
        var i = 2
        do{
            if(n % i == 0)
                return false
            i += 1
        }while(i*i < n)

        // println(n + " is prime.")
        return true
    }
    def isPrime(n: Int): Boolean = {
        !((2 to sqrt(n).toInt) exists (n % _ == 0))
    }

    //for given positive integer n, find all pairs of integers i and j, where 1 ≤ j < i < n such that i + j is prime
    def primePairsUgly(n : Int): List[(Int, Int)] = {
        var res = List[(Int, Int)]()
        var i = 2

        do{
            var j = 1
            do{
                if(isPrime(i+j))
                    res = (i, j) :: res
                j += 1
            }while(j < i)
            i += 1
        }while(i<n)
        res
    }
    def primePairs(n : Int): List[(Int, Int)] = {
        (for (i <- 2 until n; j <- 1 until i if(isPrime(i+j))) yield (i, j)).toList
    }

    //create a list with all lines from given file
    import scala.io.Source
    import java.util.Scanner
    def fileLinesUgly(file: java.io.File): List[String] = {
        var res = List[(String)]()
        var scanner = new Scanner(file)

        if(scanner.hasNextLine())
            do{
                res = scanner.nextLine :: res
            }while(scanner.hasNextLine())

        scanner.close()
        res.reverse
    }
    def fileLines(file: java.io.File): List[String] = {
        Source.fromFile(file).getLines().toList
    }

    //print names of all .scala files which are in filesHere & are non empty
    val filesHere = new java.io.File(".").listFiles
    def printNonEmptyUgly(pattern: String): Unit = {
        var i = 0
        if(filesHere.length > 0)
            do{
                if(filesHere(i).getName.endsWith(pattern) && filesHere(i).length != 0)
                    println(filesHere(i).getName)
                i += 1
            }while(i < filesHere.size)
    }
    def printNonEmpty(pattern: String): Unit = {
        for(file <- filesHere if(file.getName.endsWith(pattern) && file.length != 0))
                println(file.getName)
    }

    def main(args: Array[String]) {
        var xs = List(2, 1, 3, 7)
        var ys = List(7, 3, 1, 2)
        println(scalarUgly(xs, ys))
        println(scalar(xs, ys))
        println(sortUgly(xs))
        println(sort(ys))
        println(isPrimeUgly(55))
        println(isPrime(2137))
        println(primePairsUgly(17))
        println(primePairs(17))
        val ff = new java.io.File("porcelana.txt")
        println(fileLinesUgly(ff))
        println(fileLines(ff))
        printNonEmptyUgly("scala")
        printNonEmpty("scala")
    }
}