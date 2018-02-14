package sandwiches;

import java.util.*;

/**
 * The agent randomly selects two of the ingredients and places them on a table
 * @author Mohamed Dahrouj
 *
 */
public class Table {
	//Available Ingredients that are immutable and thread safe
	private Set<Ingredient> ingredients = Collections.synchronizedSet(new HashSet<Ingredient>());
	//Flags
	private boolean isEdibleYet = false;
	private boolean signalChef = false;
	private boolean signalAgent = true;
	
	/**
	 * Returns true if table currently missing specified ingredient, otherwise false
	 */
	private boolean tableDoesNotHaveIngredient(Ingredient ingredient) {
		Iterator<Ingredient> iter = ingredients.iterator();
		while (iter.hasNext()) {
			Ingredient nextIngredient = iter.next();
			if (ingredient.equals(nextIngredient)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds ingredient to table and sets flags
	 */
	public void addIngredient(Ingredient ingredient) {
		if (tableDoesNotHaveIngredient(ingredient)) {
			ingredients.add(ingredient);
		}
		//All ingredients must be present on table for a chef to make a sandwich
		if (ingredients.size() == Sandwiches.INGREDIENTS.size()) {
			isEdibleYet = true;
		}
		//When the agent puts out both ingredients on table we signal the chef
		if (ingredients.size() == 2) {
			signalChef = true;
			signalAgent = false;
		}
		
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
	
	/**
	 * Chef eats the sandwich then all flags reset
	 */
	public void eatSandwich() {
		ingredients.clear();
		resetFlags();
	}
	
	private void resetFlags(){
		isEdibleYet = false;
		signalChef = false;
		signalAgent = true;
	}

	public boolean isEdible() {
		return isEdibleYet;
	}

	public boolean isSignalChef() {
		return signalChef;
	}

	public boolean isSignalAgent() {
		return signalAgent;
	}
}
