package junitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Items.DryItem;
import Items.RefrigeratedItem;
import store.Stock;
import store.Store;

/**
 * @author Nathan Thai
 * This JUnit test class is used to test the instantiable classes
 * that were created by my partner. Those classes being DryItem,
 * RefrigeratedItem, Store and Stock. 
 */
public class TestCases2 {

	/**
	 * The following test will verify that DryItem passes the given 
	 * string argument value correctly for the item name.
	 */
	@Test
	public void testDryItemItemName()
	{
		DryItem dryItem = new DryItem("test", 0, 0, 0, 0);
		String testString = dryItem.itemName();
		
		assertEquals(testString, "test");
	}
	
	/**
	 * The following test will verify that DryItem passes the given
	 * integer argument value correctly for the item cost.
	 */
	@Test
	public void testDryItemItemCost()
	{
		DryItem dryItem = new DryItem("test", 10, 0, 0, 0);
		int testCost = dryItem.itemCost();
		
		assertEquals(testCost, 10);
	}
	
	/**
	 * The following test will verify that DryItem passes the given
	 * integer argument value correctly for the item price.
	 */
	@Test
	public void testDryItemItemPrice()
	{
		DryItem dryItem = new DryItem("test", 0, 10, 0, 0);
		int testPrice = dryItem.itemPrice();
		
		assertEquals(testPrice, 10);
	}
	
	/**
	 * The following test will verify that DryItem passes the given
	 * integer argument value correctly for the reorder point.
	 */
	@Test
	public void testDryItemReorderPoint()
	{
		DryItem dryItem = new DryItem("test", 0, 0, 10, 0);
		int testReorderPoint = dryItem.reorderPoint();
		
		assertEquals(testReorderPoint, 10);
	}
	
	/**
	 * The following test will verify that DryItem passes the given
	 * integer argument value correctly for the reorder amount.
	 */
	@Test
	public void testDryItemReorderAmount()
	{
		DryItem dryItem = new DryItem("test", 0, 0, 0, 10);
		int testReorderAmount = dryItem.reorderAmount();
		
		assertEquals(testReorderAmount, 10);
	}
	
	/**
	 * The following test will verify that RefrigeratedItem passes the
	 * given string argument value correctly for the item name.
	 */
	@Test
	public void testRefrigeratedItemItemName()
	{
		RefrigeratedItem refrigeratedItem = new RefrigeratedItem("test", 0, 0, 0, 0, 0);
		String testString = refrigeratedItem.itemName();
		
		assertEquals(testString, "test");
		
	}
	
	/**
	 * The following test will verify that RefrigeratedItem passes the
	 * given integer argument value correctly for the item cost.
	 */
	@Test
	public void testRefrigeratedItemItemCost()
	{
		RefrigeratedItem refrigeratedItem = new RefrigeratedItem("test", 10, 0, 0, 0, 0);
		int testCost = refrigeratedItem.itemCost();
		
		assertEquals(testCost, 10);
	}
	
	/**
	 * The following test will verify that RefrigeratedItem passes the
	 * given integer argument value correctly for the item price.
	 */
	@Test
	public void testRefrigeratedItemItemPrice()
	{
		RefrigeratedItem refrigeratedItem = new RefrigeratedItem("test", 0, 10, 0, 0, 0);
		int testPrice = refrigeratedItem.itemPrice();
		
		assertEquals(testPrice, 10);
	}
	
	/**
	 * The following test will verify that RefrigeratedItem passes the
	 * given integer argument value correctly for the reorder point.
	 */
	@Test
	public void testRefrigeratedItemReorderPoint()
	{
		RefrigeratedItem refrigeratedItem = new RefrigeratedItem("test", 0, 0, 10, 0, 0);
		int testReorderPoint = refrigeratedItem.reorderPoint();
		
		assertEquals(testReorderPoint, 10);
	}
	
	/**
	 * The following test will verify that RefrigeratedItem passes the
	 * given integer argument value correctly for the reorder amount.
	 */
	@Test
	public void testRefrigeratedItemReorderAmount()
	{
		RefrigeratedItem refrigeratedItem = new RefrigeratedItem("test", 0, 0, 0, 10, 0);
		int testReorderAmount = refrigeratedItem.reorderAmount();
		
		assertEquals(testReorderAmount, 10);
	}
	
	/**
	 * The following test will verify that RefrigeratedItem passes the 
	 * given integer argument value correctly for the temperature.
	 */
	@Test
	public void testRefrigeratedItemTemperature()
	{
		RefrigeratedItem refrigeratedItem = new RefrigeratedItem("test", 0, 0, 0, 0, 10);
		int testTemperature = refrigeratedItem.temperature();
		
		assertEquals(testTemperature, 10);
	}
	
	/**
	 * The following code is used to create an instance of the store that
	 * will be used for any of the proceeding tests involving Store.
	 */
	Store store = Store.getInstance();
	
	/**
	 * The following test will verify that the default capital of the 
	 * Store is 100000
	 * 
	 * NOTE: the capital default is set to 100000, but due to running 
	 * all tests simultaneously, this test is run after the next test
	 * (a similar test that sets the capital to 20). 
	 * If testing default Capital value of 100000 is desired, this test
	 * must be run individually, as well as commenting out the line
	 * "store.setCapital(100000);" 
	 */
	@Test
	public void testStoreGetCapital()
	{	
		store.setCapital(100000); //This line must be commented out, along with this test being run individually if you wish to test the default capital value of 100000.
		
		double testGetCapital = store.getCapital();
		
		assertEquals(testGetCapital, 100000, 0);
	}
	
	/**
	 * The following test will verify that setCapital() correctly assigns
	 * the given value to the local variable, in this case the value 20.
	 */
	@Test
	public void testStoreSetCapital()
	{	
		store.setCapital(20);
		double testGetCapital = store.getCapital();
		
		assertEquals(testGetCapital, 20, 0);
	}
	
	/**
	 * The following test will verify that getName() returns the string name
	 * "Super Mart".
	 */
	@Test
	public void testStoreGetName()
	{	
		String testGetName = store.getName();
		
		assertEquals(testGetName, "Super Mart");
	}
	
	/**
	 * The following test will verify that Stock passes the given 
	 * string argument value correctly for the name.
	 */
	@Test
	public void testStockGetName()
	{
		Stock stock = new Stock("test", 0);
		String testGetName = stock.getName();
		
		assertEquals(testGetName, "test");
		
	}
	
	/**
	 * The following test will verify that Stock passes the given 
	 * integer argument value correctly for the quantity.
	 */
	@Test
	public void testStockGetQuantity()
	{
		Stock stock = new Stock("test", 20);
		int testGetQuantity = stock.getQuantity();
		
		assertEquals(testGetQuantity, 20);
	}
	
	/**
	 * The following test will verify that setQuantity() correctly assigns
	 * the given value to the local variable, in this case the value 20.
	 */
	@Test
	public void testStockSetQuantity()
	{
		Stock stock = new Stock("test", 20);
		stock.setQuantity(50);
		int testGetQuantity = stock.getQuantity();
		
		assertEquals(testGetQuantity, 50);
	}
	
}
