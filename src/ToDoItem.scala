import scala.collection.mutable

/**
  * Created by halleyfroeb on 10/18/16.
  */
case class ToDoItem(text: String, var isDone: Boolean) {
  override def toString: String = s"${if (isDone) "[X]" else "[ ]"} ${text}"

  //  override def toString: String = s"$displayCheckbox ${text}"
  // val displayCheckbox = {if (isDone) "[X]" else "[ ]"}
}
object Hello {
  val items: mutable.MutableList[ToDoItem] = mutable.MutableList[ToDoItem]()

  def prompt(s: String) = {
    println(s);
    io.StdIn.readLine()
  }

  def menu = {
    val seq = Seq(choiceOne, choiceTwo, choiceThree, choiceQuit).mkString("\n") //passes a separator
    prompt(s"\nPlease select your choice:\n ${seq}\n").toUpperCase
  }

  case object choiceOne {
    def opt() = {
      items += new ToDoItem(prompt("Enter new item"), false); ""
    }
    override def toString: String = "1. Create to-do item"
  }

  case object choiceTwo {
    def opt(): String = {
      val idx = prompt("Which item do you want to toggle").toInt
      items(idx).isDone = !items(idx).isDone;""
    }
    override def toString: String = "2. Toggle to-do item"
  }

  case object choiceThree {
    def opt() = {items.zipWithIndex.foreach(x => println(s"${x._2}. ${x._1}")); // ; for new line
      ""}
    override def toString: String = "3. List to-do items"
  }

  case object choiceQuit{
    def opt() = "q"
    override def toString: String = "Q. Quit"
  }

  def main(args: Array[String]): Unit = {
    var resp = ""
    while (resp != "q"){
      resp = menu match {
        case "1" => choiceOne.opt()
        case "2" => choiceTwo.opt()
        case "3" => choiceThree.opt()
        case "q" => choiceQuit.opt()
      }
    }
  }

}

