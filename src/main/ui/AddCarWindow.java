package ui;

import javax.swing.*;
import java.awt.*;

// A window where the user will insert information to add a car to the list
public class AddCarWindow {
    JFrame frame;
    JPanel panel;

    JLabel vin;
    JLabel year;
    JLabel make;
    JLabel model;
    JLabel title;
    JLabel mileage;
    JLabel price;

    JTextField vinText;
    JTextField yearText;
    JTextField makeText;
    JTextField modelText;
    JTextField titleText;
    JTextField mileageText;
    JTextField priceText;


    // Constructs and runs the class
    public AddCarWindow() {
        initializeNewWindowLabels();
        initializeTextFields();
        initializePanel();
    }


    // EFFECTS: Initializes the labels for the panel
    private void initializeNewWindowLabels() {
        frame = new JFrame("Add a Car");
        panel = new JPanel();

        vin = new JLabel("VIN of car: ");
        year = new JLabel("Year of car: ");
        make = new JLabel("Make of car: ");
        model = new JLabel("Model of car: ");
        title = new JLabel("Title of car: ");
        mileage = new JLabel("Mileage of car: ");
        price = new JLabel("Price of car: ");
    }


    // EFFECTS: Initializes the text fields for the panel
    private void initializeTextFields() {
        vinText = new JTextField();
        vinText.setMaximumSize(new Dimension(250, 25));
        yearText = new JTextField();
        yearText.setMaximumSize(new Dimension(250, 25));
        makeText = new JTextField();
        makeText.setMaximumSize(new Dimension(250, 25));
        modelText = new JTextField();
        modelText.setMaximumSize(new Dimension(250, 25));
        titleText = new JTextField();
        titleText.setMaximumSize(new Dimension(250, 25));
        mileageText = new JTextField();
        mileageText.setMaximumSize(new Dimension(250, 25));
        priceText = new JTextField();
        priceText.setMaximumSize(new Dimension(250, 25));
    }


    // EFFECTS: Adds the labels and text fields to the panel
    private void initializePanel() {
        panel.add(vin);
        panel.add(vinText);
        panel.add(year);
        panel.add(yearText);
        panel.add(make);
        panel.add(makeText);
        panel.add(model);
        panel.add(modelText);
        panel.add(title);
        panel.add(titleText);
        panel.add(mileage);
        panel.add(mileageText);
        panel.add(price);
        panel.add(priceText);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(250,300));
        panel.setPreferredSize(new Dimension(250,300));
    }


    // EFFECTS: returns the panel
    public JPanel returnJPanel() {
        return panel;
    }

    // getters
    public String getVIN() {
        return vinText.getText();
    }

    public String getTitle() {
        return titleText.getText();
    }

    public int getYear() {
        return Integer.parseInt(yearText.getText());
    }

    public String getMake() {
        return makeText.getText();
    }

    public String getModel() {
        return modelText.getText();
    }

    public int getMileage() {
        return Integer.parseInt(mileageText.getText());
    }

    public float getPrice() {
        return Float.parseFloat(priceText.getText());
    }

}
