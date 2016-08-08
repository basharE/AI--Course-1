public class Matrix {

	private static TreeNode[][] matrix;
	private static int row;
	private static int column;
	 			

	public Matrix(TreeNode[][] matrix, int row, int column) {
		super();
		this.matrix = matrix;
		this.row = row;
		this.column = column;
	}

	public TreeNode[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(TreeNode[][] matrix) {
		this.matrix = matrix;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public static TreeNode getUp(TreeNode currentNode) {

		int currX = currentNode.getX();
		int currY = currentNode.getY();

		if (currY - 1 < 0) {
			return null;
		}

		return matrix[currX - 1][currY];
	}

	public static TreeNode getDown(TreeNode currentNode) {

		int currX = currentNode.getX();
		int currY = currentNode.getY();

		if (currX + 1 >= row) {
			return null;
		}

		return matrix[currX + 1][currY];
	}

	public static TreeNode getLeft(TreeNode currentNode) {

		int currX = currentNode.getX();
		int currY = currentNode.getY();

		if (currX - 1 < 0) {
			return null;
		}

		return matrix[currX][currY - 1];
	}

	public static TreeNode getRight(TreeNode currentNode) {

		int currX = currentNode.getX();
		int currY = currentNode.getY();

		if (currY + 1 >= column) {
			return null;
		}

		return matrix[currX][currY+ 1];
	}
	
	

}
