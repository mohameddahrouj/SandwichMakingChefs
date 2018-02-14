package sandwiches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextArea;

/**
 * The agent has a table and is responsible for placing random ingredients on the table for chefs to take
 * @author Mohamed Dahrouj
 *
 */
public class Agent extends Thread{
	//JTextArea used in Runner
	private JTextArea transcript;
	//Table the Agent is putting ingredients on
	private Table table;
	
	public Agent(Table table, JTextArea transcript) {
		this.table = table;
		this.transcript = transcript;
	}
	
	/**
	 * The agent randomly puts out two of the three ingredients
	 */
	public List<Ingredient> randomizeIngredients() {
	    List<Ingredient> copy = new ArrayList<Ingredient>(Sandwiches.INGREDIENTS);
	    Collections.shuffle(copy);
	    //Grabs the 2 ingredients at random
	    return copy.subList(0, 2);
	}
	
	@Override
	public void run() {
		System.out.println("Agent " + Thread.currentThread().getName() + " started.");
		transcript.append("Agent " + Thread.currentThread().getName() + " started.\n");
		while(Sandwiches.counter <= Sandwiches.SANDWICHES) {//Supply ingredients to all sandwiches
			System.out.println("\n"+Thread.currentThread().getName() + " grabbing ingredients for sandwich #"+Sandwiches.counter+".");
			transcript.append("\n"+Thread.currentThread().getName() + " grabbing ingredients for sandwich #"+Sandwiches.counter+".\n");
			List<Ingredient> randomizedIngredients = randomizeIngredients();
			for(int i = 0; i < randomizedIngredients.size(); i++) {
	            Ingredient ingredient = randomizedIngredients.get(i);
	            synchronized (table) {//Synchronized block on table so that only one chef can access the two ingredients
	                while (table.isEdible() || !table.isSignalAgent()) {
	                    try {
	                    	table.wait();
	                    } catch (InterruptedException e) {
	                        return;
	                    }
	                }    
	                //Chefs signal if ready for agent
	                if(table.isSignalAgent()) {
	                	table.addIngredient(ingredient);
	                	System.out.println(Thread.currentThread().getName() + " added " + ingredient + " to table.");
	                	transcript.append(Thread.currentThread().getName() + " added " + ingredient + " to table.\n");
	                	//Notify all chefs that ingredients have been added to table
	                	table.notifyAll();
	                }
	            }
	            try {
	                Thread.sleep(750); //Slow things down
	            } catch (InterruptedException e) {}
	        }
			Sandwiches.incrementCounter();
		}
	}
}
