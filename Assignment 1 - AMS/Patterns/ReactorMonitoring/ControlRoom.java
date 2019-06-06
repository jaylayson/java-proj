package patt.ReactorMonitoring;

import java.util.Observable;

public class ControlRoom extends RadiationMonitor {
	private double warningThreshold;

	/**
	 * Constructs a ControlRoom object, which observes reactor radiation readings
	 * and prints reports if the radiation exceeds a threshold.
	 *
	 * @param location.
	 *            An arbitrary location.
	 * @param warningThreshold.
	 *            The radiation threshold for when reports should be printed.
	 */
	public ControlRoom(String location, double warningThreshold) {
		super(location);
		this.warningThreshold = warningThreshold;
	}

	/**
	 * Updates the monitor with a new observation and prints a report if and only if
	 * the observation is equal to or greater than the warning threshold.
	 */
	public void update(Observable subject, Object o) {
		double d = Double.valueOf((o.toString())).doubleValue();	// grabs value and converts into double
		if (d >= warningThreshold) {
			System.out.printf("%s %.4f %s %s\n", generateReport(), d, "::", super.getLocation());

		}
	}
	/**
	 * Generates a report based on the current observation.
	 */
	@Override
	public String generateReport() {
		return super.now() + " :: WARNING ::";
	}
}
