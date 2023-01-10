package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ListOfCarsTest {
    private ListOfCars testListOfCar1;
    private ListOfCars testListOfCar2;
    private Car testCar1;
    private Car testCar2;

    @BeforeEach
    public void runBefore() {
        testListOfCar1 = new ListOfCars();
        testListOfCar2 = new ListOfCars();
        testCar1 =
                new Car("ADJ36", "ACTIVE", 2012, "Honda", "Civic", 10000, 500);
        testCar2 =
                new Car("JH432", "ACTIVE", 2011, "MAZDA", "3", 100030, 5040);
    }

    @Test
    public void testAddCar() {
        assertFalse(testListOfCar1.doesListContainCar(testCar1));
        testListOfCar1.addCarToList(testCar1);
        assertTrue(testListOfCar1.doesListContainCar(testCar1));
        testListOfCar1.addCarToList(testCar1);
        assertTrue(testListOfCar1.doesListContainCar(testCar1));
        assertFalse(testListOfCar2.doesListContainCar(testCar1));
    }

    @Test
    public void testGetNumberOfCarsInList() {
        assertEquals(testListOfCar1.getNumberOfCarsInList(), 0);
        assertEquals(testListOfCar2.getNumberOfCarsInList(), 0);
        testListOfCar2.addCarToList(testCar1);
        assertEquals(testListOfCar2.getNumberOfCarsInList(), 1);
        testListOfCar2.addCarToList(testCar1);
        assertEquals(testListOfCar2.getNumberOfCarsInList(), 2);
        assertEquals(testListOfCar1.getNumberOfCarsInList(), 0);
    }


    @Test
    public void testDoesListContainCar(){
        assertFalse(testListOfCar1.doesListContainCar(testCar1));
        testListOfCar1.addCarToList(testCar1);
        assertFalse(testListOfCar1.doesListContainCar(testCar2));
        assertTrue(testListOfCar1.doesListContainCar(testCar1));
        testListOfCar1.addCarToList(testCar2);
        assertTrue(testListOfCar1.doesListContainCar(testCar1));
    }

    @Test
    public void testListOfCars(){
        assertFalse(testListOfCar1.doesListContainCar(testCar1));
        testListOfCar1.addCarToList(testCar2);
        assertFalse(testListOfCar1.doesListContainCar(testCar1));
        testListOfCar1.addCarToList(testCar1);
        assertTrue(testListOfCar1.doesListContainCar(testCar1));
    }

    @Test
    public void testRemoveCarIndex() {
        assertFalse(testListOfCar1.doesListContainCar(testCar1));
        testListOfCar1.addCarToList(testCar1);
        assertTrue(testListOfCar1.doesListContainCar(testCar1));
        testListOfCar1.removeCarFromListIndex(0);
        assertFalse(testListOfCar1.doesListContainCar(testCar1));
        testListOfCar1.addCarToList(testCar1);
        assertTrue(testListOfCar1.doesListContainCar(testCar1));
        testListOfCar1.removeCarFromListIndex(-1);
    }

    @Test
    public void testUpdateCar() {
        testListOfCar1.addCarToList(testCar1);
        testListOfCar1.updateCar(testCar1, testCar2);
        assertTrue(testListOfCar1.doesListContainCar(testCar2));
        testListOfCar2.addCarToList(testCar2);
        testListOfCar2.updateCar(testCar1, testCar2);
        assertTrue(testListOfCar1.doesListContainCar(testCar2));
        testListOfCar2.addCarToList(testCar2);
        testListOfCar2.updateCar(testCar2, testCar1);
        assertTrue(testListOfCar1.doesListContainCar(testCar2));
    }

    @Test
    public void testDisplayListOfCars() {
        assertEquals(testListOfCar1.displayListOfCars(), "");
        testListOfCar1.addCarToList(testCar1);
        assertEquals(testListOfCar1.displayListOfCars(), "Car #" + (1) + "\n" + "ADJ36" + "\n" + "ACTIVE"
                + "\n" + 2012 + "\n" + "Honda" + "\n" + "Civic" + "\n" + 10000 + " KM" + "\n"
                + "$" + 500.0 + "\n");
    }

    @Test
    public void testGetCarDatabase() {
        testListOfCar1.addCarToList(testCar1);
        testListOfCar1.getCarDatabase();
        assertEquals(testListOfCar1.getNumberOfCarsInList(), testListOfCar1.getCarDatabase().size());
    }
}