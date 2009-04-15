package bank3

// Variation of exercise where "before" and "after" notifications
// are supported and we use "structural typing", rather than define
// an Observer abstraction.

trait Account {
    def balance: Double
	def deposit(amount: Double)
	def withdraw(amount: Double)
}

class BankAccount(var balance: Double) extends Account {
	def deposit(amount: Double) = balance += amount
	def withdraw(amount: Double) = balance -= amount
}

// No more "trait Observer[A] {...}"

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
	
	type observer = { def receiveUpdate(isBefore: Boolean, a: Account) }
	
	protected var observers = List[observer]()
	
	def addObserver(obs: observer) = observers ::= obs
	
	protected def notifyObservers(isBefore: Boolean) = observers foreach {
	    o => o.receiveUpdate(isBefore, this) 
	}
}
