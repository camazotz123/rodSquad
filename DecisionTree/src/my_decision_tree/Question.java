package my_decision_tree;

import java.text.DecimalFormatSymbols;

public class Question {
	String[] header = {"size", "color", "vertical bars", "teeth", "spotted", "age", "fish type"}; 
	// String[] header = { "color", "diameter", "label" }; // Header for test data
    int column;
    String true_val;
    float info_gained = 0;
 
    Question(int column, String true_val) {
        this.column = column;
        this.true_val = true_val; 
    }
    
    public String getValue() {
    	return true_val;
    }
   
    
    public float getGain() {
    	return info_gained;
    }
    
    public void setGain(float gain) {
    	this.info_gained = gain;
    }

    Boolean match(String[] row) { //one fish-data array
        String val = row[column]; //get value to compare from fish array
        
        if (isNumeric(val)) {
        	//USE TO TEST isNumeric
        	//System.out.println("Is numerical? " + isNumeric(val));
        	//System.out.println("val " + val);
        	//System.out.println("true_val " + true_val);
            return (Float.parseFloat(val) > Float.parseFloat(true_val));
        }
        else {
            return val.equals(true_val);
        }
        
    }
    
	boolean isNumeric(String str){
	    DecimalFormatSymbols symbol = DecimalFormatSymbols.getInstance();
	    boolean isDecimalSeparatorFound = false;
	    char decimal = symbol.getDecimalSeparator();

	    for (int i = 0; i < str.length(); i ++) {
	    	if (!Character.isDigit(str.charAt(i))) {
	    		if (str.charAt(i) == decimal && !isDecimalSeparatorFound)
	    			continue;
	    		
	    		return false;
	    	}
	    }
	    return true;
	}
        
    //Should just print the question at this node
    String print_question() {
        String condition = "==";
        if (isNumeric(true_val))
            condition = ">=";     
        
        return "Is " + header[column] + " " + condition + " " + true_val + "?";            
    }
}

