// bank-account3-test.scala: ScalaTest to exercise code.
// This variation supports "before" and "after" notifications
// and uses "structural typing", rather than defining an
// Observer abstraction.

package bank3

import org.scalatest._
import org.scalatest.matchers._
import java.util.NoSuchElementException

class BalanceWatcher {  // no "with/extends" Observer[_]
    var newBalanceBefore: Double = 0.0
    var newBalanceAfter:  Double = 0.0
    
    def receiveUpdate(isBefore: Boolean, a: Account) = isBefore match {
        case true  => newBalanceBefore = a.balance
        case false => newBalanceAfter  = a.balance
    }
}

class ObservedBankAccountTest extends FunSuite with ShouldMatchers {

    test("Deposits are observed after the balance change") {
        val account = new BankAccount(100.0) with ObservedAccount
        val watcher = new BalanceWatcher
        account.addObserver(watcher)

        account.deposit(10.1)
        watcher.newBalanceBefore should be (100.0 plusOrMinus .01)
        watcher.newBalanceAfter  should be (110.1 plusOrMinus .01)

        account.deposit(10.1)
        watcher.newBalanceBefore should be (110.1 plusOrMinus .01)
        watcher.newBalanceAfter  should be (120.2 plusOrMinus .01)
    }

    test("Withdraws are observed after the balance change") {
        val account = new BankAccount(100.0) with ObservedAccount
        val watcher = new BalanceWatcher
        account.addObserver(watcher)

        account.withdraw(10.1)
        watcher.newBalanceBefore should be (100.0 plusOrMinus .01)
        watcher.newBalanceAfter  should be (89.9  plusOrMinus .01)

        account.withdraw(10.1)
        watcher.newBalanceBefore should be (89.9 plusOrMinus .01)
        watcher.newBalanceAfter  should be (79.8 plusOrMinus .01)
    }
}