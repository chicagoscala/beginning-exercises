// bank-account1-test.scala: ScalaTest to exercise code.

package bank1

import org.scalatest._
import org.scalatest.matchers._
import java.util.NoSuchElementException

class BankAccount1Test extends FunSuite with ShouldMatchers {

  // I could have put the test inside "receiveUpdate", rather than
  // save the balance to "newBalance" and do the test at the end.
  // However, there's a flaw with that approach. What is it?
    
/*
  test("Deposits are observed after the balance change") {
    val account = new BankAccount(100.0) with ObservedAccount
    var newBalance = 0.0
    account.addObserver(new Observer[Account] {
      def receiveUpdate(a: Account) = newBalance = a.balance
    })
    
    account.deposit(10.1)
    newBalance should be (110.1 plusOrMinus .01)
  }

  test("Withdraws are observed after the balance change") {
    val account = new BankAccount(100.0) with ObservedAccount
    var newBalance = 0.0
    account.addObserver(new Observer[Account] {
      def receiveUpdate(a: Account) = newBalance = a.balance
    })
    
    account.withdraw(10.1)
    newBalance should be (89.9 plusOrMinus .01)
  }
*/
}