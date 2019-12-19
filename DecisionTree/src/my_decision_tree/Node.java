package my_decision_tree;

public class Node {
	// ---------------------------------- data field
	private Node parent;
	private Node leftChild;
	private Node rightChild;
	public int type = -1; //type = -2 for decision, -3 for node
	String[][] data;
	Question question; //Should be null unless node is a Decision
	String decVal; //Value if node is decision
	String leafVal; //Value is node is leaf

	// ---------------------------------- method field
	// --------------------- constructors
	public Node(int type, String[][] current_data) {
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.type = type;
		this.data = current_data;
	}

	public String getValue() {
		if (type == -3)
			return question.getValue();
		else if (type == -2)
			return leafVal;
		else
			return "getValue() method in Node class error";
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setType(int t) {
		type = t;
	}
	
    public String getDecVal() {
		return decVal;
	}

	public void setDecVal(String decVal) {
		this.decVal = decVal;
	}

	public String getLeafVal() {
		return leafVal;
	}

	public void setLeafVal(String leafVal) {
		this.leafVal = leafVal;
	}

	public String getType() {
    	if (type == -2) {
    		return "Leaf";
    	} else if (type == -3)
    		return "Decision";
    	else
    		return "Type was never assigned";
    }

	public void setQuestion(Question question) {
		this.question = question;
		this.decVal = question.getValue();
	}
	
	public Node getLeftChild() {
		return this.leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
		if (leftChild != null) {
			this.leftChild.setParent(this); //
		}
	}

	public Node getRightChild() {
		return this.rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
		if (rightChild != null) {
			this.rightChild.setParent(this);
		}
	}

	public Node getParent() {
		return this.parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public boolean isLeftChild() {
		if(this.parent == null) {
			System.out.println("It is the root");
			return false;
		} 
		if (this.getParent().getLeftChild() == null) {
			return false;
		}
		else if (this.getParent().getLeftChild() != this) {
			return false;
		}
		else {
			return true;
		}
	}

}

