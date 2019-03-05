/**
 * All individual objects package.
 */
package store;

/**
 * @author John Layson
 * Store is an object to represent Super Mart.
 * A Supermarket chain that sells a range of fresh produce, 
 * meats, frozen food, and dry goods.
 * 
 */
public class Store {
	private static double capital = 100000;
	final static String name = "Super Mart";
	private static Store instance = new Store();

	
	/**
	 * Store's private constructor
	 */
	private Store() {
		super();		
	}

	
	/**
	 * @return an instance of Store
	 */
	public static Store getInstance(){
		return instance;
	}
	

	/**
	 * @return the instantiated Store's capital
	 */
	public double getCapital() {
		return capital;
	}

	/**
	 * @param capital store's desired/calculated value
	 */
	public void setCapital(double capital) {
		Store.capital = capital;
	}
	

	/**
	 * @return the instantiated Store's name
	 */
	public static String getName() {
		return name;
	}
}
