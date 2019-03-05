package delivery;

/**
 * @author Nathan Thai
 * An Ordinary truck is a truck that contains only items that 
 * are not temperature controlled.
 */
public class OrdinaryTruck extends Trucks {
    
    private int itemQuantity;
    private double cost;
    private int capacity = 1000;
    private int truckQuantity;
    
    /**
     * Assigns int value to local itemQuantity variable
     * @param itemQuantity the quantity of items
     */
    public OrdinaryTruck(int itemQuantity)
    {
        this.itemQuantity = itemQuantity;
    }
    
    /**
     * This method is used to calculate the cost of a singular
     * ordinary truck. It uses the given argument quantity
     * which is then substituted into the formula.
     * The method then returns a double called "cost"
     * which represents the cost of a single ordinary truck.
     * @param quantity the quantity of items to be stored
     * @return the cost of a single ordinary truck.
     */
    public double calculatingCostO(int quantity)
    {            
            cost = 750 + (0.25 * quantity);
            return cost;
            
    }

    /**
     * @return OrdinaryTruck's quantity of items
     */
    public int getItemQuantity() 
    {
        return itemQuantity;
    }

    /**
     * @return OrdinaryTruck's truck capacity
     */
    public int getCapacity() 
    {
        return capacity;
    }

    /**
     * Assigns int value to local capacity variable
     * @param capacity the capacity of the truck
     */
    public void setCapacity(int capacity) 
    {
        this.capacity = capacity;
    }

    /**
     * @return number of Ordinary trucks
     */
    public int getTruckQuantity() 
    {
        return truckQuantity;
    }

    /**
     * Assigns int value to local truckQuantity variable
     * @param truckQuantity the quantity of trucks needed
     */
    public void setTruckQuantity(int truckQuantity) 
    {
        this.truckQuantity = truckQuantity;
    }

    @Override
    public int quantity() {
        return this.itemQuantity;
    }

    @Override
    public double temperature() {
        return 0;
    }




}