// --== CS400 File Header Information ==--
// Name: <Mack Hooyman and Marcus Califf>
// Email: <mhooyman@wisc.edu, mcaliff@wisc.edu>
// Team: <EG>
// Role: <Data Wranglers>
// TA: <Keren Chen>
// Lecturer: <Florian Heimerl>

import java.util.ArrayList;

/**
 * This class creates a patient object that can be placed inside the "Hospital 
 * Tree of Life" Red Black Tree.
 * @author mhooyman and mcaliff
 *
 */
public class Patient  implements Comparable<Patient>{
    
    // The ID of the patient at the Hospital of Life.
    private int patientID;
    // The name of the patient.
    private String name;
    // The date of birth of the patient.
    private String dateOfBirth;
    // An arraylist filled with information from any number of visits.
    private ArrayList<VisitInfo> chartData;
    // A marker for if a patient is currently in the hospital or not.
    private boolean status;
    
    /**
     * A constructor that creates a patient object based on parameters.
     * @param patientID - The ID representing the given patient.
     * @param name - The name of the given patient.
     * @param dateOfBirth - The date of birth of the given patient.
     */
    public Patient(int patientID, String name, String dateOfBirth) {
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        chartData = new ArrayList<VisitInfo>();
        status = true;
    }
    
    /**
     * A constructor that creates a patient object based on parameters.
     * @param patientID - The ID representing the given patient.
     * @param name - The name of the given patient.
     * @param dateOfBirth - The name of the given patient.
     * @param visitInfo - A set of info from past visits of a given patient.
     */
    public Patient(int patientID, String name, String dateOfBirth, VisitInfo visitInfo) {
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        chartData = new ArrayList<VisitInfo>();
        chartData.add(visitInfo);
        status = true;
    }

    /**
     * Gets and returns a given patient's ID.
     * @return - patient's ID
     */
    public int getId() {
        return patientID;
    }
    
    /**
     * Sets whether a patient is in the hospital currently or not.
     * @param status - whether a patient is in the hospital or not.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    /**
     * Adds a visit to the patients current chart data.
     * @param roomNum- The patient's room number.
     * @param reason- The reason the patient is visiting.
     * @return Whether the visit was added successfully.
     */
    public boolean addVisit(int roomNum, String reason) {
        VisitInfo visitInfo = new VisitInfo(roomNum, reason);
        chartData.add(visitInfo);
        
        return true;
    }
    
    /**
     * Adds a visit to the patients current chart data.
     * @param roomNum- The patient's room number.
     * @param reason- The reason the patient is visiting.
     * @param notes- Notes the doctor gave about the patient.
     * @param prescription- Prescription given by doctor.
     * @return Whether the visit was added successfully.
     */
    public boolean addVisit(int roomNum, String reason, String notes, String prescription) {
        VisitInfo visitInfo = new VisitInfo(roomNum, reason, notes, prescription);
        chartData.add(visitInfo);
        
        return true;
    }
    
    /**
     * Gets and returns the last visit that a patient had at the hospital.
     * @return - The info from the patient's last visit.
     */
    public VisitInfo getPreviousVisit() {
        if(chartData.isEmpty())return null;
        
        return chartData.get(chartData.size()-1);
    }
    
    /**
     * Put's the patient's info into a string and returns it.
     * @returns - A string representation of the patient
     */
    public String toString() {
        String info = "Name: " + this.name + "\n";
        info += "DOB: " + this.dateOfBirth + "\n";
        info += "ID: " + this.patientID + "\n";
        info += "Occupancy: " + (status ? "Checked In" : "Checked Out") + "\n";
        
        for(int i=0; i<this.chartData.size(); i++) {
            info += this.chartData.get(i);
        }

        return info;
    }
    
    /**
     * Compares two patients based on their IDs.
     * @returns - 1 if patientID is larger, -1 if patientID is smaller, O if they
     * are equal.
     */
    public int compareTo(Patient p) {
        if(patientID > p.patientID) {
            return 1;
        }
        if(patientID < p.patientID) {
            return -1;
        }
        return 0;
    }


}
