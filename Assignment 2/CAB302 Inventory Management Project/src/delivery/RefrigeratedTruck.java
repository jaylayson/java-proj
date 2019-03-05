package delivery;

/**
 * @author Nathan Thai
 * A Refrigerated truck is a truck that contains items the must
 * be temperature controlled. This truck may also contain items
 * that do not require temperature control.
 */
public class RefrigeratedTruck extends Trucks {
    
    private double cost;
    int capacity = 800;
    private double temperature;
    int quantity;

    /**
     * Assigns given double value to local temperature variable
     * @param temperature the temperature of the truck
     */
    public RefrigeratedTruck(double temperature) 
    {
        this.temperature = temperature;
    }

    /**
     * Calculates the cost of a refrigerated truck
     * using the given temperature argument
     * The argument value is then substituted into 
     * the formula
     * @param temperature the given required temperature of the truck
     * @return the cost of a single refrigerated truck
     */
    public double calculatingCostR (double temperature)
    {
        cost = 900 + 200 * Math.pow(0.7, temperature / 5);
        
        return cost;
    }

    @Override
    public int quantity() 
    {
        return this.quantity;
    }

    @Override
    public double temperature() 
    {
        return temperature;
    }

    /**
     * @return RefrigeratedTruck capacity
     */
    public int getCapacity() 
    {
        return capacity;
    }

    /**
     * Assigns given int value to local capacity variable
     * @param capacity the capacity of the truck
     */
    public void setCapacity(int capacity) 
    {
        this.capacity = capacity;
    }

    /**
     * Assigns given int value to local quantity variable
     * @param quantity the quantity of items
     */
    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }
}