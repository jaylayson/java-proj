package coll.Movies;

import java.util.ArrayList;
import java.util.List;

public class Movie {
	private String title;
	private int year;
	private List<Integer> ratings = new ArrayList<>();

	/**
	 * Constructor. Construct a new movie object.
	 * @param title
	 * @param year
	 */
	public Movie(String title, int year) {
		this.title = title;
		this.year = year;
	}

	/**
	 * Rate the movie.
	 * @param rating
	 */
	public void addRating(int rating) {
		this.ratings.add(rating);
	}

	/**
	 * Get the title of the movie.
	 * @return the title of the movie as a String.
	 */
	public String title() {
		return title;
	}

	/**
	 * Get the year of the movie.
	 * @return the year of the movie as an int.
	 */
	public int year() {
		return year;
	}

	/**
	 * Get the average rating of the movie.
	 * @return the average rating of the movie as a double.
	 */
	double sum = 0;
	double average = 0;
	public double rating() {
		for(double r : ratings) {
			sum += r;
			average = sum / ratings.size();
		}
		return average;
	}

}
