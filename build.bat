
scalac -classpath .:lib/scalatest-0.9.5.jar *.scala 

scala -classpath .:lib/scalatest-0.9.5.jar org.scalatest.tools.Runner -o -s ninetynine.NinetyNineProblemsTest
scala -classpath .:lib/scalatest-0.9.5.jar org.scalatest.tools.Runner -o -s bank1.ObservedBankAccountTest
scala -classpath .:lib/scalatest-0.9.5.jar org.scalatest.tools.Runner -o -s bank2.ObservedBankAccountTest
