package persistence;

import org.json.JSONObject;

// Represents data that can be saved to a JSON file
// Inspired by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {

    // MODIFIES: this
    // EFFECTS: returns file as a JSON object
    JSONObject toJson();

}
