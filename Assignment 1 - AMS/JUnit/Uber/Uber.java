package junit.Uber;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Uber {


	String name;
	String carModel;
	Uber(String name, String carModel){
		this.name = name;
		this.carModel = carModel;

	}

	public String getDriverName(){
		return name;
	}

	public String getCarModel() {
		return carModel;
	}

	public static double FareRate;
	public static void setFareRate( double newFareRate) {
		FareRate = newFareRate;
	}

	public static double getFareRate() {
		return FareRate;
	}

	Instant pickup;
	public void pickupPassenger() {

		this.pickup = Instant.now();
	}

	public double setdownPassenger() {
		Instant setdown;
		double gap;
		double cost;

		setdown = Instant.now();
		gap = ChronoUnit.MILLIS.between(pickup, setdown) / 1000d;
		cost = FareRate * gap * surge;

		return cost;

	}

	double surge = 1;
	public double setSurgeMultiplier(double surgeMultiplier) {
		this.surge = surgeMultiplier;

		return surge;
	}


}
