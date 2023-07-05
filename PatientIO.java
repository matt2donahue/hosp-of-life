// --== CS400 File Header Information ==--
// Name: <Mack Hooyman and Marcus Califf>
// Email: <mhooyman@wisc.edu, mcaliff@wisc.edu>
// Team: <EG>
// Role: <Data Wranglers>
// TA: <Keren Chen>
// Lecturer: <Florian Heimerl>


import java.io.*;
import java.util.ArrayList;

/**
 * A class that reads a txt file of patients and their information and
 * puts it into an ArrayList.
 * @author mhooyman & mcaliff
 *
 */
public class PatientIO {
    // The ArrayList of patients from the given file.
    public ArrayList<Patient> patients;
    // The name of the file we are reading the patients from.
    public String fileName;
    
    /**
     * A constructor which creates the ArrayList of patients and attempts
     * to load it with the given file.
     * @param fileName -  The name of the file being read.
     */
    public PatientIO(String fileName) {
        this.fileName = fileName;
        patients = new ArrayList<Patient>();
        
        try {
            this.loadPatients(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * A constructor which creates the ArrayList of patients and attempts
     * to load it with the given file.
     * @param fileName - The name of the file being read.
     * @param patients - The ArrayList of patients at the hospital.
     */
    public PatientIO(String fileName, ArrayList<Patient> patients) {
        this.fileName = fileName;
        this.patients = patients;
    }
    
    /**
     * Reads the file of patients to a given list of patients.
     * @param fileName -  The name of the file being read.
     * @throws IOException - If the file does not match the format of other patient information.
     */
    public void loadPatients(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String str;
        
        while((str = br.readLine()) != null) {
            String[] arr = str.split(",");
            
            for(int i=0; i<arr.length; i++) {
                arr[i] = arr[i].trim();
            }
            
            if(arr.length <= 3) {
                VisitInfo visitInfo = new VisitInfo();
                Patient patient = new Patient(Integer.parseInt(arr[0]), arr[1], arr[2], visitInfo);
                patients.add(patient);
            }
            
            else if(arr.length == 5) {
                VisitInfo visitInfo = new VisitInfo(Integer.parseInt(arr[3]), arr[4]);
                Patient patient = new Patient(Integer.parseInt(arr[0]), arr[1], arr[2], visitInfo);
                patients.add(patient);
            }
            
            else {
                VisitInfo visitInfo = new VisitInfo(Integer.parseInt(arr[3]), arr[4], arr[5], arr[6]);
                Patient patient = new Patient(Integer.parseInt(arr[0]), arr[1], arr[2], visitInfo);
                patients.add(patient);
            }
        }
        br.close();
    }
    
    /**
     * Creates a writer which writes out all of a patient's information in the correct order.
     * @param fileName - The name of the given file.
     * @throws IOException - If the file does not match the format of other patient information.
     */
    public void writePatients(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        for(int i=0; i<this.patients.size(); i++) {
            printWriter.print(this.patients.get(i) + "\n");
        }
        printWriter.close();
    }
    
    /**
     * Gets and returns the list of patients at the hospital.
     * @return getList - A list of patients at the hospital.
     */
    public ArrayList<Patient> getList(){
        return this.patients;
    }
    
}
