import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Search {

	private static int matrixY;
	private static int matrixX;
	private static LinkedList<TreeNode> slnList = new LinkedList<TreeNode>();
	private static boolean firstHreostic = false;
	private static int visited = 0;
	

	public static void main(String[] args) throws Exception {
		if (args.length == 2){
		
		
		String mazeName = args[0];
		String algoType = args[1];
		TreeNode[][] matrix = buildMatrix(mazeName).getMatrix();
		TreeNode startCoor = getStartCoordinate(matrix);
		TreeNode goalCoor = getGoalCoordinate(matrix);
		TreeNode startNode = matrix[startCoor.getX()][startCoor.getY()]; 
		TreeNode goalNode = matrix[goalCoor.getX()][goalCoor.getY()]; 			
		
		
		int res= 0;
		switch  (algoType){
		case "u": 
			res = search(startNode);
						
			break;
		case "a1":			
			res = aStar(startNode,goalNode);
			break;		
		case "a2":
			firstHreostic = false;
			res = aStar(startNode,goalNode);
			break;
		case "b1":
			res = bfs(startNode,goalNode);
						
			break;
		case "b2":
			firstHreostic = false;
			res = bfs(startNode,goalNode);					
			break;
		 default:
			System.out.println("Undifined Agorithm Type!!");
			return;
	 
		}
		if (res != 0){
		PrintMatrix(matrix, matrixX, matrixY, slnList );
		System.out.println(visited + " Nodes Were Visited");
		System.out.println("Path Was Found With = " + res + " Weight");
		}
		else 
			{
			System.out.println(visited + " Nodes Were Visited");
			System.out.println("No Path Was Found");
			
			}
		}
		else {
			System.out.println("Please Enter Both Map And Algorithm Type!!");
			return;	
			}
		
																			

	}
	private static int search(TreeNode startNode) {
		boolean flag = true;		
		PriorityQueue<TreeNode> queue = new PriorityQueue<TreeNode>(
				new Comparator<TreeNode>() {

					@Override
					public int compare(TreeNode o1, TreeNode o2) {
						return Integer.compare(o1.getWeight(), o2.getWeight());
					}
				});

		queue.add(startNode);
		slnList.add(startNode);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			node.setVisited(true);
			int charValue = node.getCharValue();		
			if (charValue == 'g') {			
				return node.getWeight();
			}			
			if ((node.getUp() != null) && (!node.getUp().isVisited())) {	
				flag = true;
				visited ++;
				if (queue.contains(node.getUp()))
				{
					if (node.getUp().getWeight() <= (node.getWeight() + (node.getUp().getCharValue() == ',' ? 2 : 1))){
										
					}
					else{
						node.getUp().setWeight(node.getWeight() + (node.getUp().getCharValue() == ',' ? 2 : 1));
						slnList.add(node);
					}
				}
				else
				{
					node.getUp().setWeight(node.getWeight() + (node.getUp().getCharValue() == ',' ? 2 : 1));
					queue.add(node.getUp());
					slnList.add(node);
				}				
				
			}

			if ((node.getRight() != null) && (!node.getRight().isVisited())) {
				flag = true;
				visited ++;
				if (queue.contains(node.getRight()))
				{
					if (node.getRight().getWeight() <= (node.getWeight() + (node.getRight().getCharValue() == ',' ? 2 : 1))){				
					
					}
					else{
						node.getRight().setWeight(node.getWeight() + (node.getRight().getCharValue() == ',' ? 2 : 1));
						slnList.add(node);
					}
				}
				else
				{
					node.getRight().setWeight(node.getWeight() + (node.getRight().getCharValue() == ',' ? 2 : 1));
					queue.add(node.getRight());
					slnList.add(node);
				
					
				}														
			}

			if ((node.getDown() != null) && (!node.getDown().isVisited())) {
				flag = true;
				visited ++;
				if (queue.contains(node.getDown()))
				{
					if (node.getDown().getWeight() <= (node.getWeight() + (node.getDown().getCharValue() == ',' ? 2 : 1))){
										
					}
					else{
						node.getDown().setWeight(node.getWeight() + (node.getDown().getCharValue() == ',' ? 2 : 1));
						slnList.add(node);
					}
				}
				else
				{
					node.getDown().setWeight(node.getWeight() + (node.getDown().getCharValue() == ',' ? 2 : 1));
					queue.add(node.getDown());
					slnList.add(node);
				
				}				
				
			}

			if ((node.getLeft() != null) && (!node.getLeft().isVisited())) {
				flag = true;
				visited ++;
				if (queue.contains(node.getLeft()))
				{
					if (node.getLeft().getWeight() <= (node.getWeight() + (node.getLeft().getCharValue() == ',' ? 2 : 1))){					
					
					}
					else{
						node.getLeft().setWeight(node.getWeight() + (node.getLeft().getCharValue() == ',' ? 2 : 1));
						slnList.add(node);
					}
				}
				else
				{
					node.getLeft().setWeight(node.getWeight() + (node.getLeft().getCharValue() == ',' ? 2 : 1));
					queue.add(node.getLeft());
					slnList.add(node);
			
				
				}				
				
			}
			if (flag == false)				
				slnList.remove(node);
		}
		return 0;
	}
	private static int aStar(TreeNode startNode, TreeNode goalNode){
		boolean flag = true;		
		PriorityQueue<TreeNode> queue = new PriorityQueue<TreeNode>(
				new Comparator<TreeNode>() {
					@Override
					public int compare(TreeNode o1, TreeNode o2) {
						return Double.compare(o1.getCompareValue(), o2.getCompareValue());
					}
				});
		queue.add(startNode);
		slnList.add(startNode);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();			
			node.setVisited(true);
			int charValue = node.getCharValue();			
			if (charValue == 'g') {	
				slnList.add(node);
				return node.getWeight();
			}														
			if ((node.getUp() != null) && (!node.getUp().isVisited())) {
				visited ++;
				flag = true;
				if (queue.contains(node.getUp()))
				{
					if (node.getUp().getHursticeValue() + node.getUp().getWeight() <= (node.getHursticeValue() +  (node.getUp().getCharValue() == ',' ? 2 : 1))){
					
					}
					else{
						node.getUp().setWeight(node.getWeight() + (node.getUp().getCharValue() == ',' ? 2 : 1));					
						node.getUp().setHursticeValue(b1(node.getUp(),goalNode));
						slnList.add(node);					
					}
				}
				else
				{				
					node.getUp().setWeight(node.getWeight() + (node.getUp().getCharValue() == ',' ? 2 : 1));					
					node.getUp().setHursticeValue(b1(node.getUp(),goalNode));							
					queue.add(node.getUp());
					slnList.add(node);				
				}													
			}
			if ((node.getRight() != null) && (!node.getRight().isVisited())){
				visited ++;
				flag = true;
				if (queue.contains(node.getUp()))
				{
					if (node.getRight().getHursticeValue() + node.getRight().getWeight() <= (node.getHursticeValue() +  (node.getRight().getCharValue() == ',' ? 2 : 1))){				
					}
					else{
						node.getRight().setWeight(node.getWeight() + (node.getRight().getCharValue() == ',' ? 2 : 1));					
						node.getRight().setHursticeValue(b1(node.getRight(),goalNode));		
						slnList.add(node);						
					}
				}
				else
				{				
					node.getRight().setWeight(node.getWeight() + (node.getRight().getCharValue() == ',' ? 2 : 1));					
					node.getRight().setHursticeValue(b1(node.getRight(),goalNode));							
					queue.add(node.getRight());
					slnList.add(node);			
				}													
			}
			if ((node.getDown() != null) && (!node.getDown().isVisited())) {
				visited ++;
				flag = true;
				if (queue.contains(node.getDown()))
				{
					if (node.getDown().getHursticeValue() + node.getDown().getWeight() <= (node.getHursticeValue() +  (node.getDown().getCharValue() == ',' ? 2 : 1))){
					
					}
					else
					{
						node.getDown().setWeight(node.getWeight() + (node.getDown().getCharValue() == ',' ? 2 : 1));					
						node.getDown().setHursticeValue(b1(node.getDown(),goalNode));
						slnList.add(node);
					}
				}
				else
				{				
					node.getDown().setWeight(node.getWeight() + (node.getDown().getCharValue() == ',' ? 2 : 1));					
					node.getDown().setHursticeValue(b1(node.getDown(),goalNode));							
					queue.add(node.getDown());
					slnList.add(node);
		
				}										
			}
			if ((node.getLeft() != null) && (!node.getLeft().isVisited())) {
				visited ++;
				flag = true;
				if (queue.contains(node.getLeft()))
				{
					if (node.getLeft().getHursticeValue() + node.getLeft().getWeight() <= (node.getHursticeValue() +  (node.getLeft().getCharValue() == ',' ? 2 : 1))){
					
					}
					else{
						node.getLeft().setWeight(node.getWeight() + (node.getLeft().getCharValue() == ',' ? 2 : 1));					
						node.getLeft().setHursticeValue(b1(node.getLeft(),goalNode));			
						slnList.add(node);
					}
				}
				else
				{				
					node.getLeft().setWeight(node.getWeight() + (node.getLeft().getCharValue() == ',' ? 2 : 1));					
					node.getLeft().setHursticeValue(b1(node.getLeft(),goalNode));							
					queue.add(node.getLeft());
					slnList.add(node);
				}			
			}
			if (flag == false)
				slnList.remove(node);
		}
		return 0;
	}
	
	
	
	private static  void PrintMatrix(TreeNode[][] matrix, int y, int x, List<TreeNode> slnList2){
		for (TreeNode tn : slnList2) {
			if ((matrix[tn.getX()][tn.getY()].getCharValue() != 'g') && (matrix[tn.getX()][tn.getY()].getCharValue() != 's')) 
				matrix[tn.getX()][tn.getY()].setCharValue('x');
		} 		
		for (int i = 0; i < x; i++) {
		    for (int j = 0; j < y; j++) {
		    	if (matrix[i][j]  == null){
		    		System.out.print( "#"+ " ");
		    	}
		    	else
		        System.out.print(matrix[i][j].getCharValue() + " ");
		    }
		    System.out.print("\n");
		}		
	}
	//mission 2
	

	private static int bfs(TreeNode startNode, TreeNode goalNode){
		PriorityQueue<TreeNode> queue = new PriorityQueue<TreeNode>(
				new Comparator<TreeNode>() {

					@Override
					public int compare(TreeNode o1, TreeNode o2) {
						return Double.compare(o1.getHursticeValue(), o2.getHursticeValue());
					}
				});
		queue.add(startNode);
		slnList.add(startNode);
		while (!queue.isEmpty()) {

			TreeNode node = queue.poll();
			node.setVisited(true);
			double huristicGoal = b1(node,goalNode);

			if (huristicGoal == 0.0) {
				slnList.add(node);
				return node.getWeight();
			}
						
			boolean flag = false;
			if ((node.getUp() != null) && (!node.getUp().isVisited())) {
				visited ++;
				flag  = true;
				if (queue.contains(node.getUp()))
				{
					if (node.getUp().getHursticeValue() <= (node.getHursticeValue() + 1)){
					
					}
					else{
						node.getUp().setWeight(node.getWeight() + 1);
						node.getUp().setHursticeValue(b1(node.getUp(),goalNode));			
						slnList.add(node);
					}
				}
				else
				{				
					node.getUp().setWeight(node.getWeight() + 1);
					node.getUp().setHursticeValue(b1(node.getUp(),goalNode));					
					queue.add(node.getUp());
					slnList.add(node);
				}
				
				
			}

			if ((node.getRight() != null) && (!node.getRight().isVisited())) {
				visited ++;
				flag = true;
				if (queue.contains(node.getRight()))
				{
					if (node.getRight().getHursticeValue() <= (node.getHursticeValue() + 1)){
						
					}
					else{
						node.getRight().setWeight(node.getWeight() + 1);
						node.getRight().setHursticeValue(b1(node.getRight(),goalNode));	
						slnList.add(node);
						
					}
				}
				else
				{				
					node.getRight().setWeight(node.getWeight() + 1);
					node.getRight().setHursticeValue(b1(node.getRight(),goalNode));					
					queue.add(node.getRight());
					slnList.add(node);
				}
				
														
			}

			if ((node.getDown() != null) && (!node.getDown().isVisited())) {
				visited ++;
				flag = true;
				if (queue.contains(node.getDown()))
				{
					if (node.getDown().getHursticeValue() <= (node.getHursticeValue() + 1)){
						
					}
					else{
						node.getDown().setWeight(node.getWeight() + 1);
						node.getDown().setHursticeValue(b1(node.getDown(),goalNode));	
						slnList.add(node);
						
					}
				}
				else
				{				
					node.getDown().setWeight(node.getWeight() + 1);
					node.getDown().setHursticeValue(b1(node.getDown(),goalNode));					
					queue.add(node.getDown());
					slnList.add(node);
				}
				
				
			}

			if ((node.getLeft() != null) && (!node.getLeft().isVisited())) {
				visited ++;
				flag = true;
				if (queue.contains(node.getLeft()))
				{
					if (node.getLeft().getHursticeValue() <= (node.getHursticeValue() + 1)){
						
					}
					else{
						node.getLeft().setWeight(node.getWeight() + 1);
						node.getLeft().setHursticeValue(b1(node.getLeft(),goalNode));	
						slnList.add(node);
						
					}
				}
				else
				{				
					node.getLeft().setWeight(node.getWeight() + 1);
					node.getLeft().setHursticeValue(b1(node.getLeft(),goalNode));					
					queue.add(node.getLeft());
					slnList.add(node);
				}
			}
			if (flag == false)
				slnList.remove(node);
		}

		return 0;
			

	}
		public static double b1(TreeNode treeNode, TreeNode goalNode){
			if (firstHreostic){
				double xResult = Math.pow((treeNode.getX()-goalNode.getX()) , 2);
				double YResult = Math.pow((treeNode.getY()-goalNode.getY()) , 2);
			
				return Math.sqrt(xResult + YResult );
				
			}
			else {
				double xResult = Math.abs(treeNode.getX()-goalNode.getX());
				double YResult = Math.abs(treeNode.getY()-goalNode.getY());
			
				return (xResult + YResult );
			}
			
		}
				
		public static TreeNode getGoalCoordinate(TreeNode[][] matrix) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					if(matrix[i][j]!=null)
						if(matrix[i][j].getCharValue()=='g')
							return new TreeNode(i,j,'g');
				}
			}
			return null;
		}
	//mission 1
	
	
	private static TreeNode getStartCoordinate(TreeNode[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j]!=null)
					if(matrix[i][j].getCharValue()=='s')
						return new TreeNode(i,j,'s');
			}
		}
		return null;
	}

	public static Matrix buildMatrix(String fileName) throws Exception {

		int x = 0, y = 0;
		int count = 0;
		TreeNode[][] matrix = null;

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				// System.out.println(line);
				// System.out.println(line.length());
				if (line.charAt(0) != '#') {
					String[] st = line.split(" ");
					matrixX = Integer.parseInt(st[0]);
					matrixY = Integer.parseInt(st[1]);
					y = Integer.parseInt(st[0]);
					x = Integer.parseInt(st[1]);
					matrix = new TreeNode[x][y];

				} else {
					char[] lineAsChars = line.toCharArray();
					for (int i = 0; i < lineAsChars.length; i++) {
						char value = lineAsChars[i];
						if (value != '#') { // TODO fix captical letter , add if
								// for start
							matrix[count][i] = new TreeNode( count,i, value);			
						}
					}

					count++;
				}

			}
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
			throw new Exception("Unable to open file " + ex);
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			throw new Exception("Unable to open file " + ex);
			// Or we could just do this:
			// ex.printStackTrace();
		}
		return new Matrix(matrix, x, y);
	}

}
