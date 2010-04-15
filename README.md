# Chicago-Area Scala Enthusiasts (CASE) Hands On: "99 Problems" (and Then Some...)

April 15, 2010, Thoughtworks<br/>
[dean@deanwampler.com](mailto:dean@deanwampler.com)

## Setup

Clone this [GitHub repo](http://github.com/chicagoscala/beginning-exercises). 

It contains this README, a `lib` directory with the the _SBT_ jar (for convenience), tests for the exercises and example solutions.

If you want to build the exercises and run the tests, use one of the included _SBT_ scripts.

    sbt test  # Bash script
    sbt.bat   # Windows script
    
At the `sbt` prompt (`>`), use these commands.

    > update    # downloads the interwebs to your computer
    > test
    
After a lot of output, it should end with the following

    [success] Successful.
    [info] 
    [info] Total time: 0 s, completed Apr 14, 2010 10:59:40 PM
    >

However, there are no tests yet to run. See the **Notes** and **Cheat Sheet** sections below for additional information about *SBT* and some Scala suggestions.


## Functional Programming.

[S-99: Ninety-Nine Scala Problems](http://aperiodic.net/phil/scala/s-99/)

Most of the exercises (which would take days to complete) exercise functional programming. Recall that we did some of these exercises at our April 2009 meeting. If you were there, you might redo some of them, as a refresher (or look at the solutions), then pick up where you left off.

I recommend you start with the following sets of exercises, then branch out from there.

* *Recommended*: 1-2, 4-6
* *Extra credit*: 7, 14, 15

Try to avoid looping, mutable variables, and other *proceduralisms*. 

* Start `sbt` and enter the `~ test-only ninetynine.NinetyNineProblemsTest` command (for continuous compilation and testing).
* Edit `src/test/scala/NinetyNineProblemsTest.scala`.
* Uncomment each test in succession.
* Implement the corresponding method that is already declared in `src/main/scala/NinetyNineProblems.scala`.
* For ideas or help, my solutions are in `src/main/scala/solutions/NinetyNineProblems.scala`

## Object-Oriented Programming

If you want a break from Functional Programming ;), I have an OOP exercise (also from last year). Consider a simple Bank Account class.

<pre class="brush: scala;">
package bank1
class BankAccount(var balance: Double) {
	def deposit(amount: Double) = balance += amount
	def withdraw(amount: Double) = balance -= amount
}
</pre>

(I'm using a number in the package name, *e.g.,* `package bank1`, to indicate the iteration of this exercise.) 

What if we want to observe balance changes? Use a _Trait_ to implement the Observer Pattern.

Define an `Account` trait (in `src/main/scala`) that declares the `balance` reader, `deposit` and `withdraw` methods. Change the class to use the trait (`with` keyword).

Define another trait that defines the observer abstraction.

<pre class="brush: scala;">
trait Observer[A] {
  def receiveUpdate(x: A)
}
</pre>

Define a _subtrait_ of `Account` that implements the Observer pattern. Something like this:

<pre class="brush: scala;">
trait ObservedAccount extends Account {
	abstract override def deposit(amount: Double) = {
		super.deposit(amount)
		// notify observers after change. I.e., call "receiveUpdate" on each.
	}
	// Similarly for "withdraw"...
	
	// Manage list of observers (don't worry about deletion...)
}
</pre>

Edit `test/scala/BankAccount1Test.scala` and uncomment the first test. Then run the test using `~ test-only bank1.BankAccount1Test`.

Continue with the other test in `BankAccount1Test`.  

*Note:* Solutions can be found in `src/main/scala/solutions/BankAccount*.scala`.

`BankAccount2Test` and `BankAccount3Test` explore variations, such as *before* and *after* observation and the use of *structural types* to implement observation.

## Implement the Next Twitter in Scala. ##

Handle 100 million accounts. Take about 25 minutes.


# Notes #

As of this writing, the build is based on Scala 2.7.7, because I ran into problems using _ScalaTest_ with Scala 2.8.0.Beta1 and Scala 2.8.0.RC1. You can try changing to one of the Scala 2.8.0 releases, if you want. Edit the `build.scala.versions` in `project/build.properties`, then in `sbt`, run these commands:

    > reload         # If you edited the properties and didn't restart sbt.
    > update         # Tell sbt to update its jars to the correct versions.

## Cheat Sheet

Here are a few useful `sbt` commands.

    > actions        # List and briefly describe available commands.
    > clean          # Clean all build products.
    > compile        # Compile the "main" sources.
    > test-compile   # Compile the test sources.
    > test           # Run all tests (runs test-compile first).
    > test-quick     # Run only the tests that have changed or previously failed.
    > test-only ninetynine.NinetyNineProblemsTest  # Run just this suite
    > ~ command      # Automatically run "command" after every file save.
  
Here are a few Scala-isms that you might find useful. Start the `scala` interpreter and paste this text at the `scala>` prompt.

<pre class="brush: scala;">
	val list = List(1,2,3,4)
	
	5 :: list
	
	5 :: list.reverse
	
	6 :: 5 :: list.reverse
	
	List(6, 5) ::: list.reverse
	
	list ++ List(5, 6)
	
	val (a, b) = (list, list.length)
		
	def tupledlist(l: List[_]) = (l, l.length)
	
	val (a2, b2) = tupledlist(list)
	
	list map { x => x * x }
	list foreach { x => println(x * x) }
	
	trait T1 { def m1(i: Int) }
	trait T2 { def m2(d: Double) }
	
	class C extends T1 with T2 { 
		def m1(i: Int) = println("Int: " + i) 
		def m2(d: Double) = println("Double: " + d) 
	}
	val c = new C
	c.m1(10)
	c.m2(22.2)
</pre>

### For More Information:

* [Scala API](http://www.scala-lang.org/archives/downloads/distrib/files/nightly/docs/library/index.html) 
* [ScalaTest](http://www.scalatest.org/scaladoc/doc-1.0/)
* [Simple Build Tool (SBT)](http://code.google.com/p/simple-build-tool/)
