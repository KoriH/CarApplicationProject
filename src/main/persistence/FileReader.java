package persistence;

import model.Car;
import model.ListOfCars;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// A reader that reads a car database from a JSON data stored in a file
// Inspired by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class FileReader {
    private final String source;

    // EFFECTS: Constructs reader to read source file
    public FileReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfCars from file then returns it
    // throws IOException if an error occurs reading data from file
    public ListOfCars read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfCars(jsonObject);
    }

    // EFFECT: reads source file as a string then returns it
    // throws IOException if an error occurs reading data from file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);

        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfCars from JSON object and returns it
    private ListOfCars parseListOfCars(JSONObject jsonObject) {
        ListOfCars listOfCars = new ListOfCars();
        addCars(listOfCars, jsonObject);
        return listOfCars;
    }

    // MODIFIES: listOfCars
    // EFFECTS: parses cars from JSON object and adds them to ListOfCars
    private void addCars(ListOfCars listOfCars, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cars");
        for (Object json : jsonArray) {
            JSONObject nextListOfCars = (JSONObject) json;
            addCar(listOfCars, nextListOfCars);
        }
    }

    // MODIFIES: listOfCars
    // EFFECTS: parses Cars from JSON object and adds it to workroom
    private void addCar(ListOfCars listOfCars, JSONObject jsonObject) {
        String vin = jsonObject.getString("vin");
        String title = jsonObject.getString("title");
        int year = jsonObject.getInt("year");
        String make = jsonObject.getString("make");
        String model = jsonObject.getString("model");
        int mileage = jsonObject.getInt("mileage");
        float price = jsonObject.getFloat("price");
        Car car = new Car(vin, title, year, make, model, mileage, price);
        listOfCars.addCarToList(car);
    }
}