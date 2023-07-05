// --== CS400 File Header Information ==--
// Name: Matthew Donahue
// Email: mtdonahue@wisc.edu
// Team: EG
// Role: Back End Developer
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BackEndHoL {

  private RedBlackTree<Patient> patientsTree;
  private ArrayList<Patient> patientData;
  private int roomNum;
  private String fileName;

  /**
   * Constructor
   */
  public BackEndHoL() {
    patientsTree = new RedBlackTree<Patient>();
    roomNum = 1;
    
    fileName = "PatientData.txt";

    PatientIO loader = new PatientIO(fileName);
    patientData = loader.getList();

    for (int i = 0; i < patientData.size(); i++) {
      patientsTree.insert(patientData.get(i));
    }
  }
  
  /**
   * This method adds a patient to the red black tree. First it randomly
   * generates a 6 digit number, and if the number is already taken by a
   * patient it will call itself and randomly generate another number.
   * It then creates the new patient object and inserts it into the tree
   * @param name
   * @param dateOfBirth
   * @param reason
   * @return the toString() output of the new patient
   */
  public String addPatient(String name, String dateOfBirth, String reason) {
    int newId = (int) (Math.random() * ((1000000 - 100000 + 1) + 100000));
    if (lookupHelper(patientsTree.root, newId) == null) {
      Patient newPatient = new Patient(newId, name, dateOfBirth);
      newPatient.addVisit(roomNum, reason);
      roomNum++;
      patientData.add(newPatient);
      patientsTree.insert(newPatient);
      return newPatient.toString();
    } else {
      addPatient(name, dateOfBirth, reason);
    }
    return "";
  }
  
  /**
   * This method modifies a patient's visit info and adds treatment info to their
   * information file.
   * @param treatment
   * @param patientId
   * @return the treatment inserted
   */
  public String addTreatment(String treatment, String patientId) {
    Patient target = lookupHelper(patientsTree.root, Integer.parseInt(patientId));
    if (target == null) {
      throw new NoSuchElementException();
    } else {
      target.getPreviousVisit().setTreatment(treatment);
      return treatment;
    }
  }
  
  /**
   * This method modifies a patient's visit info and adds notes about the
   *  patient to their information file.
   * @param notes
   * @param patientId
   * @return the notes inserted
   */
  public String addNotes(String notes, String patientId) {
    Patient target = lookupHelper(patientsTree.root, Integer.parseInt(patientId));
    if (target == null) {
      throw new NoSuchElementException();
    } else {
      target.getPreviousVisit().setNotes(notes);
      return notes;
    }
  }
  
  /**
   * This method calls the lookup helper to find a patient in the red black tree
   * from a given id number.
   * @param patientId
   * @return the toString of the patient object found
   */
  public String lookup(int patientId) {
    Patient target = lookupHelper(patientsTree.root, patientId);
    if (target == null) {
      return "Patient Not Found";
    } else {
      return target.toString();
    }
  }
  
  /**
   * This method traverses the red black tree recursively inorder to find a
   * matching ID number.
   * @param patientNode
   * @param patientId
   * @return null if the number is not found, else it returns the Patient object
   */
  private Patient lookupHelper(RedBlackTree.Node<Patient> patientNode, int patientId) {
    if (patientNode == null) {
      return null;
    }
    if (patientNode.data.getId() == patientId) {
      return patientNode.data;
    }

    Patient recurseLeft = lookupHelper(patientNode.leftChild, patientId);
    Patient recurseRight = lookupHelper(patientNode.rightChild, patientId);
    if (recurseLeft != null) {
      return recurseLeft;
    }
    if (recurseRight != null) {
      return recurseRight;
    }
    return null;
  }
  
  /**
   * This method changes the status of the patient so that they are no longer
   * checked into the hospital any more.
   * @param patientId
   * @return the file of the Patient or "Patient not found" if the object doesn't
   * exist.
   */
  public String checkout(int patientId) {
    Patient target = lookupHelper(patientsTree.root, patientId);
    if (target == null) {
      return "Patient Not Found";
    } else {
      target.setStatus(false);
      return target.toString();
    }
  }

  /**
   * This method creates a new txt file of the database with the changes made
   * using the application.
   * @param newName
   */
  public void writeFile(String newName) {
    newName += ".txt";
    PatientIO overwriteFile = new PatientIO(newName, patientData);
    try {
    overwriteFile.writePatients(newName);

    } catch (IOException e) {
      System.out.println("Error. File could not be saved.");
    }
  }
}
