package patt.Coffee;

import java.util.ArrayList;

import patt.Coffee.CoffeeFactory.Ingredient;
import patt.Coffee.CoffeeFactory.Type;

public class Coffee {
	Type type;
	double cost;
	ArrayList<Ingredient> ingredients;

	public Coffee(ArrayList<Ingredient> ingredients, Type type) {
		this.type = type;

		this.ingredients = ingredients;

		double sum = 0;
		for (Ingredient ingredient : ingredients) {
			if (ingredient == Ingredient.ESPRESSO) {
				sum += ingredient.getCost();
			} else if (ingredient == Ingredient.MILK) {
				sum += ingredient.getCost();
			} else if (ingredient == Ingredient.CHOCOLATE) {
				sum += ingredient.getCost();
			} else {
				sum += 0;
			}
		}
		this.cost = sum;

	}

	public double getCost() {
		return cost;
	}

	public double getPrice() {
		if (this.type.equals(Type.LONG_BLACK)) {
			return type.getPrice();
		} else if (this.type.equals(Type.FLAT_WHITE)) {
			return type.getPrice();
		} else if (this.type.equals(Type.MOCHA)) {
			return type.getPrice();
		}
		return 0;
	}

	public String listIngredients() {
		String string = "";
		for (Ingredient ingredient : ingredients) {
			string += ingredient;
			string += "\n";
		}
		return string;
	}
}
