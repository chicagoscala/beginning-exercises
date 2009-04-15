// bank-account2-test.scala: ScalaTest to exercise code.
// This variant supports "before" and "after" notifications.

package bank2

import org.scalatest._
import org.scalatest.matchers._
import java.util.NoSuchElementException

class ObservedBankAccountTest extends FunSuite with ShouldMatchers {

    test("Deposits are observed after the balance change") {
        val account = new BankAccount(100.0) with ObservedAccount
        var newBalanceBefore = 0.0
        var newBalanceAfter  = 0.0
        account.addObserver(new Observer[Account] {
            def receiveUpdate(isBefore: Boolean, a: Account) = isBefore match {
                case true  => newBalanceBefore = a.balance
                case false => newBalanceAfter  = a.balance
            }
        })
        
        account.deposit(10.1)
        newBalanceBefore should be (100.0 plusOrMinus .01)
        newBalanceAfter  should be (110.1 plusOrMinus .01)
        
        account.deposit(10.1)
        newBalanceBefore should be (110.1 plusOrMinus .01)
        newBalanceAfter  should be (120.2 plusOrMinus .01)
    }

    test("Withdraws are observed after the balance change") {
        val account = new BankAccount(100.0) with ObservedAccount
        var newBalanceBefore = 0.0
        var newBalanceAfter = 0.0
        account.addObserver(new Observer[Account] {
            def receiveUpdate(isBefore: Boolean, a: Account) = isBefore match {
                case true  => newBalanceBefore = a.balance
                case false => newBalanceAfter  = a.balance
            }
        })
        
        account.withdraw(10.1)
        newBalanceBefore should be (100.0 plusOrMinus .01)
        newBalanceAfter  should be (89.9  plusOrMinus .01)
        
        account.withdraw(10.1)
        newBalanceBefore should be (89.9 plusOrMinus .01)
        newBalanceAfter  should be (79.8 plusOrMinus .01)
    }
}