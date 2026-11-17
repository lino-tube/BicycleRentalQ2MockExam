/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog6112.bicyclerentalsq2;

import javax.swing.*; // Imports GUI components such as JFrame, JButton, JLabel, JPanel, JOptionPane, JComboBox, JTextField and menus.
import java.awt.GridLayout; // Imports GridLayout to arrange components in rows and columns.
import java.io.FileWriter; // Imports FileWriter to save information to a text file.
import java.io.IOException; // Imports IOException to handle file-saving errors.


public class BicycleRentalsGUI extends JFrame {
    
     private JTextField txtCustomerName;
    private JTextField txtDailyPrice;
    
    private JComboBox<String> cmbRentalDays;
    
    private String latestReport;
    
    private String lastProcessedName;
    private String lastProcessedPriceText;
    private String lastProcessedRentalDays;
    
    public BicycleRentalsGUI(){
        
        // ========== WINDOW SETTINGS ==========

        // setTitle() displays a heading at the top of the window.
        setTitle("Bicycle Hire");

        // setSize() sets the width and height in pixels.
        // The first number is the width, the second is the height.
        setSize(430, 270);

        // This closes the entire program when the X button is clicked.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // null tells Java to centre the window on the screen.
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );
        
        JLabel lblCustomerName = new JLabel("Customer Name:");
        
        txtCustomerName = new JTextField();
         
        JLabel lblRentalDays = new JLabel("Daily Price:");
        
        String[] rentalDays = {"3", "6", "12"};
        
        cmbRentalDays = new JComboBox<>(rentalDays);
        
        JLabel lblDailyPrice = new JLabel("Daily Price:");
        
        txtDailyPrice = new JTextField();
        
        JButton btnProcess = new JButton("PROCESS");
        JButton btnSave = new JButton("SAVE");
        
        // ROW 1: Customer name label and input field.        
        panel.add(lblCustomerName);
        panel.add(txtCustomerName);

        // ROW 2: Storage duration label and dropdown list.
        panel.add(lblRentalDays);
        panel.add(cmbRentalDays);

        // ROW 3: Storage price label and input field.
        panel.add(lblDailyPrice);
        panel.add(txtDailyPrice);

        // ROW 4: Process button and Save button.
        panel.add(btnProcess);
        panel.add(btnSave);

        add(panel);
        
         // JMenuBar creates the menu bar at the top of the window.
        JMenuBar menuBar = new JMenuBar();

        // JMenu creates a heading used to group commands.
        JMenu menuFile = new JMenu("File");
        JMenu menuTools = new JMenu("Tools");

        // JMenuItem creates an option that users can click.
        JMenuItem mnuExit = new JMenuItem("Exit");
        JMenuItem mnuProcess = new JMenuItem("Process");
        JMenuItem mnuSave = new JMenuItem("Save");

        // Adds the Exit option under the File menu.
        menuFile.add(mnuExit);
        
        // Adds the Process and Save button under the Tools menu.
        menuTools.add(mnuProcess);
        menuTools.add(mnuSave);

        // Adds the menus to the menu bar.
        menuBar.add(menuFile);
        menuBar.add(menuTools);

        // Attachs the completed menu bar to the window.
        setJMenuBar(menuBar);
        
        btnProcess.addActionListener(e -> processCustomer());
        
        btnSave.addActionListener(e -> saveCustomer());
        
        mnuExit.addActionListener(e -> System.exit(0));
        
        mnuProcess.addActionListener(e -> processCustomer());
        
        mnuSave.addActionListener(e -> saveCustomer());

    }
    
    private void processCustomer() {
        
        String customerName = txtCustomerName.getText().trim();
        
        String priceText = txtDailyPrice.getText().trim();
        
        if (customerName.isEmpty() || priceText.isEmpty()) {

            // JOptionPane displays a message in a pop-up box.
            // this refers to the current FurnitureStorageForm window.
            JOptionPane.showMessageDialog(
                    this, "Please enter a customer name and price."
            );

            // return stops the method immediately.
            // We do not want calculations to run with missing data.
            return;
        }
        
        try {
            double dailyPrice = Double.parseDouble(priceText);
            
            if (dailyPrice <= 0 || !Double.isFinite(dailyPrice)) {

                JOptionPane.showMessageDialog(
                        this, "Please enter a valid price greater than zero."
                );

                // Stop processing if the price is invalid.
                return;
            }
            
            String selectedDay =
                    cmbRentalDays.getSelectedItem().toString();
            
            int rentalDays = Integer.parseInt(selectedDay);
            
            Calculations calculations = new Calculations(dailyPrice, rentalDays);
            
            double vat = calculations.GetVat();
             
            double total = calculations.GetTotal();
             
            latestReport = "Customer Name: " + customerName
                    + "\nRental Days: " + rentalDays
                    + "\nVAT: R " + vat
                    + "\nTOTAL: R " + total;
            
            lastProcessedName = customerName;
            lastProcessedPriceText = priceText;
            lastProcessedRentalDays = selectedDay;
            
            JOptionPane.showMessageDialog(this, latestReport);

        } catch (NumberFormatException e) {
            
             JOptionPane.showMessageDialog(
                    this, "Invalid price. Please enter numbers only."
            );
        }
    }
    
    private void saveCustomer() {
        
        if (latestReport == null) {

            JOptionPane.showMessageDialog(
                    this, "Please click PROCESS before saving."
            );

            return;
        }
        
        if (!txtCustomerName.getText().trim().equals(lastProcessedName)
                || !txtDailyPrice.getText().trim().equals(lastProcessedPriceText)
                || !cmbRentalDays.getSelectedItem().toString().equals(lastProcessedRentalDays)) {

            JOptionPane.showMessageDialog(
                    this, "Information has changed. Please click PROCESS again before saving."
            );

            return;
        }
        
         try (FileWriter writer = new FileWriter("data.txt")) {
             
             writer.write("DATA LOG\n");

            writer.write("********************************\n");

            // Writes the customer name, duration, VAT and Total.
            writer.write(latestReport + "\n");

            writer.write("********************************\n");
            
            JOptionPane.showMessageDialog(
                    this, "Customer information saved to data.txt successfully."
            );
         } catch (IOException e) {
             
             JOptionPane.showMessageDialog(
                    this, "Could not save data.txt: " + e.getMessage()
            );
         }
    }
    
}
