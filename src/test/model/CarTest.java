package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car test1;
    private Car test2;

    @BeforeEach
    public void runBefore() {
        test1 = new Car("ADJ36", "ACTIVE", 2012, "Honda", "Civic", 10000, 500);
        test2 = new Car("K8VP6", "REBUILT", 1999, "Toyota", "CR-V", 1030, 900);
    }

    @Test
    public void testSetCar() {
        assertEquals(test1.getVin(), "ADJ36");
        assertEquals(test1.getTitle(), "ACTIVE");
        assertEquals(test1.getYear(), 2012);
        assertEquals(test1.getMake(), "Honda");
        assertEquals(test1.getModel(), "Civic");
        assertEquals(test1.getMileage(), 10000);
        assertEquals(test1.getPrice(), 500);
        assertEquals(test2.getVin(), "K8VP6");
        assertEquals(test2.getTitle(), "REBUILT");
        assertEquals(test2.getYear(), 1999);
        assertEquals(test2.getMake(), "Toyota");
        assertEquals(test2.getModel(), "CR-V");
        assertEquals(test2.getMileage(), 1030);
        assertEquals(test2.getPrice(), 900);
    }

    @Test
    public void testToString() {
        assertEquals(test1.toString(), "2012 Honda Civic");
    }
}