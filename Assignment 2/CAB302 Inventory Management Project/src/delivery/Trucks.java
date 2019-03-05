package delivery;

/**
 * @author Nathan Thai
 * Trucks have a quantity value or temperature.
 */
public abstract class Trucks {
    
    public Trucks()
    {
    }
    
    /**
     * @return truck quantity
     */
    public abstract int quantity();
    
    /**
     * @return truck temperature
     */
    public abstract double temperature();
}