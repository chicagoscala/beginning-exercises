package solutions.bank2

// Variation of exercise where "before" and "after" notifications
// are supported, using a true/false flag to indicate which one.

trait Account {
    def balance: Double
	def deposit(amount: Double)
	def withdraw(amount: Double)
}

class BankAccount(var balance: Double) extends Account {
	def deposit(amount: Double) = balance += amount
	def withdraw(amount: Double) = balance -= amount
}

trait Observer[A] {
    def receiveUpdate(isBefore: Boolean, x: A)
}

trait ObservedAccount extends Account {
	abstract override def deposit(amount: Double) = {
		notifyObservers(true)
		super.deposit(amount)
		notifyObservers(false)
	}
	
	abstract override def withdraw(amount: Double) = {
		notifyObservers(true)
		super.withdraw(amount)
		notifyObservers(false)
	}
	
	protected var observers = List[Observer[Account]]()
	
	def addObserver(observer: Observer[Account]) = observers ::= observer
	
	protected def notifyObservers(isBefore: Boolean) = observers foreach {
	    o => o.receiveUpdate(isBefore, this) 
	}
}
