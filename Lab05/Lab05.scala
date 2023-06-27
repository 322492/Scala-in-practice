object Lab05 {

    def main(args: Array[String]) {
        import plugins._

        val ps1 = new Pluginable with Reverting
        println("Reverting = " + ps1.plugin("ala ma kota"))

        val ps2 = new Pluginable with LowerCasing
        println("LowerCasing = " + ps2.plugin("aLA Ma kOTa"))

        val ps3 = new Pluginable with SingleSpacing
        println("SingleSpacing = " + ps3.plugin("ala ma     kota"))

        val ps4 = new Pluginable with NoSpacing
        println("NoSpacing = " + ps4.plugin("ala ma     kota"))

        val ps5 = new Pluginable with DuplicateRemoval
        println("DuplicateRemoval = " + ps5.plugin("alzaa cda"))

        val ps6 = new Pluginable with Rotating
        println("Rotating = " + ps6.plugin("abc"))

        val ps7 = new Pluginable with Doubling
        println("Doubling = " + ps7.plugin("abcd"))

        val ps8 = new Pluginable with Shortening
        println("Shortening = " + ps8.plugin("ab cd"))


        println("A: " + Actions.actionA.plugin("abcd    q we rty "))
        println("B: " + Actions.actionB.plugin("abcd    q we rty "))
        println("C: " + Actions.actionC.plugin("XDDD    q we rty "))
        println("D: " + Actions.actionD.plugin("XDDD  rfdzbvvdsgzxffvty "))
        println("E: " + Actions.actionE.plugin("A B C D  w x y z"))
        println("F: " + Actions.actionF.plugin("abcdefgh"))
        println("G: " + Actions.actionG.plugin("abcd    q we rty "))

    }
}