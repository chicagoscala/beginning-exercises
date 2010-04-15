package solutions.ninetynine

object NinetyNineProblems {

  /** P01 (*) Find the last element of a list. */
  def lastEasy(l: List[_]) = l.reverse.head
  def last[A](l: List[A]): A = l match {
    case Nil => throw new NoSuchElementException
    case head :: Nil => head
    case head :: tail => last(tail)
  }
  
  /** P02 (*) Find the last but one element of a list. */
  def penultimate[A](l: List[A]): A = l.reverse.tail.head
  
  /** P03 (*) Find the Kth element of a list, where 0 is the 1st element. */
  def nth[A](n: Int, l: List[A]): A = n match {
    case 0 => l head
    case _ => l match {
      case Nil => throw new IllegalArgumentException("n > list.length")
      case _ => nth(n-1, l.tail)
    }
  }
  
  /** P04 (*) Find the number of elements of a list. */
  def listLength(l: List[_]): Int = {
    def _len(l: List[_], len: Int): Int = l match {
      case head :: tail => _len(tail, len + 1)
      case Nil => len
    }
    _len(l, 0)
  }
  
  /** P05 (*) Reverse a list. */
  def reverseEasy[A](l: List[A]): List[A] = l reverse
  def reverse[A](l: List[A]): List[A] = {
    def _reverse(l: List[A], accum: List[A]): List[A] = l match {
      case Nil => accum
      case h :: t => _reverse(t, h :: accum)
    }
    _reverse(l, Nil)
  }

  /** P06 (*) Find out whether a list is a palindrome. */
  def isPalindrome(l: List[_]): Boolean = l.reverse == l

  /** P07 (**) Flatten a nested list structure. */
  def flatten(l: List[_]): List[_] = l match {
      case Nil => Nil
      case _ => (l head match {
          case l2: List[_] => flatten(l2)
          case h => List(h)
      }) ::: flatten(l tail)
  }

  /** P08 (**) Eliminate consecutive duplicates of list elements.
   * If a list contains repeated elements they should be replaced with a single copy of the element.
   * The order of the elements should not be changed.
   */
  def compress(l: List[_]): List[_] = {
    def _compress(l: List[_], accum: List[_]): List[_] = l match {
      case Nil => accum
      case _ => {
        val accum2 = l head match {
          case h if (accum != Nil && h == accum.head) => accum
          case h => h :: accum
        }
        _compress(l tail, accum2)
      }
    }

    _compress(l, Nil).reverse
  }
  
  /** P09 (**) Pack consecutive duplicates of list elements into sublists."
   * If a list contains repeated elements they should be placed in separate sublists.
   */
  def pack(l: List[_]): List[List[_]] = {
    def _pack(l: List[_], accum: List[List[_]]): List[List[_]] = l match {
      case Nil => accum
      case _ => {
        val accum2 = if (accum == Nil) 
          List(List(l head))
        else {
          val accumHeadElem = accum.head.head
          l head match {
            case h if (h == accumHeadElem) => (h :: accum.head) :: accum.tail
            case h => List(h) :: accum
          }
        }
        _pack(l tail, accum2)
      }
    }

    _pack(l, Nil).reverse
  }

  /** P10 (*) Run-length encoding of a list.
   * Use the result of problem P09 to implement the so-called run-length encoding
   * data compression method. Consecutive duplicates of elements are encoded as 
   * tuples (N, E) where N is the number of duplicates of the element E.
   */
  def encode(l: List[_]): List[Pair[Int,_]] = pack(l) map { x: List[_] => (x.length, x.head) }
  
  /** P11 (*) Modified run-length encoding.
   * Modify the result of problem P10 in such a way that if an element has no duplicates
   * it is simply copied into the result list. Only elements with duplicates are transferred
   * as (N, E) terms.
   */
  def encodeModified(l: List[_]): List[_] = 
    pack(l) map { 
      x: List[_] => x.length match {
        case 1 => x.head
        case n => (n, x.head) 
      }
    }
  
  /** P12 (**) Decode a run-length encoded list. 
   * Given a run-length code list generated as specified in problem P10, 
   * construct its uncompressed version.
   */
  def decode(l: List[Pair[Int,_]]): List[_] = {
    def _decode(l: List[_], accum: List[_]): List[_] = l match {
      case Nil => accum
      case head :: tail => {
        val prefix = head match {
          case (n :Int, x) => (for (i <- 1 to n) yield x).toList
          case x => List(x)
        }
        _decode(tail, prefix ::: accum)
      }
    }
    _decode(l, Nil).reverse
  }
  
  /** P13 (**) Run-length encoding of a list (direct solution).
   * Implement the so-called run-length encoding data compression method directly. 
   * I.e. don't use other methods you've written (like P09's pack); do all the work directly.
   */
  def encodeDirect[_](l: List[_]): List[Pair[Int,_]] = {
    def _encode(l: List[_], accum: List[(Int,_)]): List[(Int,_)] = l match {
      case Nil => accum
      case _ => {
        val accum2: List[(Int,_)] = if (accum == Nil) 
          List((1, l.head))
        else {
          val accumHeadCount = accum.head._1
          val accumHeadValue = accum.head._2
          l head match {
            case h if (h == accumHeadValue) => (accumHeadCount + 1, h) :: accum.tail
            case h => (1, h) :: accum
          }
        }
        _encode(l tail, accum2)
      }
    }

    _encode(l, Nil).reverse
  }

  /** P14 (*) Duplicate the elements of a list. */
  def duplicate(l: List[_]): List[_] = {
    def _dup(l: List[_], accum: List[_]): List[_] = l match {
      case Nil => accum
      case h :: t => _dup(t, h :: h :: accum)
    }
    _dup(l, Nil).reverse
  }

  /** P15 (**) Duplicate the elements of a list a given number of times. */
  def duplicateN(n: Int, l: List[_]): List[_] = {
    def _dupN(l: List[_], accum: List[_]): List[_] = l match {
      case Nil => accum
      case h :: t => _dupN(t, (for (i <- 1 to n) yield h ).toList ::: accum)
    }
    _dupN(l, Nil).reverse
  }
  
  /** P16 (**) Drop every Nth element from a list. */
  def drop(n: Int, l: List[_]): List[_] = {
    def _drop(l: List[_], i: Int, accum: List[_]): List[_] = l match {
      case Nil => accum
      case h :: t => i match {
        case _ if i % n == 0 => _drop(t, i+1, accum)
        case _ => _drop(t, i+1, h :: accum)
      }
    }
    _drop(l, 1, Nil).reverse
  }

  /** P17 (*) Split a list into two parts. 
   * The length of the first part is given. Use a Pair for your result.
   */
  def split(n: Int, l: List[_]): Pair[List[_], List[_]] = {
    def _split(l: List[_], i: Int, accum: List[_]): Pair[List[_], List[_]] = l match {
      case Nil => (accum.reverse, Nil)
      case h :: t => i match {
        case _ if i == n => (accum.reverse, h :: t)
        case _ => _split(t, i+1, h :: accum)
      }
    }
    _split(l, 0, Nil)
  }

  /** P18 (**) Extract a slice from a list.
   * Given two indices, I and K, the slice is the list containing the elements
   * from and including the Ith element up to but not including the Kth element 
   * of the original list. Start counting the elements with 0.
   */
  def slice(start: Int, end: Int, l: List[_]): List[_] = {
    def _slice(l: List[_], i: Int, accum: List[_]): List[_] = l match {
      case Nil => accum
      case h :: t => i match {
        case _ if i >= start && i < end => _slice(t, i+1, h :: accum)
        case _ if i >= end => accum
        case _ => _slice(t, i+1, accum)
      }
    }
    _slice(l, 0, Nil).reverse          
  }

  /** P19 (**) Rotate a list N places to the left. */
  def rotate(n: Int, l: List[_]): List[_] = {
    def _rotate(n: Int, l: List[_]) = {
      val l12 = split(n, l)
      l12._2 ::: l12._1                
    }
    n match {
      case _ if n >= 0 => _rotate(n, l)
      case _           => _rotate(-n, l.reverse).reverse
    }
  }

  /** P20 (*) Remove the Kth element from a list. 
   * Return the list and the removed element in a Tuple. Elements are numbered from 0.
   */
  def removeAt(n: Int, l: List[_]): Pair[List[_],_] = {
    val l12 = split(n, l)
    (l12._1 ::: l12._2.tail, l12._2.head)
  }
}