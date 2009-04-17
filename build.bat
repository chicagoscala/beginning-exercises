
scalac -classpath .;lib\scalatest-0.9.5.jar 99-problems-test.scala 
scalac -classpath .;lib\scalatest-0.9.5.jar bank-account1.scala 
scalac -classpath .;lib\scalatest-0.9.5.jar bank-account2.scala 
scalac -classpath .;lib\scalatest-0.9.5.jar bank-account3.scala 
scalac -classpath .;lib\scalatest-0.9.5.jar bank-account1-test.scala 
scalac -classpath .;lib\scalatest-0.9.5.jar bank-account2-test.scala 
scalac -classpath .;lib\scalatest-0.9.5.jar bank-account3-test.scala 

scala -classpath .;lib\scalatest-0.9.5.jar org.scalatest.tools.Runner -o -s ninetynine.NinetyNineProblemsTest
scala -classpath .;lib\scalatest-0.9.5.jar org.scalatest.tools.Runner -o -s bank1.ObservedBankAccountTest
scala -classpath .;lib\scalatest-0.9.5.jar org.scalatest.tools.Runner -o -s bank2.ObservedBankAccountTest
