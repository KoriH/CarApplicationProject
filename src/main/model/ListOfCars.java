package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of Cars
public class ListOfCars implements Writable {
    private final ArrayList<Car> listOfCars;

    // EFFECTS: Creates a New Array List of Cars
    public ListOfCars() {
        listOfCars = new ArrayList<>();
    }

    // MODIFIES: This
    // EFFECTS: Adds a new car to the list
    public void addCarToList(Car car) {
        listOfCars.add(car);
        EventLog.getInstance().logEvent(new Event("Added car to list: " + car));
    }

    // MODIFIES: This
    // EFFECTS: Deletes a car from the list
    public void removeCarFromList(Car car) {
        listOfCars.remove(car);
        EventLog.getInstance().logEvent(new Event("Removed car: " + car));
    }

    // MODIFIES: this
    // EFFECTS: Removes a car from the list by its index placement
    public void removeCarFromListIndex(int i) {
        if (i >= 0) {
            listOfCars.remove(i);
            int x = i + 1;
            EventLog.getInstance().logEvent(new Event("Removed car at index: " + x));
        } else {
            System.out.println("Out of Bounds");
        }
    }

    // EFFECTS: Returns true if the list contains the car
    public boolean doesListContainCar(Car car) {
        return listOfCars.contains(car);
    }

    // EFFECTS: returns the size of the list
    public int getNumberOfCarsInList() {
        return listOfCars.size();
    }

    // MODIFIES: This
    // EFFECTS: Updates a car in the list by removing the old one and putting a new one in
    public void updateCar(Car oldCar, Car updatedCar) {
        removeCarFromList(oldCar);
        addCarToList(updatedCar);
    }

    // EFFECTS: Displays the List of Cars
    // Inspired by the TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp to design the representation
    // for the list
    public String displayListOfCars() {
        String listings = "";
        for (int i = 0; i < getNumberOfCarsInList(); i++) {
            listings += "Car #" + (1 + i) + "\n" + listOfCars.get(i).getVin() + "\n" + listOfCars.get(i).getTitle()
                    + "\n" + listOfCars.get(i).getYear() + "\n" + listOfCars.get(i).getMake() + "\n"
                    + listOfCars.get(i).getModel() + "\n" + listOfCars.get(i).getMileage() + " KM" + "\n"
                    + "$" + listOfCars.get(i).getPrice() + "\n";
        }
        return listings;
    }

    public ArrayList<Car> getCarDatabase() {
        return listOfCars;
    }

    // EFFECTS: converts car data in ListOfCars to JSON object
    // Inspired by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cars", carsToJson());
        return json;
    }

    // EFFECTS: Adds cars to the Json array
    private JSONArray carsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Car car : listOfCars) {
            jsonArray.put(car.toJson());
        }

        return jsonArray;
    }
}