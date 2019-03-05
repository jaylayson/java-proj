package Items;

/**
 * @author John Layson
 * A Refrigerated item must be transported in a temperature controlled cargo
 * It cannot go into a non-temperature controlled truck.
 */
public class RefrigeratedItem extends ItemTemp{
	
	private String name;
	private int cost;
	private int price;
	private int reorderP;
	private int reorderA;
	private int temp;
	
	/**
	 * A Refrigerated Item is identified by having mainly a name cost and price.
	 * Though inventory management items must have a reorder point and amount
	 * for keeping stock levels in track
	 * 
	 * 
	 * @param name the refrigerated item's name
	 * @param cost the refrigerated item's cost
	 * @param price the refrigerated item's price
	 * @param reorderP the refrigerated item's reorder point
	 * @param reorderA the refrigerated item's reorder amount
	 * @param temp the refrigerated item's temperature
	 */
	public RefrigeratedItem(String name, int cost, int price, int reorderP, int reorderA, int temp) {
		super(name, cost, price, reorderP, reorderA);
		this.name = name;
		this.cost = cost;
		this.price = price;
		this.reorderP = reorderP;
		this.reorderA = reorderA;
		this.temp = temp;	
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



	/**
	 * @param temp the item's temperature
	 */
	public void setTemp(int temp) {
		this.temp = temp;
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
	public int temperature() {
		return this.temp;
	}
	
	@Override
	public String toString() {
		String string;
		string = "[Item Name: " + itemName() + "]" +
				"[Item Cost: " + itemCost() + "]" + 
				"[Item Price: " + itemPrice() + "]" + 
				"[Item Reorder Point: " + reorderPoint() + "]" +
				"[Item Reorder Amount: " + reorderAmount() + "]" +
				"[Item Temperature: " + temperature() + "]";
		return string;
	}

	
	
	
}
