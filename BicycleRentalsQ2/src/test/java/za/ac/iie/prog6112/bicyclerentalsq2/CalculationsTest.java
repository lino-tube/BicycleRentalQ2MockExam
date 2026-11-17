/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package za.ac.iie.prog6112.bicyclerentalsq2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Linothando
 */
public class CalculationsTest {
    
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        
        //Create a Calculations object using R180 and 3 months.
        Calculations calculations = new Calculations(180, 3);
        
        //The expected VAT is 15% of R180.
        double expectedVat = 27.00;
        
        //Calling GetVat() to calculate the actual VAT.
        double actualVat = calculations.GetVat();
        
        //assertsEquals() compares the expected and actual results.
        //0.002 allows for floating-points differences.
        assertEquals(expectedVat, actualVat, 0.002);
    }
    
    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        
        //Create a Calculations object using R180 and 3 months.
        Calculations calculations = new Calculations(180, 3);
        
        //The expected total is (180 + 27.00) * 3.
        double expectedTotal = 621.00;
        
        //Calling GetTotal() to calculate the actual total
        double actualTotal = calculations.GetTotal();
        
        //Compare the expected and actual results.
        assertEquals(expectedTotal, actualTotal, 0.002);
    }
    
}
