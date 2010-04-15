package ninetynine

object NinetyNineProblems {

  class NotImplemented extends RuntimeException
  
  /** P01 (*) Find the last element of a list. */
  def last[A](l: List[A]): A = throw new NotImplemented
  
  /** P02 (*) Find the last but one element of a list. */
  def penultimate[A](l: List[A]): A = throw new NotImplemented
  
  /** P03 (*) Find the Kth element of a list, where 0 is the 1st element. */
  def nth[A](n: Int, l: List[A]): A = throw new NotImplemented
  
  /** P04 (*) Find the number of elements of a list. */
  def listLength(l: List[_]): Int = throw new NotImplemented
  
  /** P05 (*) Reverse a list. */
  def reverse[A](l: List[A]): List[A] = throw new NotImplemented

  /** P06 (*) Find out whether a list is a palindrome. */
  def isPalindrome(l: List[_]): Boolean = throw new NotImplemented

  /** P07 (**) Flatten a nested list structure. */
  def flatten(l: List[_]): List[_] = throw new NotImplemented

  /** P08 (**) Eliminate consecutive duplicates of list elements.
   * If a list contains repeated elements they should be replaced with a single copy of the element.
   * The order of the elements should not be changed.
   */
  def compress(l: List[_]): List[_] = throw new NotImplemented
  
  /** P09 (**) Pack consecutive duplicates of list elements into sublists."
   * If a list contains repeated elements they should be placed in separate sublists.
   */
  def pack[_](l: List[_]): List[List[_]] = throw new NotImplemented

  /** P10 (*) Run-length encoding of a list.
   * Use the result of problem P09 to implement the so-called run-length encoding
   * data compression method. Consecutive duplicates of elements are encoded as 
   * tuples (N, E) where N is the number of duplicates of the element E.
   */
  def encode(l: List[_]): List[Pair[Int,_]] = throw new NotImplemented
  
  /** P11 (*) Modified run-length encoding.
   * Modify the result of problem P10 in such a way that if an element has no duplicates
   * it is simply copied into the result list. Only elements with duplicates are transferred
   * as (N, E) terms.
   */
  def encodeModified(l: List[_]): List[_] = throw new NotImplemented
  
  /** P12 (**) Decode a run-length encoded list. 
   * Given a run-length code list generated as specified in problem P10, 
   * construct its uncompressed version.
   */
  def decode(l: List[Pair[Int,_]]): List[_] = throw new NotImplemented
  
  /** P13 (**) Run-length encoding of a list (direct solution).
   * Implement the so-called run-length encoding data compression method directly. 
   * I.e. don't use other methods you've written (like P09's pack); do all the work directly.
   */
  def encodeDirect[_](l: List[_]): List[Pair[Int,_]] = throw new NotImplemented

  /** P14 (*) Duplicate the elements of a list. */
  def duplicate(l: List[_]): List[_] = throw new NotImplemented

  /** P15 (**) Duplicate the elements of a list a given number of times. */
  def duplicateN(n: Int, l: List[_]): List[_] = throw new NotImplemented
  
  /** P16 (**) Drop every Nth element from a list. */
  def drop(n: Int, l: List[_]): List[_] = throw new NotImplemented

  /** P17 (*) Split a list into two parts. 
   * The length of the first part is given. Use a Pair for your result.
   */
  def split(n: Int, l: List[_]): Pair[List[_], List[_]] = throw new NotImplemented

  /** P18 (**) Extract a slice from a list.
   * Given two indices, I and K, the slice is the list containing the elements
   * from and including the Ith element up to but not including the Kth element 
   * of the original list. Start counting the elements with 0.
   */
  def slice(start: Int, end: Int, l: List[_]): List[_] = throw new NotImplemented

  /** P19 (**) Rotate a list N places to the left. */
  def rotate(n: Int, l: List[_]): List[_] = throw new NotImplemented

  /** P20 (*) Remove the Kth element from a list. 
   * Return the list and the removed element in a Tuple. Elements are numbered from 0.
   */
  def removeAt(n: Int, l: List[_]): Pair[List[_],_] = throw new NotImplemented
}