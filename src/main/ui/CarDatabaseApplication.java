package ui;

import model.Car;
import model.EventLog;
import model.ListOfCars;
import persistence.FileReader;
import persistence.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.*;
import java.util.*;

public class CarDatabaseApplication {
    private ListOfCars listOfCars;
    private Scanner input;
    private boolean exitApp;
    private final FileReader fileReader;
    private final FileWriter fileWriter;
    private static final String JSON_STORE = "./data/saveFile.json";


    // EFFECTS: constructs and runs the application
    public CarDatabaseApplication() {
        fileWriter = new FileWriter(JSON_STORE);
        fileReader = new FileReader(JSON_STORE);

        runCarDealershipApplication();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCarDealershipApplication() {
        exitApp = false;
        String command;
        input = new Scanner(System.in);

        initializeApp();

        while (!exitApp) {

            displayMenu();
            command = input.next();
            processInput(command);
        }

        System.out.println("\nExiting Application");
    }

    // EFFECTS: displays the menu for the application
    private void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("\t1 -> Display database of cars");
        System.out.println("\t2 -> Add a new car to the database");
        System.out.println("\t3 -> Delete a car from the database");
        System.out.println("\t4 -> To Replace a Car");
        System.out.println("\t5 -> To Save file");
        System.out.println("\t6 -> To Load File");
        System.out.println("\t0 -> Exit the Program");
    }


    // EFFECTS: Assigns a method to the input of the user
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void processInput(String command) {
        switch (command) {
            case "1":
                displayDatabase();
                break;
            case "2":
                addCar();
                break;
            case "3":
                deleteCar();
                break;
            case "4":
                replaceCar();
                break;
            case "5":
                saveFile();
                break;
            case "6":
                loadFile();
                break;
            case "0":
                LogPrinter lp = new PrintLog();
                try {
                    lp.printLog(EventLog.getInstance());
                }  catch (Exception e) {
                    System.out.println("System Error");
                }
                exitApp = true;
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: replaces a car
    private void replaceCar() {
        System.out.print("What is the index of the old car?");
        deleteCar();

        System.out.print("What is the information of the new car?");
        addCar();

    }


    // MODIFIES: this
    // EFFECTS: deletes a car from the list
    private void deleteCar() {
        int carIndex;
        displayDatabase();
        System.out.print("Delete car #: ");
        carIndex = input.nextInt();
        listOfCars.removeCarFromListIndex(carIndex - 1);
    }

    // MODIFIES: this
    // EFFECTS: adds a car to the list
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addCar() {
        String vin;
        String title;
        int year;
        String make;
        String model;
        int mileage;
        float price;

        System.out.println("Please enter the following details:");
        System.out.println("VIN of car:");
        vin = input.next();
        System.out.println("Title of car:");
        title = input.next();
        System.out.println("Year of car:");
        year = input.nextInt();
        System.out.println("Make of car:");
        make = input.next();
        System.out.println("Model of car");
        model = input.next();
        System.out.println("Mileage of car:");
        mileage = input.nextInt();
        System.out.println("Price of car:");
        price = input.nextFloat();

        Car car = new Car(vin, title, year, make, model, mileage, price);
        listOfCars.addCarToList(car);

    }

    // EFFECTS: prints the list of cars
    private void displayDatabase() {
        System.out.println(listOfCars.displayListOfCars());

    }

    // EFFECTS: initializes list
    private void initializeApp() {
        listOfCars = new ListOfCars();
    }

    // MODIFIES: saveFile
    // EFFECTS: saves a file
    private void saveFile() {
        try {
            fileWriter.openWriter();
            fileWriter.writeFile(listOfCars);
            fileWriter.closeWriter();
            System.out.println("Saved Database");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file ");
        }
    }

    // EFFECTS: loads saved file
    private void loadFile() {
        try {
            listOfCars = fileReader.read();
            System.out.println("Log successfully loaded from file!");

        } catch (IOException e) {
            System.out.println("Unable to read from file ");
        }
    }
}
