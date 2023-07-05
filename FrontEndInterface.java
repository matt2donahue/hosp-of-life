// --== CS400 File Header Information ==--
// Name: Zi Hern Wong
// Email: zwong@wisc.edu
// Team: EG
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author wongz
 *This method directly interacts with BackEndHoL.java
 *It also has a menu which allows the following operations to be performed:
 *Add Patient, Add Treatment, Lookup Patient, Checkout Patient, Add Notes, Q/Overwrite
 *The methods in this class consist of lookupInput(), AddPatient(), AddTreatment(), AddNotes(), writeFile(), Checkout()
 */
public class FrontEndInterface {
  /**
   * Scanner asks for string from the user until a valid patient name is entered.
   * @param input
   * @param back
   * @return String patient to string.
   */
  public static String lookupInput(Scanner input, BackEndHoL back) {
	boolean validInput = false;//for the do-while loop
    String toString = "";
	String result = "";
	int parser=0;

    do {
      System.out.println("Please enter the patient's patientID to lookup.");
      toString = input.nextLine();//toString takes string typed into the scanner
      try {
        parser = Integer.parseInt(toString);
       
        result = back.lookup(parser);
      } catch (NumberFormatException e) {//if not able to be parsed into an Integer
        System.out.println("Invalid ID format.");//prints out error message, and prompts the user to type again
      }
      if (result.equals("") || result.contains("Patient Not Found")) {//result will return Patient Not Found if not successfully looked up
        System.out.println("Patient Not Found, please enter a valid patientID");//prints out error message, and prompts the user to type again
      }
      else {
    	  validInput = true;//allows it to exit do while loop
      }
	}
	
    while(validInput == false);
    parser = Integer.parseInt(toString);//takes the ID inputed and calls lookup method to print out toString of patient
    String lookup = back.lookup(parser);
  	  System.out.println("\nPatient Found: \n" + lookup);//prints out toString of patient that was found
	return lookup;
  }

/**
 * This method prompts the user to enter patient name, patient date of birth, and then reason for hospital stay
 * This method ensures that the inputed name, date of birth, and reason are all formatted correctly
 * It then adds the patient object into our Red Black Tree
 * @param input
 * @param back
 */
public static void AddPatient(Scanner input, BackEndHoL back) {
	String name = "";
	String DOB="";
	String reason ="";

  do {
		System.out.println("Please enter patient name ");//prompts user to type a name into scanner
		name = input.nextLine();
		
		if(name==null || name.equals("\n")) {//prints out error message if inputed name is null or blank, and prompts the user to type again
			System.out.println("Invalid format.");
		}
  } while(name == null || name.equals("\n"));
//if breaks out of the while, that means the input for patient name is valid
  do {
		System.out.println("Please enter patient's date of birth in the form of MM/DD/YYYY e.g: 01/01/2001");//prompts user to type a birthday into scanner
		DOB = input.nextLine();
		
		if(DOB==null || DOB.equals("\n") || DOB.contains("/")==false) {//prints out error message if format is wrong, prints error message and prompts the user to type again
			System.out.println("Invalid Birthday Format.");
		}
		//if it breaks out of the while loop, that means the input for patient name and patient birthday are both valid
  } while(DOB==null || DOB.equals("\n") || DOB.contains("/")==false);		
  
  do {
		System.out.println("Please enter a reason for going to the Hospital of Life");//prompts user to type reason for going to hospital of life into scanner
		reason = input.nextLine();
		
		if(reason==null || reason.equals("\n")) {//if format is wrong, prints error message and prompts the user to type again
			System.out.println("Invalid Format.");
		}
  } while(reason == null || reason.equals("\n"));	
  
  String patient = back.addPatient(name, DOB, reason);//adds patient and returns the patient tostring
  
  if (patient.equals(""))
    System.out.println("Patient was not added successfully");
  else
	  System.out.println("\nPatient was sucessfully added\n" + patient);//prints out added patient toString
}
  /**
   * This method prompts the user to enter treatment following with the patient ID
   * It then goes to your Red Black Tree to find the patient via PatientID and then 
   * Adds the treatment specified by the user
 * @param input
 * @param back
 */
public static void AddTreatment(Scanner input, BackEndHoL back) {
	String treatment = "";
	boolean validInput = false;//for do while loop
    String toString = "";
	String result = "";
	int parser=0;
  do {
		System.out.println("Please enter treatment for patient ");//prompts user to type in treatment into the scanner
		treatment = input.nextLine();
		if(treatment==null || treatment.equals("\n")) {// prints error message and prompts the user to type again if format is wrong
			System.out.println("Please enter a valid treatment");
		}
  } while(treatment== null || treatment.equals("\n"));
//that means it has exited the while loop and is a valid treatment
    do {
      System.out.println("Please enter the patient's patientID to lookup.");//prompts user to type in patientID into scanner
      toString = input.nextLine();
      try {
        parser = Integer.parseInt(toString);//sees if inputed id can be parsed into an Integer, if not prompts the user to enter valid patient ID
        result = back.lookup(parser);
      } catch (NumberFormatException e) {
        System.out.println("Please enter a patient's patientID to lookup");
      }
      if (result.equals("") || result.contains("Patient Not Found")) {
        System.out.println("Patient Not Found, please enter a valid patientID");// prints error message and prompts the user to type again if format is wrong
      }
      else {
    	  validInput = true;//allows it to exit the loop
      }
	}
	
    while(validInput == false);
    parser = Integer.parseInt(toString);
    back.addTreatment(treatment, toString);//adds treatment to the specified patientID
    String lookup = back.lookup(parser);//looks up new patient and prints it out including new treatment
  	System.out.println("\n" + lookup);
  }	
  /**
   * This method prompts the user to enter notes following with the patient ID
   * It then goes to your Red Black Tree to find the patient via PatientID and then 
   * Adds the notes specified by the user
 * @param input
 * @param back
 */
public static void AddNotes(Scanner input, BackEndHoL back) {
	String notes = "";
	boolean validInput = false;//for doWhile loop
    String toString = "";
	String result = "";
	int parser=0;
  do {
		System.out.println("Please enter notes for patient ");//prompts user to type in notes into the scanner
		notes = input.nextLine();
		
		if(notes==null || notes.equals("\n")) {//if notes are null or empty, print out error message and prompt them to enter again
			System.out.println("Please enter a valid notes");
		}
  } while(notes== null || notes.equals("\n"));
//breaks out of the while loop menaing the notes input is valid
    do {
      System.out.println("Please enter the patient's patientID to lookup.");//prompts the user to type in patientID into scanner
      toString = input.nextLine();
      try {
        parser = Integer.parseInt(toString);//tries to parse user input into an integer if doesn't work, reprompts the user to enter valid ID
        result = back.lookup(parser);
      } catch (NumberFormatException e) {
        System.out.println("Please enter a patient's patientID to lookup");
      }
      if (result.equals("") || result.contains("Patient Not Found")) {
        System.out.println("Patient Not Found, please enter a valid patientID");//if not a valid id, prints error message and prompts the user to enter again
      }
      else {
    	  validInput = true;//allow to exit loop
      }
	}
	
    while(validInput == false);
    parser = Integer.parseInt(toString);
    back.addNotes(notes, toString);//adds notes to the user inputed id
    String lookup = back.lookup(parser);//prints out the whole patient toString with new notes
  	System.out.println("\n" + lookup);
	}
  
  /**
   * Prompts the user to enter a patientID and then finds that patientID 
   * to remove it in our red black tree
 * @param input
 * @param back
 */
public static void checkout(Scanner input, BackEndHoL back) {
	boolean validInput = false;//for do while loop
    String toString = "";
	String result = "";
	int parser=0;
    do {
      System.out.println("Please enter the patient's patientID to checkout.");//prompts user to enter patient id to checkout
      toString = input.nextLine();
      try {
        parser = Integer.parseInt(toString);//tries to parse user input into an integer if doesn't work, reprompts the user to enter valid ID
        result = back.lookup(parser);
      } catch (NumberFormatException e) {
        System.out.println("Please enter a patient's patientID to lookup");
      }
      if (result.equals("") || result.contains("Patient Not Found")) {
        System.out.println("Patient Not Found, please enter a valid patientID");//if not a valid id, prints error message and prompts the user to enter again
      }
      else {
    	  System.out.println(result + "\nready to checkout\n" );
    	  validInput = true;
      }
    }//exits while loop means it is a valid ID
    while(validInput == false);
    parser = Integer.parseInt(toString);
    System.out.println(back.checkout(parser));
}

/**
 * if called, this method quits the main method while saving current tree into a new text file that is specified by the user
 * @param input
 * @param back
 * @throws FileNotFoundException
 */
public static void writeFile(Scanner input, BackEndHoL back) throws FileNotFoundException {
	System.out.println("Plese enter a new file name to save the editied patient records.");
	boolean success = false;	//for do while loop
	do {
		String fileName = "";
		if (input.hasNextLine()) fileName = input.nextLine();
		try {
			back.writeFile(fileName);
			success = true;
		} catch(Exception e) {
			System.out.println("Please enter a valid file name");
		}
	} while(!success);
}

	public static void main(String args[]) {
	    BackEndHoL back = new BackEndHoL();//initializes a back end object which also loads the text file into our Red Black Tree in the constructor
	    Scanner input = new Scanner(System.in);

	    String helpMenu =
	        "Here is a list of available commands! \n" + "Type \"A\" to add a patient to our database! \n"
	            + "Type \"L\" to get information of a patient from our database! \n"
	            + "Type \"C\" to checkout a patient! \n"
	            + "Type \"AT\" to add a treatment to an existing patient! \n"
	            + "Type \"AN\" to add additional notes for a patient! \n"
	            + "Type \"Q\" to quit and saves new patients to a new data file!";
	    String command;

		System.out.print("Welcome to the Hospital of Life app! ");
		
	    do {
			System.out.println("Please enter a command (type \"h\" for a list of commands): ");
			command = input.nextLine().toUpperCase();
			if (command.equals("H")) {
				System.out.println(helpMenu);
			} else if (command.equals("L")) {
				
				lookupInput(input, back);
			}
			else if (command.equals("A")) {
				AddPatient(input, back);
						}
			else if (command.equals("C")) {
				checkout(input, back);
			}

	      else if (command.equals("AT")) {
	    	  AddTreatment(input, back);
	      }

	       else if (command.equals("AN")) {
	    	   AddNotes(input, back);
	        }
	      
		} while (!command.equals("Q"));
		
		try {
			writeFile(input, back);
		} catch (FileNotFoundException e) {
			System.out.println("Error when saving file");
		}
	    System.out.println("Thank you for using the Hospital Tree of Life.");
	  }
}
