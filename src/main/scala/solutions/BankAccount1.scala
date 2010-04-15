package solutions.bank1

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
    def receiveUpdate(x: A)
}

trait ObservedAccount extends Account {
	abstract override def deposit(amount: Double) = {
		super.deposit(amount)
		notifyObservers()
	}
	
	abstract override def withdraw(amount: Double) = {
		super.withdraw(amount)
		notifyObservers()
	}
	
	protected var observers = List[Observer[Account]]()
	
	def addObserver(observer: Observer[Account]) = observers ::= observer
	
	protected def notifyObservers() = observers foreach { o => o.receiveUpdate(this) }
}
