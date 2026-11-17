/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog6112.bicyclerentalsq2;

/**
 *
 * @author Linothando
 */
public class Calculations implements ICalculations {
    
    private double price;
    private int duration;

    public Calculations(double price, int duration) {
        this.price = price;
        this.duration = duration;
    }
    
    @Override
    public double GetVat() {

        double vat = price * 0.15;

        return vat;
    }
    
     @Override
    public double GetTotal() {
 
        double total = (price + GetVat()) * duration;

        return total;
    }
    
}
