import java.util.*;
import java.io.*;
public class DFS {
	// Method which checks if a graph has cycles and if it does not, it builds a 
	// list which contains the edges of a graph, in topological order.
	// An aditional map is used to keep the state of visited nodes (opened or closed)
	public static boolean isCycleFree(int currentIndex, int numberOfEdges, 
		Map<Integer, List<Integer>> adjacencyMap, Map<Integer, String> visited,
		List<Integer> topologicalSorted) {
		boolean cycleFree = true;
		if (!visited.containsKey(currentIndex)) {
			visited.put(currentIndex, "open");
			if (adjacencyMap.containsKey(currentIndex)) {
				for (int adj : adjacencyMap.get(currentIndex)) {
					if (!visited.containsKey(adj)) {
						cycleFree = cycleFree && isCycleFree(adj, numberOfEdges, 
							adjacencyMap, visited, topologicalSorted);
					} else if (visited.get(adj) == "open") {
						cycleFree = false;
					}
				}	
			}
			visited.put(currentIndex, "closed");
			topologicalSorted.add(currentIndex);
		}
		else if (visited.get(currentIndex) == "open") {
			cycleFree = false;
		} 
		return cycleFree;
	}

	public static void main(String[] args) {
		try{
			Scanner scanner = new Scanner(System.in);
			String fileName = scanner.next();
			File inputFile = new File(fileName);
			scanner = new Scanner(inputFile);
			int numberOfEdges = scanner.nextInt();
			
			if (numberOfEdges <= 1) {
				System.err.println("You should have at least one edge!");
				return;
			}
		
			//create adjacency map
			Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();
			while(scanner.hasNext()) {
				int startEdgeIndex = scanner.nextInt();
				int endEdgeIndex = scanner.nextInt();
				System.out.println(startEdgeIndex + "  " + endEdgeIndex);
				if (adjacencyMap.containsKey(startEdgeIndex)) {
					adjacencyMap.get(startEdgeIndex).add(endEdgeIndex);
				} else {
					List<Integer> currentAdjacencyList = new ArrayList<>();
					currentAdjacencyList.add(endEdgeIndex);
					adjacencyMap.put(startEdgeIndex, currentAdjacencyList);
				}
			}

			List<Integer> result = new ArrayList<>();
			Map<Integer, String> visited = new HashMap<Integer, String>();
			boolean graphHasNoCycle = true;

			//test for cycle, for all unexplored edges
			for (int index = 0; index < numberOfEdges; index++) {
				if(visited.containsKey(index)) {
					continue;
				}
				graphHasNoCycle = graphHasNoCycle && 
					isCycleFree(index, numberOfEdges, adjacencyMap, visited, result);	
			}
			
			System.out.println(graphHasNoCycle);
			
			//show the topological order, if there is no cycle
			if (graphHasNoCycle) {
				for(int node : result) {
					System.out.print(node + "  ");
				}
			}				
		}
		catch(Exception e) {
			System.err.println(e);
		}
	}
}
