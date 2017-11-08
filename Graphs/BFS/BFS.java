import java.util.*;
import java.io.*;

public class BFS {
	public static void breadthFirstSearch(int numberOfEdges, 
		Map<Integer, List<Integer>> adjacencyMap, int startIndex) {
		
		int[] distance = new int[numberOfEdges];
		for (int index = 0; index < distance.length; index++) {
			//mark the edges which were not visited with distance -1
			distance[index] = -1; 
		}
		distance[startIndex] = 0;
		Set<Integer> visited = new HashSet<>(); 
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startIndex);
		while (!queue.isEmpty()) {
			int currentEdge = queue.remove();
			//check if the current edge has neighbours
			if (adjacencyMap.containsKey(currentEdge)) {
				for (int neighbour : adjacencyMap.get(currentEdge)) {
					if (!visited.contains(neighbour)) {
						distance[neighbour] = distance[currentEdge] + 1;
						queue.add(neighbour);
						visited.add(neighbour);
					}
				}
			}
		}
		for (int index = 0; index < distance.length; index++) {
			System.out.println(distance[index]);
		}
	}	

	public static void main(String[] args) {
		try{
			File inputFile = new File("input.txt");
			Scanner scanner = new Scanner(inputFile);
			int numberOfEdges = scanner.nextInt();
			
			if (numberOfEdges <= 1) {
			System.err.println("You should have at least one edge");
			return;
			}
		
			//create adjacency list
			Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();
			//System.out.println(numberOfEdges);
			while(scanner.hasNext()) {
				int startEdgeIndex = scanner.nextInt();
				int endEdgeIndex = scanner.nextInt();
				System.out.println(startEdgeIndex + "  " + endEdgeIndex);
				if (adjacencyMap.containsKey(startEdgeIndex)) {
					adjacencyMap.get(startEdgeIndex).add(endEdgeIndex);
				}
				else {
					List<Integer> currentAdjacencyList = new ArrayList<>();
					currentAdjacencyList.add(endEdgeIndex);
					adjacencyMap.put(startEdgeIndex, currentAdjacencyList);
				}
			}

			breadthFirstSearch(numberOfEdges, adjacencyMap, 0);

		}

		catch(Exception e) {
			System.err.println(e);
		}

	}
}