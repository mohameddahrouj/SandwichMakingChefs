package sandwiches;

import java.util.Set;

import javax.swing.JTextArea;

/**
 * Chef is on a table and has a single ingredient at any given time
 * 
 * @author Mohamed Dahrouj
 *
 */
public class Chef extends Thread {
	private Table table;
	// Chef can only have infinite supply of one ingredient
	private Ingredient ingredient;
	// JTextArea for transcript in Runner
	private JTextArea transcript;

	public Chef(Table table, Ingredient ingredient, JTextArea transcript) {
		this.table = table;
		this.ingredient = ingredient;
		this.transcript = transcript;
	}
	
	/**
	 * Returns true if ingredient missing from table including chefs infinite supply otherwise, false
	 */
	private boolean isMissingIngredient(Table table) {
		Set<Ingredient> ingredientsOnTable = table.getIngredients();
		ingredientsOnTable.add(ingredient);
		//Checks to see if all the required ingredients to form a sandwich are available
		return ingredientsOnTable.containsAll(Sandwiches.INGREDIENTS);
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " started.");
		transcript.append(Thread.currentThread().getName() + " started.\n");
		while (Sandwiches.counter <= Sandwiches.SANDWICHES) {
			synchronized (table) {
				while ((!table.isEdible() && !table.isSignalChef()) || !isMissingIngredient(table)) {
					try {// Use while idiom to wait
						table.wait();
					} catch (InterruptedException e) {
						return;
					}
				}
				//Chef adds their infinite ingredient and eats the sandwich
				if (isMissingIngredient(table) && table.isSignalChef()) {
					//Chefs add their ingredient to the table
					table.addIngredient(ingredient);
					System.out.println(Thread.currentThread().getName() + " added " + ingredient + " to sandwich #" + Sandwiches.counter + ".");
					transcript.append(Thread.currentThread().getName() + " added " + ingredient + " to sandwich #" + Sandwiches.counter + ".\n");
					table.eatSandwich();
					System.out.println(Thread.currentThread().getName() + " ate sandwich #" + Sandwiches.counter + "!");
					transcript.append(Thread.currentThread().getName() + " ate sandwich #" + Sandwiches.counter + "!\n\n");
				}
				table.notifyAll();// Notify all when sandwich is edible and ready for chef or if simply missing ingredient
			}

			try {
				Thread.sleep(200); // Slow things down
			} catch (InterruptedException e) {
			}
		}
	}
}
