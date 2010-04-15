package ninetynine
import NinetyNineProblems._
import org.scalatest._
import org.scalatest.matchers._
import java.util.NoSuchElementException

/**
 * Tests for the 99 problems. (Actually it's 1-20 or so at this point...)
 * Uncomment each test and implement the corresponding methods that are tests. 
 * They are declared, but not yet defined in src/main/scala/NinetyNineProblems.scala.
 * See src/main/scala/solutions/NinetyNineProblems.scala for possible solutions.
 */
class NinetyNineProblemsTest extends FunSuite with ShouldMatchers {
  
/*  
  test("P01 (*) Find the last element of a list.") {
    last(List(1, 1, 2, 3, 5, 8)) should equal (8)
    last(List(1)) should equal (1)
    intercept[NoSuchElementException] {
      last(List[String]()) 
    }
  }
  
  test("P02 (*) Find the last but one element of a list.") {
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
    listLength(Nil) should equal (0)
    listLength(List(1)) should equal (1)
    listLength(List(1, 1, 2, 3, 5, 8))  should equal (6)
  }

  test("P05 (*) Reverse a list.") {
    reverse(Nil) should equal (Nil)
    reverse(List(1)) should equal (List(1))
    reverse(List(1, 1, 2, 3, 5, 8)) should equal (List(8, 5, 3, 2, 1, 1))
  }
  
  test("P06 (*) Find out whether a list is a palindrome.") {
    isPalindrome(Nil) should equal (true)
    isPalindrome(List(1)) should equal (true)
    isPalindrome(List(1, 1)) should equal (true)
    isPalindrome(List(1, 2, 2, 1)) should equal (true)
    isPalindrome(List(1, 2, 3, 2, 1)) should equal (true)
    isPalindrome(List(1, 3, 2, 2, 1)) should equal (false)
  }
  
  test("P07 (**) Flatten a nested list structure.") {
    flatten(Nil) should equal (Nil)
    flatten(List(1, 1, 2, 3, 5, 8)) should equal (List(1, 1, 2, 3, 5, 8))
    flatten(List(List(1, 1), 2, List(3, List(5, 8)))) should equal (List(1, 1, 2, 3, 5, 8))
  }

  test("P08 (**) Eliminate consecutive duplicates of list elements.") {
    // If a list contains repeated elements they should be replaced with a single copy of the element.
    // The order of the elements should not be changed.

    compress(Nil) should equal (Nil)
    compress(List('a)) should equal (List('a))
    compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should equal (List('a, 'b, 'c, 'a, 'd, 'e))
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

    encodeModified(Nil) should equal (Nil)
    encodeModified(List('a)) should equal (List('a))
    encodeModified(List('a, 'b)) should equal (List('a, 'b))
    encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should equal (
      List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e)))
  }

  test("P12 (**) Decode a run-length encoded list.") {
    // Given a run-length code list generated as specified in problem P10, 
    // construct its uncompressed version.
      
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
      
    encodeDirect(Nil) should equal (Nil)
    encodeDirect(List('a)) should equal (List((1,'a)))
    encodeDirect(List('a, 'b)) should equal (List((1,'a), (1,'b)))
    encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should equal (
        List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
  }

  test("P14 (*) Duplicate the elements of a list.") {
    duplicate(Nil) should equal (Nil)
    duplicate(List('a)) should equal (List('a, 'a))
    duplicate(List('a, 'b, 'c, 'c, 'd)) should equal (
        List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
  }

  test("P15 (**) Duplicate the elements of a list a given number of times.") {      
    duplicateN(10, Nil) should equal (Nil)
    duplicateN(3, List('a)) should equal (List('a, 'a, 'a))
    duplicateN(3, List('a, 'b, 'c, 'c, 'd)) should equal (
        List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd))
  }

  test("P16 (**) Drop every Nth element from a list.") {
    drop(1, Nil) should equal (Nil)
    drop(1, List(1)) should equal (Nil)
    drop(2, List(1, 2)) should equal (List(1))
    drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should equal (
        List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k))
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

    slice(0, 1, Nil) should equal (Nil)
    slice(1, 0, List(1)) should equal (Nil)
    slice(0, 1, List(1)) should equal (List(1))
    slice(0, 3, List(1, 2)) should equal (List(1, 2))
    slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should equal (
        List('d, 'e, 'f, 'g))
  }

  test("P19 (**) Rotate a list N places to the left.") {
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
*/
}