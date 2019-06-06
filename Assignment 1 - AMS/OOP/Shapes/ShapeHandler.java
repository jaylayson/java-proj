package oop.Shapes;

public class ShapeHandler {
	// Returns the sum of the volumes of the given shapes.
	public static double volumeSum(Shape[] shapes) {
		double sum = 0;

		sum = RectangularPrism.recVolume + Sphere.sphvolume + Cylinder.cylVolume;

		return sum;
	}

	// Returns the sum of the surface areas of the given shapes.
	public static double surfaceAreaSum(Shape[] shapes) {
		double sum2 = 0;

		sum2 = RectangularPrism.recArea + Sphere.sphArea + Cylinder.cylArea;

		return sum2;
	}
}
