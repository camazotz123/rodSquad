package my_decision_tree;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DecisionTree {
	String[] header = { "size", "color", "vertical bars", "teeth", "spotted", "age", "fish type" };

//===========+========================================================================================================

	// Finds the unique values for columns in a dataset
	String[] unique_vals(String[][] data, int col) {
		ArrayList<String> unique_vals = new ArrayList<String>();
		String[] values;

		// Test if values are already in unique_vals, add to AL if not
		for (int i = 0; i < data.length; i++) {
			if (unique_vals.contains(data[i][col]))
				continue;
			else
				unique_vals.add(data[i][col]);
		}

		// Copy ArrayList into a normal array to return
		values = new String[unique_vals.size()];
		for (int i = 0; i < unique_vals.size(); i++) {
			values[i] = unique_vals.get(i);
		}

		return values;
	}

//====================================================================================================================

	// Splits nested array into two smaller nested arrays
	// One nested array will contains rows with an attribute (column value) that
	// matches an input
	// The other nested array will contain the rows that do not match
	String[][] partition(boolean true_false, String[][] training_data, Question q) {
		Boolean truth = true_false;
		ArrayList<String[]> true_rows_AL = new ArrayList<String[]>();
		ArrayList<String[]> false_rows_AL = new ArrayList<String[]>();

		// Add to correct ArrayList (true or false rows)
		for (int row = 0; row < training_data.length; row++) {
			if (q.match(training_data[row]))
				true_rows_AL.add(training_data[row]);
			else
				false_rows_AL.add(training_data[row]);
		}

		// These statements will copy the ArrayLists into nested arrays
		String[][] true_rows = new String[true_rows_AL.size()][training_data[0].length];
		String[][] false_rows = new String[false_rows_AL.size()][training_data[0].length];
		for (int i = 0; i < true_rows_AL.size(); i++) {
			for (int j = 0; j < true_rows_AL.get(i).length; j++) {
				true_rows[i][j] = true_rows_AL.get(i)[j];
			}
		}
		for (int i = 0; i < false_rows_AL.size(); i++) {
			for (int j = 0; j < false_rows_AL.get(i).length; j++) {
				false_rows[i][j] = false_rows_AL.get(i)[j];
			}
		}

		if (truth == true)
			return true_rows;
		else
			return false_rows;
	}

//====================================================================================================================

	// For printing Strings
	void testPrint(String[] str) {
		System.out.print("Values: ");
		for (int i = 0; i < str.length; i++) {
			System.out.print(str[i]);
			if (i != str.length - 1)
				System.out.print(", ");
		}
		System.out.println();
	}

//====================================================================================================================

	float gini(String[][] data) {

		// Keep the index of both of these the same
		// labelCount will count the number of times the label at the same index occurs
		// in the nested array
		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<Integer> labelCount = new ArrayList<Integer>();
		float impurity = 1;
		int label = data[0].length - 1; // column number of label

		// we do the class_count method inline here to avoid using a HashMap
		for (int row = 0; row < data.length; row++) {
			if (!labels.contains(data[row][label])) { // if our list of labels does not contain the label yet, add it
				labels.add(data[row][label]);
				labelCount.add(1);
			} else { // else, increase the count that should line up with that
				int index = labels.indexOf(data[row][label]); // index of label already in AL
				int currentVal = labelCount.get(index); // Current # of times that label has shown up
				labelCount.set(index, currentVal + 1); // currentVal + 1
			}
		}

		// calculates impurity
		for (int i = 0; i < labels.size(); i++) {
			float prob_of_label = (float) labelCount.get(i) / data.length;
			impurity -= Math.pow(prob_of_label, 2);
		}

		return impurity;
	}

//====================================================================================================================

	float info_gain(String[][] true_rows, String[][] false_rows, float current_uncertainty) {
		String[][] left = true_rows;
		int numRowsLeft = true_rows.length;
		String[][] right = false_rows;
		int numRowsRight = false_rows.length;

		// p = # of rows on left / # of rows before partition
		float p = (float) (numRowsLeft) / (numRowsLeft + numRowsRight);

		// current uncertainty is gini(nested array before partition)
		return current_uncertainty - (p * gini(left)) - ((1 - p) * gini(right));
	}

//====================================================================================================================

	Question find_best_split(String[][] data_to_split) {
		String[][] data = data_to_split;
		float best_gain = 0;
		Question best_question = new Question(-1, "no value");

		float current_uncertainty = gini(data);
		int n_features = data[0].length - 1;

		for (int col = 0; col < n_features; col++) {
			// Populate AL with all possible values for each Q
			ArrayList<String> values = new ArrayList<String>();
			for (int row = 0; row < data.length; row++) {
				values.add(data[row][col]);
			}

			// test each value in ArrayList to find best info gain for that column
			for (int i = 0; i < values.size(); i++) {

				Question question = new Question(col, values.get(i));

				String[][] true_rows = partition(true, data, question);
				String[][] false_rows = partition(false, data, question);

				if (true_rows.length == 0 || false_rows.length == 0)
					continue;

				float gain = info_gain(true_rows, false_rows, current_uncertainty);

				if (gain > best_gain) {
					best_gain = gain;
					best_question = question;
				}
			}
		}

		// return best_question;
		best_question.setGain(best_gain);
		return best_question;
	}

//====================================================================================================================

	Node build_tree(String[][] rows, Node node) {
		float gain;
		Question question = find_best_split(rows);
		gain = question.getGain();

		if (gain == 0) {
			// If gain = 0, this node will be changed into a leaf
			node.setType(-2);
			int label = rows[0].length - 1;
			node.setLeafVal(rows[0][label]);
			node.setQuestion(question);
			//node.printRowData();
			//System.out.println();
		} else if (gain > 0) {
			// Left branch
			String[][] true_rows = partition(true, rows, question);
			Node left = new Node(-3, true_rows);
			left.setQuestion(find_best_split(true_rows));
			build_tree(true_rows, left);
			// System.out.println("Setting " + left.getValue() + " as a child of " +
			// node.getValue());
			node.setLeftChild(left);

			// Right branch
			String[][] false_rows = partition(false, rows, question);
			Node right = new Node(-3, false_rows);
			right.setQuestion(find_best_split(false_rows));
			build_tree(false_rows, right);
			// System.out.println("Setting " + right.getValue() + " as a child of " +
			// node.getValue());
			node.setRightChild(right);
		} else {
			System.out.println("Should never reach this point");
		}

//		if (node.type == -3) {
//			System.out.println("\ncurrent_node is of class Decision\nValue: " + node.getValue());
//		} else if (node.type == -2) {
//			System.out.println("\ncurrent_node is of class Leaf\nValue: " + node.getValue() + "\n");
//		} else
//			System.out.println("Something went wrong");

		return node;
	}

//====================================================================================================================	

	// Print inorder traversal
	void print_tree(Node node) {
		if (node.getLeftChild() != null) {
			print_tree(node.getLeftChild());
		}

		if (node.type == -2) {
			System.out.println("Leaf at: " + node.getValue());
		} else if (node.type == -3) {
			System.out.println("Decision: " + node.getValue());
		}

		if (node.getRightChild() != null) {
			print_tree(node.getRightChild());
		}
	}

//====================================================================================================================

	String classify(String[] row, Node node) {

		if (node.type == -1)
			return "Node class was never given a type";
		else if (node.type == -2) {
			return node.getValue(); // We eventually want this to return when we get to a leaf (this is our
									// prediction)
		} else if (node.type == -3) {
			if (node.getQuestion().match(row)) {
				return classify(row, node.getLeftChild());
			} else {
				return classify(row, node.getRightChild());
			}
		} else
			return "Something went wrong in clasify method";

	}

// ====================================================================================================================

	String getAccuracy(String[][] test_data, Node node, int total) {
		String[][] data;
		DecimalFormat df = new DecimalFormat("#.00");
		
		double correctClassifications = 0.0;
		int labelIndex = test_data[0].length - 1;
		
		//Create fish objects
		RandomFish[] fishList = new RandomFish[total];
		for (int j = 0; j < fishList.length; j++) {
			fishList[j] = new RandomFish();
		}

		//Fill Array with object traits
		data = new String[total][test_data[0].length];
		for (int i = 0; i < data.length; i++) {
			data[i] = fishList[i].createArray();
		}
		
		
		for (int i = 0; i < data.length; i++) {
			if (data[i][labelIndex].equals(classify(data[i], node))) {
				correctClassifications++;
			}
		}
		
		return  df.format(correctClassifications/total*100);
	}
	
// ====================================================================================================================

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] inputArray;
		int i = 0;

		System.out.println("*****************************\n");
		RandomFish[] fishList = new RandomFish[100];
		System.out.println("DATA FOR FISH IN MICHIGAN:\n");
		System.out.println("*****************************\n");
		for (int j = 0; j < fishList.length; j++) {
			fishList[j] = new RandomFish();
			fishList[j].print();
		}

		inputArray = new String[100][7];
		for (String[] u : inputArray) {
			u = fishList[i].createArray();
			inputArray[i] = u;
			i++;
		}

		DecisionTree myTree = new DecisionTree();
		Node root = new Node(-3, inputArray);
		root.setQuestion(myTree.find_best_split(inputArray));
		myTree.build_tree(inputArray, root);
		//myTree.print_tree(root);
		System.out.println("\n***********************************************************\n");
		System.out.println("OUR TESTING DATA (FISH THAT WE WILL ATTEMPT TO CLASSIFY:\n");
		System.out.println("***********************************************************\n");

		//largemouth bass
		String[] my_fish1 = {"14.2", "green", "No vertical Bars", "No teeth", "Not spotted", "23", "Largemouth Bass"};
		
		//yellow perch
		String[] my_fish2 = {"6.5", "yellow", "Vertical Bars", "No teeth", "Not spotted", "11", "Yellow Perch"};
		
		//lake sturgeon
		String[] my_fish3 = {"38.5", "brown", "No vertical Bars", "No teeth", "Not spotted", "152", "Lake Sturgeon"};
		
		//northern pike
		String[] my_fish4 = {"18", "green", "No vertical Bars", "Teeth", "Spotted", "30", "Northern Pike"};
		
		//brown bullhead
		String[] my_fish5 = {"15", "brown", "No vertical Bars", "No teeth", "Spotted", "9", "Brown Bullhead"};

		System.out.println("Fish 1:  [\"14.2\", \"green\", \"No vertical Bars\", \"No teeth\", \"Not spotted\", \"23\", \"Largemouth Bass\"]");
		System.out.println("PREDICTION: " + myTree.classify(my_fish1, root) + "   ACTUAL FISH: " + my_fish1[my_fish1.length - 1]);
		System.out.println("\nFish 2:  [\"6.5\", \"yellow\", \"Vertical Bars\", \"No teeth\", \"Not spotted\", \"11\", \"Yellow Perch\"]");
		System.out.println("PREDICTION: " + myTree.classify(my_fish2, root) + "   ACTUAL FISH: " + my_fish2[my_fish1.length - 1]);
		System.out.println("\nFish 3:  [\"38.5\", \"brown\", \"No vertical Bars\", \"No teeth\", \"Not spotted\", \"152\", \"Lake Sturgeon\"]");
		System.out.println("PREDICTION: " + myTree.classify(my_fish3, root) + "   ACTUAL FISH: " + my_fish3[my_fish1.length - 1]);
		System.out.println("\nFish 4:  [\"18\", \"green\", \"No vertical Bars\", \"Teeth\", \"Spotted\", \"30\", \"Northern Pike\"]");
		System.out.println("PREDICTION: " + myTree.classify(my_fish4, root) + "   ACTUAL FISH: " + my_fish4[my_fish1.length - 1]);
		System.out.println("\nFish 5:  [\"15\", \"brown\", \"No vertical Bars\", \"No teeth\", \"Spotted\", \"9\", \"Brown Bullhead\"]");
		System.out.println("PREDICTION: " + myTree.classify(my_fish5, root) + "   ACTUAL FISH: " + my_fish5[my_fish1.length - 1]);
		
		System.out.println("\nThis tree has a predicted accuracy of: " +myTree.getAccuracy(inputArray, root, 1000) + "%.");
	}
}

