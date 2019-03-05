/**
 * 
 */
package Items;

/**
 * @author John Layson
 * Every item in a Store must have a name, cost,
 * price, reorder point, and reorder amount. 
 * This ensures that they are each uniquely identifiable.
 */
public interface ItemInterface {

	/**
	 * @return the item's name
	 */
	String itemName();
	

	/**
	 * @return the item's cost 
	 */
	int itemCost();
	

	/**
	 * @return the item's price
	 */
	int itemPrice();
	

	/**
	 * @return the reorder point
	 */
	int reorderPoint();
	
	

	/**
	 * @return the reorder amount
	 */
	int reorderAmount();
	
}
