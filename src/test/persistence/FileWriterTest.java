package persistence;

import model.Car;
import model.ListOfCars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FileWriterTest extends JSONTest {
    private ListOfCars testListOfCar1;
    private ListOfCars testListOfCar2;
    private Car testCar1;
    private Car testCar2;


    @BeforeEach
    void setup() {
        testListOfCar1 = new ListOfCars();
        testListOfCar2 = new ListOfCars();
        testCar1 =
                new Car("ADJ36", "ACTIVE", 2012, "Honda", "Civic", 10000, 500);
        testCar2 =
                new Car("JH432", "ACTIVE", 2011, "MAZDA", "3", 100030, 5040);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfCars cars = new ListOfCars();
            FileWriter writer = new FileWriter("./data/inv\0illegal:fileName.json");
            writer.openWriter();
            fail("IOException was expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testWriterEmptyListOfCars() {
        try {
            ListOfCars cars = new ListOfCars();
            FileWriter writer = new FileWriter("./data/testWriterEmptyListOfCars.json");
            writer.openWriter();
            writer.writeFile(cars);
            writer.closeWriter();

            FileReader reader = new FileReader("./data/testWriterEmptyListOfCars.json");
            cars = reader.read();
            assertEquals(0, cars.getNumberOfCarsInList());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralListOfCars() {
        try {
            testListOfCar1.addCarToList(testCar1);
            testListOfCar1.addCarToList(testCar2);
            FileWriter writer = new FileWriter("./data/testWriterGeneralListOfCars.json");

            writer.openWriter();
            writer.writeFile(testListOfCar1);
            writer.closeWriter();

            FileReader reader = new FileReader("./data/testWriterGeneralListOfCars.json");
            testListOfCar1 = reader.read();
            assertEquals(2, testListOfCar1.getNumberOfCarsInList());
            checkCar("ADJ36", "ACTIVE", 2012, "Honda", "Civic", 10000, 500,
                    testCar1);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
