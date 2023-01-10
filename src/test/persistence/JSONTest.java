package persistence;

import model.Car;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JSONTest {

    // EFFECTS: Compares given car(s) with entered data
    protected void checkCar(String vin, String title, int year, String make, String model, int mileage, float price,
                            Car car) {
        assertEquals(vin, car.getVin());
        assertEquals(title, car.getTitle());
        assertEquals(year, car.getYear());
        assertEquals(make, car.getMake());
        assertEquals(model, car.getModel());
        assertEquals(mileage, car.getMileage());
        assertEquals(price, car.getPrice());
    }
}
