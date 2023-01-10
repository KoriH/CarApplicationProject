package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Car with different attributes
public class Car implements Writable {
    private final String vin;
    private final String title;
    private final int year;
    private final String make;
    private final String model;
    private final int mileage;
    private final float price;

    // REQUIRES: Car
    // MODIFIES: This
    // EFFECTS: Creates a new car with the following arguments
    public Car(String vin, String title, int year, String make, String model, int mileage, float price) {
        this.vin = vin;
        this.title = title;
        this.year = year;
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.price = price;
    }

    // getters
    public String getVin() {
        return vin;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public float getPrice() {
        return price;
    }

    // EFFECTS: Returns a JSON representation of this Car
    // Inspired by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("vin", vin);
        json.put("title", title);
        json.put("year", year);
        json.put("make", make);
        json.put("model", model);
        json.put("mileage", mileage);
        json.put("price", price);
        return json;
    }


    // EFFECTS: Makes it so the year, make, and model are displayed
    @Override
    public String toString() {
        return year + " " + make + " " + model;
    }
}


