
CP = .:lib/scalatest-0.9.5.jar 

all: clean compile test

clean: 
	rm -rf bank1 bank2 ninetynine
#	find . -name "*.class" -exec rm {} \;

foo: 
	find . -name "*.class" -exec ls {} \;

compile:
	scalac -classpath ${CP} *.scala 

test:
	scala -classpath ${CP} org.scalatest.tools.Runner  -o -s ninetynine.NinetyNineProblemsTest
	scala -classpath ${CP} org.scalatest.tools.Runner  -o -s bank1.ObservedBankAccountTest
	scala -classpath ${CP} org.scalatest.tools.Runner  -o -s bank2.ObservedBankAccountTest
	scala -classpath ${CP} org.scalatest.tools.Runner  -o -s bank3.ObservedBankAccountTest

zip: zipclean
	mkdir case-04162009
	cp -r *.scala makefile exercises.txt web lib case-04162009
	zip -r exercises.zip case-04162009

zipclean:
	rm -rf case-04162009 exercises.zip
