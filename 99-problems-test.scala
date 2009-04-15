package ninetynine
import org.scalatest._
import org.scalatest.matchers._
import java.util.NoSuchElementException

// Actually it's 1-20 or so at this point...
// Use "make all" to build and run this test suite.

class NinetyNineProblemsTest extends FunSuite with ShouldMatchers {

    test("P01 (*) Find the last element of a list.") {
        def last(l: List[_]) = l.reverse.head

        last(List(1, 1, 2, 3, 5, 8)) should equal (8)
        last(List(1)) should equal (1)
        intercept[NoSuchElementException] {
            last(Nil) 
        }
    }
    
    test("P02 (*) Find the last but one element of a list.") {
        def penultimate(l: List[_]) = l.reverse.tail.head

        penultimate(List(1, 1, 2, 3, 5, 8)) should equal (5)
        penultimate(List(5, 8)) should equal (5)
        intercept[NoSuchElementException] {
            penultimate(Nil) 
        }
        intercept[NoSuchElementException] {
            penultimate(List(1)) 
        }
    }

    test("P03 (*) Find the Kth element of a list, where 0 is the 1st element.") {
        def nth[A](n: Int, l: List[A]): A = n match {
            case 0 => l head
            case _ => l match {
                case Nil => throw new IllegalArgumentException("n > list.length")
                case _ => nth(n-1, l.tail)
            }
        }

        intercept[NoSuchElementException] {
            nth(0, List[String]())  // Bug in Scalatest: can't use Nil as 2nd arg.
        }
        nth(0, List(1, 1, 2, 3, 5, 8)) should equal (1)
        nth(1, List(1, 1, 2, 3, 5, 8)) should equal (1)
        nth(2, List(1, 1, 2, 3, 5, 8)) should equal (2)
        nth(3, List(1, 1, 2, 3, 5, 8)) should equal (3)
        nth(4, List(1, 1, 2, 3, 5, 8)) should equal (5)
        nth(5, List(1, 1, 2, 3, 5, 8)) should equal (8)
        intercept[NoSuchElementException] {
            nth(6, List(1, 1, 2, 3, 5, 8)) 
        }
    }

    test("P04 (*) Find the number of elements of a list.") {
        def length(l: List[_]) = {
            def _len(l: List[_], len: Int): Int = l match {
                case head :: tail => _len(tail, len + 1)
                case Nil => len
            }
            _len(l, 0)
        }
        length(Nil) should equal (0)
        length(List(1)) should equal (1)
        length(List(1, 1, 2, 3, 5, 8))  should equal (6)
    }

    test("P05 (*) Reverse a list.") {
        def reverseEasy(l: List[_]) = l reverse
        def reverse(l: List[_]) = {
            def _reverse(l: List[_], accum: List[_]): List[_] = l match {
                case Nil => accum
                case h :: t => _reverse(t, h :: accum)
            }
            _reverse(l, Nil)
        }
        
        reverse(Nil) should equal (Nil)
        reverse(List(1)) should equal (List(1))
        reverse(List(1, 1, 2, 3, 5, 8)) should equal (List(8, 5, 3, 2, 1, 1))
    }
    
    test("P06 (*) Find out whether a list is a palindrome.") {
        def isPalindrome(l: List[_]) = l.reverse == l
        
        isPalindrome(Nil) should equal (true)
        isPalindrome(List(1)) should equal (true)
        isPalindrome(List(1, 1)) should equal (true)
        isPalindrome(List(1, 2, 2, 1)) should equal (true)
        isPalindrome(List(1, 2, 3, 2, 1)) should equal (true)
        isPalindrome(List(1, 3, 2, 2, 1)) should equal (false)
    }
    
    test("P07 (**) Flatten a nested list structure.") {
        def flatten(l: List[_]): List[_] = l match {
            case Nil => Nil
            case _ => (l head match {
                case l2: List[_] => flatten(l2)
                case h => List(h)
            }) ::: flatten(l tail)
        }
        
        flatten(Nil) should equal (Nil)
        flatten(List(1, 1, 2, 3, 5, 8)) should equal (List(1, 1, 2, 3, 5, 8))
        flatten(List(List(1, 1), 2, List(3, List(5, 8)))) should equal (List(1, 1, 2, 3, 5, 8))
    }

    test("P08 (**) Eliminate consecutive duplicates of list elements.") {
        // If a list contains repeated elements they should be replaced with a single copy of the element.
        // The order of the elements should not be changed.

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

        compress(Nil) should equal (Nil)
        compress(List('a)) should equal (List('a))
        compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should equal (List('a, 'b, 'c, 'a, 'd, 'e))
    }

    def pack[_](l: List[_]) = {
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

    test("P09 (**) Pack consecutive duplicates of list elements into sublists.") {
        // If a list contains repeated elements they should be placed in separate sublists.

        pack(Nil) should equal (Nil)
        pack(List('a, Nil, 'b)) should equal (List(List('a), List(Nil), List('b)))
        pack(List('a, 'b, 'c, 'a, 'd, 'e)) should equal (
            List(List('a), List('b), List('c), List('a), List('d), List('e)))
        pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should equal (
            List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)))
    }
    
    test("P10 (*) Run-length encoding of a list.") {
        // Use the result of problem P09 to implement the so-called run-length encoding
        // data compression method. Consecutive duplicates of elements are encoded as 
        // tuples (N, E) where N is the number of duplicates of the element E.

        def encode(l: List[_]) = pack(l) map { x: List[_] => (x.length, x.head) }
        
        encode(Nil) should equal (Nil)
        encode(List('a)) should equal (List((1,'a)))
        encode(List('a, 'b)) should equal (List((1,'a), (1,'b)))
        encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should equal (
            List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
    }

    test("P11 (*) Modified run-length encoding.") {
        // Modify the result of problem P10 in such a way that if an element has no duplicates
        // it is simply copied into the result list. Only elements with duplicates are transferred
        // as (N, E) terms.

        def encodeModified(l: List[_]) = pack(l) map { x: List[_] => x.length match {
                case 1 => x.head
                case n => (n, x.head) 
            }
        }

        encodeModified(Nil) should equal (Nil)
        encodeModified(List('a)) should equal (List('a))
        encodeModified(List('a, 'b)) should equal (List('a, 'b))
        encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should equal (
            List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e)))
    }

    test("P12 (**) Decode a run-length encoded list.") {
        // Given a run-length code list generated as specified in problem P10, 
        // construct its uncompressed version.

        def decode(l: List[_]) = {
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
        
        decode(Nil) should equal (Nil)
        decode(List((1,'a))) should equal (List('a))
        decode(List((2,'a))) should equal (List('a, 'a))
        decode(List((1,'a), (1,'b))) should equal (List('a, 'b))
        decode(List((2,'a), (3,'b))) should equal (List('a, 'a, 'b, 'b, 'b))
        decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))) should equal (
            List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    }

    test("P13 (**) Run-length encoding of a list (direct solution).") {
        // Implement the so-called run-length encoding data compression method directly. 
        // I.e. don't use other methods you've written (like P09's pack); do all the work directly.
        
        def encode[_](l: List[_]) = {
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

        encode(Nil) should equal (Nil)
        encode(List('a)) should equal (List((1,'a)))
        encode(List('a, 'b)) should equal (List((1,'a), (1,'b)))
        encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should equal (
            List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
    }

    test("P14 (*) Duplicate the elements of a list.") {

        def duplicate(l: List[_]) = {
            def _dup(l: List[_], accum: List[_]): List[_] = l match {
                case Nil => accum
                case h :: t => _dup(t, h :: h :: accum)
            }
            _dup(l, Nil).reverse
        }
        
        duplicate(Nil) should equal (Nil)
        duplicate(List('a)) should equal (List('a, 'a))
        duplicate(List('a, 'b, 'c, 'c, 'd)) should equal (
            List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
    }

    test("P15 (**) Duplicate the elements of a list a given number of times.") {

        def duplicateN(n: Int, l: List[_]) = {
            def _dupN(l: List[_], accum: List[_]): List[_] = l match {
                case Nil => accum
                case h :: t => _dupN(t, (for (i <- 1 to n) yield h ).toList ::: accum)
            }
            _dupN(l, Nil).reverse
        }
        
        duplicateN(10, Nil) should equal (Nil)
        duplicateN(3, List('a)) should equal (List('a, 'a, 'a))
        duplicateN(3, List('a, 'b, 'c, 'c, 'd)) should equal (
            List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd))
    }

    test("P16 (**) Drop every Nth element from a list.") {

        def drop(n: Int, l: List[_]) = {
            def _drop(l: List[_], i: Int, accum: List[_]): List[_] = l match {
                case Nil => accum
                case h :: t => i match {
                    case _ if i % n == 0 => _drop(t, i+1, accum)
                    case _ => _drop(t, i+1, h :: accum)
                }
            }
            _drop(l, 1, Nil).reverse
        }

        drop(1, Nil) should equal (Nil)
        drop(1, List(1)) should equal (Nil)
        drop(2, List(1, 2)) should equal (List(1))
        drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should equal (
            List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k))
    }

    def split(n: Int, l: List[_]) = {
        def _split(l: List[_], i: Int, accum: List[_]): (List[_], List[_]) = l match {
            case Nil => (accum.reverse, Nil)
            case h :: t => i match {
                case _ if i == n => (accum.reverse, h :: t)
                case _ => _split(t, i+1, h :: accum)
            }
        }
        _split(l, 0, Nil)
    }

    test("P17 (*) Split a list into two parts.") {
        // The length of the first part is given. Use a Tuple for your result.

        split(1, Nil) should equal ((Nil, Nil))
        split(3, List('a, 'b, 'c)) should equal ((List('a, 'b, 'c), Nil))
        split(4, List('a, 'b, 'c)) should equal ((List('a, 'b, 'c), Nil))
        split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should equal (
            (List('a, 'b, 'c), List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
    }

    test("P18 (**) Extract a slice from a list.") {
        // Given two indices, I and K, the slice is the list containing the elements
        // from and including the Ith element up to but not including the Kth element 
        // of the original list. Start counting the elements with 0.

        def slice(start: Int, end: Int, l: List[_]) = {
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
        
        slice(0, 1, Nil) should equal (Nil)
        slice(1, 0, List(1)) should equal (Nil)
        slice(0, 1, List(1)) should equal (List(1))
        slice(0, 3, List(1, 2)) should equal (List(1, 2))
        slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should equal (
            List('d, 'e, 'f, 'g))
    }

    test("P19 (**) Rotate a list N places to the left.") {

        def rotate(n: Int, l: List[_]) = {
            def _rotate(n: Int, l: List[_]) = {
                val l12 = split(n, l)
                l12._2 ::: l12._1                
            }
            n match {
                case _ if n >= 0 => _rotate(n, l)
                case _           => _rotate(-n, l.reverse).reverse
            }
        }
        
        rotate(1, Nil) should equal (Nil)
        rotate(1, List('a)) should equal (List('a))
        rotate(2, List('a)) should equal (List('a))
        rotate(1, List('a, 'b)) should equal (List('b, 'a))
        rotate(2, List('a, 'b)) should equal (List('a, 'b))
        rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should equal (
            List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c))
        rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should equal (
            List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i))
    }

    test("P20 (*) Remove the Kth element from a list.") {
        // Return the list and the removed element in a Tuple. Elements are numbered from 0.

        def removeAt(n: Int, l: List[_]) = {
            val l12 = split(n, l)
            (l12._1 ::: l12._2.tail, l12._2.head)
        }

        removeAt(0, List('a, 'b, 'c, 'd)) should equal ((List('b, 'c, 'd), 'a))
        removeAt(1, List('a, 'b, 'c, 'd)) should equal ((List('a, 'c, 'd), 'b))
        removeAt(3, List('a, 'b, 'c, 'd)) should equal ((List('a, 'b, 'c), 'd))
        intercept[NoSuchElementException] {
            removeAt(4, List('a, 'b, 'c, 'd))
        }
        intercept[NoSuchElementException] {
            removeAt(0, Nil)
        }
    }
}