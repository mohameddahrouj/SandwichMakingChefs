package sandwiches;

import java.util.Arrays;
import java.util.List;

/**
 * Class contains the resources shared between the Agent, Chef and Table
 * @author Mohamed Dahrouj
 * 
 */
public class Sandwiches {
	//Number of sandwiches to be created and eaten
	public static final int SANDWICHES = 20;
	//List of ingredients that make up a sandwich
	public static final List<Ingredient> INGREDIENTS = Arrays.asList(Ingredient.BREAD, Ingredient.PEANUTBUTTER, Ingredient.JAM);
	
	//Track the total sandwich count number
	public static int counter = 1;
	public static void incrementCounter() {
		counter++;
	}
	
}
