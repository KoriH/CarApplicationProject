package persistence;

import model.ListOfCars;

import org.json.JSONObject;
import java.io.*;

// A writer that writes car database data into a JSON file
// Inspired by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class FileWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // EFFECTS: Constructs the file writer to write file to destination
    public FileWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: Opens the FileWriter; if FileWriter cannot be opened FileNotFoundException is thrown
    public void openWriter() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: Writes the Json representation of ListOfCars to a file
    public void writeFile(ListOfCars listOfCars) {
        JSONObject json = listOfCars.toJson();
        saveFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: Closes the file writer
    public void closeWriter() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: Writes String to file
    public void saveFile(String json) {
        writer.print(json);
    }

}
