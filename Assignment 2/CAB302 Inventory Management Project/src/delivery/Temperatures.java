/**
 * All individual objects package.
 */
package delivery;

/**
 * @author John Layson
 * Identifies each Individual Items' temperatures
 */
public enum Temperatures {
	fish(2),
	beef(3),
	yoghurt(3),
	milk(3),
	cheese(3),
	chicken(4),
	celery(8),
	asparagus(8),
	grapes(9),
	lettuce(10),
	tomatoes(10),
	mushrooms(10),
	ice(-10),
	frozenvegetablemix(-12),
	frozenmeat(-14),
	icecream(-20);
	
	private double value;
	

	/**
	 * @param Temp defines the value for the certain temperature
	 */
	Temperatures(double Temp) {
		value = Temp;
	}

	
	/**
	 * @return the value in double variable type
	 */
	public double getTemp() {
		return value;
	}
	
}
