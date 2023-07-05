// --== CS400 File Header Information ==--
// Name: Bryan Li
// Email: bli378@wisc.edu
// Team: EG
// Role: Test Engineer 1
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: 

import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.*;

/**
 * JUnit test suite that tests the Hospital of Life database.
 */
public class TestProject2 {

    /**
     * Tests whether the data is loaded in properly. Tests to see if the amount of patients stays the same.
     * @throws IOException if there is an error with the text file
     */
    @Test
    public void TestLoadData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("PatientData.txt"));
        int lineNumber = 0;
        while(reader.readLine() != null)
            lineNumber++;
        reader.close();

        PatientIO loader = new PatientIO("PatientData.txt");
        if(loader.patients.size()!=lineNumber)
            fail("Loading of file failed.");
    }

    /**
     * Tests whether the data from the RBT is written properly into a dummy text file.
     * @throws IOException if there is an error with the text file
     */
    @Test
    public void TestWriteData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("PatientData.txt"));
        int lineNumber = 0;
        while(reader.readLine() != null)
            lineNumber++;
        reader.close();

        BackEndHoL backend = new BackEndHoL();
        backend.addPatient("TestName","1/1/2000","Test");
        backend.writeFile("TestFile");

        reader = new BufferedReader(new FileReader("TestFile.txt"));
        int testLineNumber = 0;
        while(reader.readLine() != null)
            testLineNumber++;
        reader.close();

        if(lineNumber == testLineNumber)
            fail("Writing to file failed.");
    }

    /**
     * Tests whether a patient is added properly into the database.
     */
    @Test
    public void TestAddPatient(){
        BackEndHoL backend = new BackEndHoL();
        String toString = backend.addPatient("TestName","1/1/2000","Test");
        if(toString.equals(""))
            fail("Failed to add patient.");
    }

    /**
     * Tests whether a treatment is added properly into the database.
     */
    @Test
    public void TestAddTreatment(){
        BackEndHoL backend = new BackEndHoL();
        String treatment = backend.addTreatment("Test","123456");
        if(!treatment.equals("Test"))
            fail("Treatment addition failed.");
    }

    /**
     * Tests whether a note is added properly into the database.
     */
    @Test
    public void TestAddNotes(){
        BackEndHoL backend = new BackEndHoL();
        String note = backend.addNotes("Test","123456");
        if(!note.equals("Test"))
            fail("Note addition failed.");
    }

    /**
     * Tests the lookup functionality of the database.
     */
    @Test
    public void TestLookup(){
        BackEndHoL backend = new BackEndHoL();
        if(backend.lookup(123456)==null)
            fail("Lookup failed.");
    }

    /**
     * Tests the checkout functionality of the database.
     */
    @Test
    public void TestCheckout(){
        BackEndHoL backend = new BackEndHoL();
        backend.checkout(123456);
        String data = backend.lookup(123456);
        if(data.contains("Checked In"))
            fail("Patient not successfully checked out.");
    }

}
