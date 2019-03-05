/*
 * 
 */
package Items;

/**
 * @author John Layson
 * An item has a name, cost, price, reorder point and reorder amount
 * but may also have a temperature
 */
public abstract class ItemTemp implements ItemInterface{

	public ItemTemp(String name, int cost, int price, int reorderP, int reorderA) {
	}

	/**
	 * @return item's temperature
	 */
	public abstract int temperature();
	

}
