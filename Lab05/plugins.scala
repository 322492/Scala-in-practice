package plugins

abstract class Pluginable {
    def plugin(text: String): Option[String] = Option(text)
}

trait Reverting extends Pluginable {
    override def plugin(text: String): Option[String] =
        Option(text) match {
            case Some(s) => super.plugin(s.reverse)
            case None => None
        }
}

trait LowerCasing extends Pluginable {
    override def plugin(text: String): Option[String] =
        Option(text) match {
            case Some(s) => super.plugin(s.toLowerCase)
            case None => None
        }
}

trait SingleSpacing extends Pluginable {
    override def plugin(text: String): Option[String] =
        Option(text) match {
            case Some(s) => super.plugin(s.replaceAll(" +", " "))
            //to remove all duplicate whitespaces (space, tab, new line, carriage return, form feed, vertical tab) use "\\s+"
            case None => None
        }
}

trait NoSpacing extends Pluginable {
    override def plugin(text: String): Option[String] =
        Option(text) match {
            case Some(s) => super.plugin(s.replaceAll(" ", ""))
            case None => None
        }
}

trait DuplicateRemoval extends Pluginable {
    override def plugin(text: String): Option[String] =
        Option(text) match {
            case Some(s) => super.plugin(RemoveDuplicate(s))
            case None => None
        }

    private def RemoveDuplicate(text: String): String =
        text match {
            case "" => ""
            case s =>
                if (s.count(_ == s.charAt(0)) > 1) RemoveDuplicate(s.replaceAll(s.charAt(0).toString, ""))
                else s.charAt(0) + RemoveDuplicate(s.substring(1))
        }
}

trait Rotating extends Pluginable {
    override def plugin(text: String): Option[String] =
        Option(text) match {
            case Some(s) => super.plugin(s.takeRight(1) + s.dropRight(1))
            case None => None
        }
}

trait Doubling extends Pluginable {
    override def plugin(text: String): Option[String] =
        Option(text) match {
            case Some(s) => super.plugin(DoubleEverySecond(s, 0))
            case None => None
        }

    private def DoubleEverySecond(text: String, pos: Int): String =
        text match {
            case "" => ""
            case s =>
                if (pos % 2 == 1) s.charAt(0).toString + s.charAt(0).toString + DoubleEverySecond(s.substring(1), pos + 1)
                else s.charAt(0) + DoubleEverySecond(s.substring(1), pos + 1)
        }
}

trait Shortening extends Pluginable {
    override def plugin(text: String): Option[String] =
        Option(text) match {
            case Some(s) => super.plugin(DeleteEverySecond(s, 0))
            case None => None
        }

    private def DeleteEverySecond(text: String, pos: Int): String =
        text match {
            case "" => ""
            case s =>
                if (pos % 2 == 1) DeleteEverySecond(s.substring(1), pos + 1)
                else s.charAt(0) + DeleteEverySecond(s.substring(1), pos + 1)
        }
}


object Actions {

    val actionA: Pluginable = new Pluginable with Shortening with Doubling with SingleSpacing

    val actionB: Pluginable = new Pluginable with Doubling with Shortening with NoSpacing

    val actionC: Pluginable = new Pluginable with Doubling with LowerCasing

    val actionD: Pluginable = new Pluginable with Rotating with DuplicateRemoval

    val actionE: Pluginable = new Pluginable with Reverting with Doubling with Shortening with NoSpacing

    val actionF: Pluginable = new Pluginable {
        override def plugin(text: String): Option[String] = repeat(text, 5)

        private def repeat(text: String, counter: Int): Option[String] = {
            
            if(counter == 0) Option(text)
            else {
                val oneRotation = new Pluginable with Rotating
                oneRotation.plugin(text) match {
                    case Some(s) => repeat(s, counter - 1)
                    case None => None
                }
            }
        }
    }
    
    val actionG: Pluginable = new Pluginable {
        override def plugin(text: String): Option[String] =
            actionA.plugin(text) match {
                case Some(s) => actionB.plugin(s)
                case None => None
        }
    }
}