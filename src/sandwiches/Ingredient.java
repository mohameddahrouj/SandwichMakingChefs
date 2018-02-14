package sandwiches;

/**
 * Enumerated class that contains the ingredients of a sandwich
 * @author Mohamed Dahrouj
 *
 */
public enum Ingredient {

	  BREAD {
	    @Override
	    public String toString() {
	      return "Bread";
	    }
	  },
	  PEANUTBUTTER {
	    @Override
	    public String toString() {
	      return "Peanut Butter";
	    }
	  },
	  JAM {
	    @Override
	    public String toString() {
	      return "Jam";
	    }
	  }

	}
