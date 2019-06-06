package patt.ReactorMonitoring;

import java.util.Observable;
import java.util.Random;

public class RadiationSensor extends Observable {
	private String location;
	private Random seed;
	private double radiation;

	/**
	 * Constructs a RadiationSensor object
	 *
	 * @param location.
	 *            An arbitrary location.
	 * @param seed.
	 *            A seed for the random number generator used to simulate radiation
	 *            readings.
	 */
	public RadiationSensor(String location, int seed) {
		this.seed = new Random(seed);
		this.location = location;
	}

	/**
	 * Gets the location
	 *
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Gets the radiation
	 *
	 * @return radiation
	 */


	public double getRadiation() {
		return radiation;
	}

	/**
	 * Updates radiation, changes the state to true, and notifies all observers of
	 * the change.
	 */
	public void readRadiation() {
		radiation = seed.nextDouble() * 10;	// generates random double based on seed from 0 to 10
		setChanged();
		notifyObservers(radiation);
	}
}
