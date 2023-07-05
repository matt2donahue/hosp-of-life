run: compile
	java FrontEndInterface

compile: FrontEndInterface.class 


FrontEndInterface.class: FrontEndInterface.java BackEndHoL.class
	javac FrontEndInterface.java

BackEndHoL.class: BackEndHoL.java RedBlackTree.class PatientIO.class Patient.class PatientData.txt
	javac BackEndHoL.java

RedBlackTree.class: RedBlackTree.java
	javac RedBlackTree.java

PatientIO.class: PatientIO.java Patient.class
	javac PatientIO.java

Patient.class: Patient.java VisitInfo.class
	javac Patient.java

VisitInfo.class: VisitInfo.java
	javac VisitInfo.java

test: TestProject2.class junit5.jar
	java -jar junit5.jar --class-path . --scan-classpath --details tree

TestProject2.class: junit5.jar BackEndHoL.class
	javac -cp .:junit5.jar TestProject2.java


clean:
	$(RM) *.class
