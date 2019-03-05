/**
 * All individual objects package.
 */
package store;

/**
 * @author John Layson
 * Stock is a collection of the Items.
 * It serves as collecting the Items to put into 'Stock'
 */
public class Stock {
	private String name;
	private int quantity;

	/**
	 * @param name item name related to the other parameter
	 * @param quantity the quantity of the item name
	 */
	public Stock(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	/**
	 * @return the Stock name
	 */
	public String getName() {
		return name;
	}
	

	/**
	 * @param name sets the name for the individual item in stock
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the Stock quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity sets the stock quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 
	 * Overriden toString for testing purposes when printing outputs.
	 */
	@Override
	public String toString() {
		String string;
		string = "[Item Name: " + getName() + "]" +
				"[Item Quantity: " + getQuantity() + "]" + "\n";
		return string;
	}
}
