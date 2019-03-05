package delivery;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Nathan Thai
 * A Manifest is a collection of the item's that need to be 
 * restocked which is used to determine which trucks are needed.
 */
public class Manifest {
	String name;
	int quantity;
		
	/**
	 * Calls super interface
	 * Assigns given values to local name and quantity variables
	 * @param name the name of the item
	 * @param quantity the quantity related to the item name
	 */
	public Manifest(String name, int quantity) 
	{
		super();
		this.name = name;
		this.quantity = quantity;
	}

	/**
	 * @return item name as type String
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Assigns string value to local name variable
	 * @param name the name of the item
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * @return Manifest quantity
	 */
	public int getQuantity() 
	{
		return quantity;
	}

	/**
	 * Assigns given int value to local quantity variable
	 * @param quantity the quantity of the item
	 */
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	/**
	 * Generates Manifest based on given String and Int 
	 * arguments. Adds each new input on a new line in csv file.
	 * Directory of generated file must be input manually.
	 * @param name the name of the item
	 * @param quantity the quantity related to the item name
	 * @throws IOException throws exception there is a failure or interruption in I/O operations
	 */
	public void generateManifest(String name, int quantity) throws IOException 
	{
        /*
         * NOTE: You might have to change the following directory to downloaded project path.
         */
        //BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/Nathan/Documents/inventory-management-application-john-layson-nathan-thai/project/Manifest1.csv", true));
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:/Work/git/inventory-management-application-john-layson-nathan-thai/project/Manifest1.csv", true));

        writer.append(name);
        writer.append(",");
        writer.append(String.valueOf(quantity));
        writer.newLine();
        writer.close();
    }
}
