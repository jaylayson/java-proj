package oop.Shapes;

public class Sphere implements Shape {

	double r;
	static double sphArea;
	static double sphvolume;

	public Sphere(double radius) {
		this.r = radius;
	}

	@Override
	public double volume() {

		sphvolume =  (4  * Math.PI * Math.pow(r, 3)) / 3;

		return sphvolume;
	}

	@Override
	public double surfaceArea() {
		sphArea = 4 * Math.PI * Math.pow(r, 2);
		return sphArea;
	}



}
