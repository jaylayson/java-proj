package patt.ReactorMonitoring;

import java.util.ArrayList;
import java.util.Observable;

public class ResearchCentre extends RadiationMonitor {
	private ArrayList<Double> as = new ArrayList<Double>();
	double sumOfList = 0;
	/**
	 * Constructs a ResearchCentre object, which observes reactor radiation readings
	 * and constantly prints a report with the current moving average of the
	 * recorded observations.
	 *
	 * @param location.
	 *            An arbitrary location.
	 */
	public ResearchCentre(String location) {
		super(location);
	}

	/**
	 * Updates the monitor with a new observation and prints a report.
	 */
	public void update(Observable subject, Object o) {
		as.add((Double.parseDouble(o.toString())));			// parses 'o' into double and adds to list
		System.out.printf("%s\n", generateReport());
	}

	/**
	 * Generates a report of the current moving average, updated by a new
	 * observation. The moving average is calculated by summing all observations
	 * made so far, and dividing by the quantity of observations so far.
	 */
	public String generateReport() {
		double zeroAvg = 0.0;
		if(as.size() <= 0) {
			return super.now() + " :: moving average :: " + ((double)Math.round((sumOfList / zeroAvg) * 10000) / 10000) +
					" :: " + super.getLocation();
		} else {
		sumOfList += as.get(as.size() - 1);
		return super.now() + " :: moving average :: " + ((double)Math.round((sumOfList / (double)as.size()) * 10000) / 10000) +
				" :: " + super.getLocation();
		}
	}
}
