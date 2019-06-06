package patt.Coffee;

import java.util.ArrayList;

public class CoffeeFactory {

	public static enum Type {
		LONG_BLACK(4.0),
		FLAT_WHITE(4.75),
		MOCHA(5.5);

		private double price;

		Type(double price) {
			this.price = price;
		}

		public double getPrice() {
			return price;
		}
	}

	public static enum Ingredient {
		ESPRESSO(0.5),
		MILK(1),
		CHOCOLATE(1.5);

		private double cost;

		Ingredient(double cost) {
			this.cost = cost;
		}

		public double getCost() {
			return cost;
		}
	}

	static ArrayList<Ingredient> temp = new ArrayList<>();
	public static Coffee CreateCoffee(Type type) {

	        switch(type){

	            case LONG_BLACK:
	            	temp.clear();
	            	temp.add(Ingredient.ESPRESSO);
	            	temp.add(Ingredient.ESPRESSO);
	            	return new Coffee(temp, type);

	            case FLAT_WHITE:
	            	temp.clear();
	            	temp.add(Ingredient.ESPRESSO);
	            	temp.add(Ingredient.MILK);
	            	return new Coffee(temp, type);

	            case MOCHA:
	            	temp.clear();
	            	temp.add(Ingredient.ESPRESSO);
	            	temp.add(Ingredient.MILK);
	            	temp.add(Ingredient.CHOCOLATE);
	            	return new Coffee(temp, type);

	    }
			return null;
	}
}
