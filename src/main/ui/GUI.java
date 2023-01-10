package ui;

import model.Car;
import model.EventLog;
import model.ListOfCars;
import persistence.FileReader;
import persistence.FileWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// GUI form of the CarDealershipApplication
public class GUI {
    private static final String JSON_STORE = "./data/saveFile.json";
    private ListOfCars cars;
    private FileReader fileReader;
    private FileWriter fileWriter;

    private JFrame frame;
    private JList<Car> carsJList;
    private DefaultListModel<Car> carsListModel;

    private JLabel label;
    private ImageIcon image;
    private JPanel panel;
    private JPanel buttonPanelMenu;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JSplitPane splitPane;


    // Constructs and runs the program
    public GUI() {
        initializeApplication();
        initializeScrollPanel();
        initializeButtons();
        initializeActions();
        initializeFrame();

        loadPrompt();
        savePrompt();
    }


    // EFFECTS: Initializes fields in the application
    public void initializeApplication() {
        fileWriter = new FileWriter(JSON_STORE);
        fileReader = new FileReader(JSON_STORE);
        frame = new JFrame("Car Database Application");
        carsJList = new JList<>();
        carsListModel = new DefaultListModel<>();
        cars = new ListOfCars();
        image = new ImageIcon("./data/background.png");
        label = new JLabel();
        panel = new JPanel();
        splitPane = new JSplitPane();
    }


    // MODIFIES: this
    // EFFECTS: Initializes the scroll panel, split panel, and label that displays the number of cars in the list
    public void initializeScrollPanel() {
        carsJList.setModel(carsListModel);

        label.setText("Number of Cars in Database: " + carsListModel.getSize());

        splitPane.setLeftComponent(new JScrollPane(carsJList));
        panel.add(label);
        splitPane.setRightComponent(panel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(300);
        splitPane.setMinimumSize(new Dimension(500, 400));
    }


    // MODIFIES: this
    // EFFECTS: creates the buttons and adds them to the main menu
    public void initializeButtons() {
        buttonPanelMenu = new JPanel();
        buttonPanelMenu.setLayout(new FlowLayout());

        buttonAdd = new JButton("Add a Vehicle");
        buttonRemove = new JButton("Remove a Vehicle");

        buttonAdd.setActionCommand("Add");
        buttonRemove.setActionCommand("Remove");

        buttonPanelMenu.add(buttonAdd);
        buttonPanelMenu.add(buttonRemove);
    }


    // MODIFIES: this, cars
    // EFFECTS: Opens a pop-up window for the user to enter a car into the database
    public void initializeAddButton() {
        buttonAdd.addActionListener(e -> {
            AddCarWindow addCarWindow = new AddCarWindow();
            JPanel panel = addCarWindow.returnJPanel();

            int optionPaneValue = JOptionPane.showConfirmDialog(null, panel, "Add a new Car",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, image);
            if (optionPaneValue == JOptionPane.OK_OPTION) {
                try {
                    String vin = addCarWindow.getVIN();
                    String make = addCarWindow.getMake();
                    String model = addCarWindow.getModel();
                    String title = addCarWindow.getTitle();
                    int year = addCarWindow.getYear();
                    int mileage = addCarWindow.getMileage();
                    float price = addCarWindow.getPrice();
                    Car newCar = new Car(vin, title, year, make, model, mileage, price);
                    cars.addCarToList(newCar);
                    updateUI();
                } catch (Exception exception) {
                    System.out.println("Please input the correct values.");
                }
            }
        });
    }


    // MODIFIES: this, cars
    // EFFECTS: Removes an element from the cars list and model list on screen
    public void initializeRemoveButton() {
        buttonRemove.addActionListener(e -> {
            ListSelectionModel selectionModel = carsJList.getSelectionModel();
            int index = selectionModel.getMinSelectionIndex();
            if (index >= 0) {
                cars.removeCarFromListIndex(index);
                updateUI();
            }
        });
    }


    // EFFECTS: Initializes both button actions
    public void initializeActions() {
        initializeAddButton();
        initializeRemoveButton();
    }


    // MODIFIES: this
    // EFFECTS: Initializes the frame for the UI
    public void initializeFrame() {
        frame.setPreferredSize(new Dimension(600, 500));
        frame.setLayout((new BorderLayout()));
        frame.add(splitPane);
        frame.add(buttonPanelMenu, BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: prompts the user to load a previous save file when opening the program
    public void loadPrompt() {
        int load = JOptionPane.showConfirmDialog(null, "Would you like to load"
                + " the database?", "Load File", JOptionPane.YES_NO_OPTION);
        if (load == JOptionPane.YES_OPTION) {
            try {
                cars = fileReader.read();
                updateUI();
                System.out.println("Log successfully loaded from file!");

            } catch (IOException e) {
                System.out.println("Unable to read from file from: " + JSON_STORE);
            }
        }
    }


    // MODIFIES: saveFile.Json
    // EFFECTS: Prompts user to save file when they close the program
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void savePrompt() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                int saveOption = JOptionPane.showConfirmDialog(null,
                        "Would you like to save your log before exiting?", "Save File",
                        JOptionPane.YES_NO_OPTION);
                if (saveOption == JOptionPane.YES_OPTION) {
                    try {
                        fileWriter.openWriter();
                        fileWriter.writeFile(cars);
                        fileWriter.closeWriter();
                        System.out.println("Saved Database");
                    } catch (FileNotFoundException e) {
                        System.out.println("Unable to write to file: " + JSON_STORE);
                    }
                }
                LogPrinter lp = new PrintLog();
                try {
                    lp.printLog(EventLog.getInstance());
                }  catch (Exception e) {
                    System.out.println("System Error");
                }
                System.exit(0);
            }
        });
    }

    // MODIFIES: carListModel
    // EFFECTS: updates cars list and number of cars in list label after changing values
    private void updateUI() {
        carsListModel.clear();
        List<Car> carsList = cars.getCarDatabase();
        for (Car car : carsList) {
            carsListModel.addElement(car);
        }
        label.setText("Number of Cars in Database: " + carsListModel.getSize());
    }

}
