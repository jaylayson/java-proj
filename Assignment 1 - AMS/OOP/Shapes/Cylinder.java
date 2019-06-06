package oop.Shapes;


public class Cylinder implements Shape {

	private double r;
	private double h;
	static double cylVolume;
	static double cylArea;

	public Cylinder(double radius, double height) {
		this.r = radius;
		this.h = height;

	}

	@Override
	public double volume() {
		cylVolume = Math.PI * Math.pow(r, 2) * h;
		return cylVolume;

	}

	@Override
	public double surfaceArea() {
		cylArea = (2 * Math.PI * r * h) + (2 * Math.PI * (Math.pow(r, 2)));
		return cylArea;
	}

}
