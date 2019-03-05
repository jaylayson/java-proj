package junitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import delivery.Manifest;
import delivery.OrdinaryTruck;
import delivery.RefrigeratedTruck;


/**
 * @author John Layson
 * This JUnit test class is for testing my partner's created classes that 
 * can be instantiated which are Ordinary Truck, Refrigerated Truck, 
 * and Manifest. As the most basic, I will try to instantiate an 
 * item and test if the program gets the right name and values it's supposed to have.
 */
public class TestCases1 {

	
	/**
	 * This test will test the ordinary truck's quantity 
	 * to ensure that the truck has 120 items initially,
	 * which is then changed to another quantity and checked again
	 * to ensure that the setter and getters work for this class
	 * correctly. This test passes.
	 */
	@Test
	public void testOrdinaryTruckQuantity() {
		OrdinaryTruck ordTruck = new OrdinaryTruck(120);
		int cargo = ordTruck.getItemQuantity();
		
		assertEquals(120, cargo);
		
		ordTruck.setTruckQuantity(200);
		cargo = ordTruck.getItemQuantity();
		
		assertEquals(120, cargo);
	}
	
	/**
	 * This test will test the ordinary truck's capacity 
	 * to ensure that the truck can only hold 1000 items initially,
	 * which is then changed to another capacity and checked again
	 * to ensure that the setter and getters work for this class
	 * correctly. This test passes.
	 */
	@Test
	public void testOrdinaryTruckCapacity() {
		OrdinaryTruck ordTruck = new OrdinaryTruck(1);	
		int capacity = ordTruck.getCapacity();				
		assertEquals(1000, capacity);
		
		ordTruck.setCapacity(500);
		capacity = ordTruck.getCapacity();
		assertEquals(500, capacity);
	}
	
	/**
	 * This test will test the ordinary truck's ability
	 * to calculate how much one truck will cost
	 * depending on the cargo.
	 * The test also changes the cargo quantity and checks
	 * the cost again to ensure it still calculates correctly
	 * which in turn tests the item quantity. This test passes.
	 */
	@Test
	public void testOrdinaryTruckCost() {
		OrdinaryTruck ordTruck = new OrdinaryTruck(800);
		double cost = ordTruck.calculatingCostO(ordTruck.getItemQuantity());
		assertEquals(950.0, cost,0);
		
		ordTruck = new OrdinaryTruck(500);
		cost = ordTruck.calculatingCostO(ordTruck.getItemQuantity());
		assertEquals(875.0, cost, 0);
	}
	
	/**
	 * This test will test the refrigerated truck's
	 * ability to set the quantity to a certain amount
	 * this determines how many refrigerated truck
	 * is supposed to be dispatched.
	 * As well as testing the getter method to identify quantity.
	 *  This test passes.
	 */
	@Test
	public void testRefrigeratedTruckQuantity() {
		RefrigeratedTruck refTruck = new RefrigeratedTruck(1);
		refTruck.setQuantity(4);
		int quantity = refTruck.quantity();
		
		assertEquals(4, quantity);
		
	}
	
	/**
	 * This test will test the refrigerated truck's
	 * given initial capacity, which is changed later
	 * and checked again for the last time
	 * to ensure that it sets and gets the new capacity.
	 *  This test passes.
	 */
	@Test
	public void testRefrigeratedTruckCapacity() {
		RefrigeratedTruck refTruck = new RefrigeratedTruck(1);
		int initialCapacity = refTruck.getCapacity();
		assertEquals(800, initialCapacity);
		
		refTruck.setCapacity(600);
		int newCapacity = refTruck.getCapacity();
		assertEquals(600, newCapacity);
	}
	
	/**
	 * This test will test the refrigerated truck's
	 * passed parameter to set the temperature.
	 * Also testing the getter method for the temperature.
	 *  This test passes.
	 */
	@Test
	public void testRefrigeratedTruckTemperature() {
		RefrigeratedTruck refTruck = new RefrigeratedTruck(10);
		double temp = refTruck.temperature();
		
		assertEquals(10, temp, 0);
	}
	
	/**
	 * This test will test the refrigerated truck's ability
	 * to calculate how much one truck will cost
	 * depending on the cargo's needed temperature.
	 *  This test passes.
	 */
	@Test
	public void testRefrigeratedTruckCost() {
		RefrigeratedTruck refTruck = new RefrigeratedTruck(10);
		double cost = refTruck.calculatingCostR(refTruck.temperature());
		
		assertEquals(998.0, cost, 0);
	}
	
	
	/**
	 * This is testing that the Manifest can get the name passed
	 * in the parameter.
	 *  This test passes.
	 */
	@Test
	public void testManifestName() {
		Manifest manifest1 = new Manifest("Almonds", 3);
		String name = manifest1.getName();
		
		assertEquals("Almonds", name);
		
	}
	
	/**
	 * This is testing that the Manifest can get the quantity passed
	 * in the parameter.
	 *  This test passes.
	 */
	@Test
	public void testManifestQuantity() {
		Manifest manifest1 = new Manifest("Almonds", 3);
		
		int amount = manifest1.getQuantity();
		
		assertEquals(3, amount);
	}

}
