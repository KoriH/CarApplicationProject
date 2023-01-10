package persistence;

import model.Car;
import model.ListOfCars;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class FileReaderTest extends JSONTest {

    @Test
    void testReaderNonExistentFile() {
        FileReader reader = new FileReader("./data/noSuchFile.json");
        try {
            ListOfCars cars = reader.read();
            fail("IOException expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderEmptyListOfCars() {
            FileReader reader = new FileReader("./data/testReaderEmptyWorkRoom.json");
            try {
                ListOfCars cars = reader.read();
                assertEquals(0, cars.getNumberOfCarsInList());
            } catch (IOException e) {
                fail("Couldn't read from file");
            }
        }

    @Test
    void testReaderGeneralListOfCars() {
        FileReader reader = new FileReader("./data/testReaderGeneralWorkRoom.json");
        try {
            ListOfCars cars = reader.read();
            ArrayList<Car> car = cars.getCarDatabase();
            assertEquals(1, cars.getNumberOfCarsInList());

        } catch (IOException e) {
            fail("Failed to read from file");
        }
    }

}