public class TreeNode {

	private int weight; // weight
	private char charValue;
	private double hursticeValue;
	private int x;
	private int y;
	private boolean isVisited;

	// Constructor
	public TreeNode(int x, int y, char charValue) {
		this.x = x;
		this.y = y;
		this.charValue = charValue;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public TreeNode getUp() {
		return Matrix.getUp(this);
	}

	public TreeNode getDown() {
		return Matrix.getDown(this);
	}

	public TreeNode getLeft() {
		return Matrix.getLeft(this);
	}

	public TreeNode getRight() {
		return Matrix.getRight(this);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public char getCharValue() {
		return charValue;
	}

	public void setCharValue(char charValue) {
		this.charValue = charValue;
	}
	public double getHursticeValue() {
		return hursticeValue;
	}

	public void setHursticeValue(double hursticeValue) {
		this.hursticeValue = hursticeValue;
	}

	public double getCompareValue() {
		// TODO Auto-generated method stub
		return this.hursticeValue + this.weight;
	}

	

}