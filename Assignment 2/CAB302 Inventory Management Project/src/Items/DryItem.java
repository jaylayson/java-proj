/**
 * All individual objects package.
 */
package Items;
/**
 * @author John Layson
 * A Dry item is an item that does not need to be temperature controlled.
 * Therefore it doesn't need to be ordered into a Refrigerated Truck, although
 * it CAN go into one.
 */
public class DryItem implements ItemInterface{
	
	private String name;
	private int cost;
	private int price;
	private int reorderP;
	private int reorderA;
	
	/**
	 * 
	 * A Dry Item is identified by having mainly a name cost and price.
	 * Though inventory management items must have a reorder point and amount
	 * for keeping stock levels in track
	 * 
	 * @param name is the name of the DryItem
	 * @param cost is the cost of the DryItem
	 * @param price is the price of the DryItem
	 * @param reorderP is the reorder point of the DryItem
	 * @param reorderA is the reorder amount of the DryItem
	 */
	public DryItem(String name, int cost, int price, int reorderP, int reorderA) {
		super();
		this.name = name;
		this.cost = cost;
		this.price = price;
		this.reorderP = reorderP;
		this.reorderA = reorderA;
	}
	
	

	/**
	 * @param name the item's name
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @param cost the item's cost
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}



	/**
	 * @param price the item's price
	 */
	public void setPrice(int price) {
		this.price = price;
	}



	/**
	 * @param reorderP the item's reorder point
	 */
	public void setReorderP(int reorderP) {
		this.reorderP = reorderP;
	}



	/**
	 * @param reorderA the item's reorder amount
	 */
	public void setReorderA(int reorderA) {
		this.reorderA = reorderA;
	}



	@Override
	public String itemName() {
		return name;
	}

	@Override
	public int itemCost() {
		return cost;
	}

	@Override
	public int itemPrice() {
		return price;
	}

	@Override
	public int reorderPoint() {
		return reorderP;
	}

	@Override
	public int reorderAmount() {
		return reorderA;
	}
	
	@Override
	public String toString() {
		String string;
		string = "[Item Name: " + itemName() + "]" +
				"[Item Cost: " + itemCost() + "]" + 
				"[Item Price: " + itemPrice() + "]" + 
				"[Item Reorder Point: " + reorderPoint() + "]" +
				"[Item Reorder Amount: " + reorderAmount() + "]";
		return string;
	}
	
}