import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

/**
 * 
 * @author Arun Agarwal Graph Project
 * 11/18/2020
 *
 */


public class Graphs
{	
	//Represents an edge in the adjacency list.

	  private class Edge {

	  public int label;

	  public int weight;

	  public Edge next;

	  }

	  public static enum State {

	  Undiscovered, Discovered, Processed

	  }

	  //Represents vertex in the graph.

	  private class Vertex {

	  public int parent;

	  public State state;

	  public Vertex() {

	state = State.Undiscovered;

	parent = -1;   } }

	public Graphs (boolean directed) {

	  this.directed = directed;

	  }

	  // whether a directed or an undirected graph.

	  private boolean directed;

	  private Edge[] adjacencyList;

	  private Vertex[] vertices;

	  // tracking graph size.

	  private int vertexCount;

	// Graph initialization and population functions.

	  //Initializing the graph

	  public void initializeGraph() {

	  //Counts saved to be stored in file header

	vertexCount = 6;

	  //Initializing the vertices.

	vertices = new Vertex[vertexCount];

	  for (int i = 0; i < vertexCount; ++i) {

	vertices[i] = new Vertex();

	  }

	  //Initializing the adjacency list.

	adjacencyList = new Edge[vertexCount];

	  //Add the seven edges

	addEdge (0, 1, 1);

	addEdge (0, 4, 1);

	addEdge (0, 5, 1);

	addEdge (1, 2, 1);

	addEdge (1, 4, 1);

	addEdge (2, 3, 1);

	addEdge (4, 3, 1);

	  }

	  public void addEdge (int source, int dest, int weight) {

	addEdge (source, dest, weight, directed);

	  }

	  public void addEdge (int source, int dest, int weight, boolean isDirected) {

	  Edge edge = new Edge();

	edge.label = dest;

	edge.weight = weight;

	edge.next = adjacencyList [source];

	adjacencyList[source] = edge;

	  

	  if (!isDirected) {

	addEdge(dest, source, weight, true);

	  }

	  }

	  // Search algorithm implementation.

	  public class SearchElement {

	  private boolean isBfs;

	  Queue nextQueue;

	  Stack nextStack;

	  public SearchElement (boolean isBfs) {

	  this.isBfs = isBfs;

	  if (isBfs) nextQueue = new LinkedList<>();

	  else nextStack = new Stack();

	  }

	  public void discoveredElement (Integer n) {

	  if (isBfs) nextQueue.add(n);

	  else nextStack.push(n);

	  }

	  public int nextSearchElement() {

	  return isBfs ? nextQueue.remove() : nextStack.pop();

	  }

	  public boolean isEmpty() {

	  return isBfs ?

	nextQueue.isEmpty() : nextStack.isEmpty();

	  }

	  }

	  public void search (boolean isBfs, int startNode) {

	  //Clear the graph state in case it is being reused.

	  for (Vertex v: vertices) {

	v.parent = -1;

	v.state = State.Undiscovered;

	  }

	  SearchElementStrategy next = new SearchElementStrategy(isBfs);

	vertices[startNode].state = State.Discovered;

	  next.discoveredElement(startNode);

	  while (!next.isEmpty()) {

	  

	  int index = next.nextSearchElement();

	  Edge edge = adjacencyList[index];

	  

	  while (edge != null) {

	  int dest = edge.label;

	  if (vertices [dest].state == State.Undiscovered) {

	  next.discoveredElement (dest);

	vertices [dest].parent = index;

	vertices [dest].state = State.Discovered;

	  }

	  //Move on to the next edge.

	edge = edge.next;

	  }

	vertices[index].state = State.Processed;

	  }

	  }

	  // Function to print path to a node in a pre-searched graph.

	  public void printPathTo(int dest) {

	  if (dest == -1) return;

	  Vertex vertex = vertices[dest];

	printPathTo(vertex.parent);

	  System.out.print(dest + " ");

	   }

	  public void printAllPathsFrom(int source) {

	  System.out.println("\n[Printing shortest path to each " +

	  "vertex from {" + source + "}]:");

	  for (int i = 0; i < vertexCount; ++i) {

	  if (i == source) continue; //Skip path to self.

	  System.out.print("Path to node " + i + ": { ");

	printPathTo(i);

	  System.out.print("}");

	  System.out.println();

	  }

	  }

	  public static void main(String[] args) {

	  //Creating and loading undirected graph.

	  Graphs g = new Graphs (false);

	g.initializeGraph();

	  //BFS with root vertex 0

	g.search(true, 0);

	g.printAllPathsFrom(0);

	  ////BFS with root vertex 3

	g.search(false, 0);

	g.printAllPathsFrom(0);

	  }
	/*public static void main (String [] args)
	{
		Graph <Integer, String> g = new SparseGraph<>();
		g.addEdge("12", 1, 2);
		System.out.println(g);
	}*/
}