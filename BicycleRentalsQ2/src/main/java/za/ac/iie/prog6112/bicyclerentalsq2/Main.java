/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog6112.bicyclerentalsq2;

import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {

        /*
         * SwingUtilities.invokeLater ensures that
         * our GUI is created on the Swing event
         * dispatch thread.
         */
        SwingUtilities.invokeLater(() -> {

            // Create an object of our GUI class.
           BicycleRentalsGUI gui = new BicycleRentalsGUI();

            // Make the JFrame visible to the user.
            gui.setVisible(true);
        });
    }
}
