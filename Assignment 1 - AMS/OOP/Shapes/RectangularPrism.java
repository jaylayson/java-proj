package oop.Shapes;

public class RectangularPrism implements Shape {

	private double w;
	private double h;
	private double l;
	static double recArea;
	static double recVolume;

	public RectangularPrism(double width, double height, double length) {

		this.w = width;
		this.h = height;
		this.l = length;

	}

	@Override
	public double volume() {
		recVolume = w * h * l;
		return recVolume;
	}

	@Override
	public double surfaceArea() {
		recArea = 2*( (w*l) + (h*l) + (h*w));

		return recArea;
	}

}
