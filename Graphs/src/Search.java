/*
 * Arun Agarwal Graphs Project
 * 11/25/2020
 */

import java.util.*;

public class Search 
{

   // Graph class which store node and its edges
   private class Graph 
   {
      
       private int val;
       private Graph left_edge; 
       private Graph right_edge;
       private boolean visited;
      
       private Graph(int val) 
       {
           this.val = val;
           this.left_edge = this.right_edge = null;
           this.visited = false;
       }
      
   }
  
   // Add data to tree
   public Graph addDataToTree() 
   {
       Graph root = new Graph(1);
       Graph a = new Graph(2);
       Graph b = new Graph(3);
       Graph c = new Graph(4);
       Graph d = new Graph(5);
       Graph e = new Graph(6);
       Graph f = new Graph(7);
       Graph g = new Graph(8);
       Graph h = new Graph(9);
       
       root.left_edge = a;
       root.right_edge = b;
       a.left_edge = c;
       a.right_edge = d;
       d.right_edge = e;
       b.left_edge = f;
       f.right_edge = g;
       f.left_edge = h;
       return root;
       
   }

   // returns the left_edge child if not visited, then right_edge child if not visited
   private Graph getUnvisitedChildNode(Graph node) 
   {
       if (node.left_edge != null) 
       {
           if (!node.left_edge.visited) 
           {
               return node.left_edge;
           }
       }
       if (node.right_edge != null) 
       {
           if (!node.right_edge.visited) 
           {
               return node.right_edge;
           }
       }
       return null;
   }

   public void bfssearch(Graph root) 
   {
       // BFS uses Queue
       Queue<Graph> queue = new LinkedList<>();
       queue.add(root);
       System.out.print(root.val);
      
       while (!queue.isEmpty()) 
       {
    	   // remove the head of queue
           Graph node = queue.remove(); 
           Graph child = null;
           while ((child = getUnvisitedChildNode(node)) != null) 
           {
               child.visited = true;
               System.out.print(" " + child.val);
               queue.add(child);
           }
       }
       System.out.println(" ");
   }

   public void dfssearch(Graph root) 
   {
       // DFS uses Stack
       Stack<Graph> stack = new Stack<>();
       stack.push(root);
       root.visited = true;
       System.out.print(root.val);
      
       while (!stack.isEmpty()) 
       {
    	   // top of the stack without removing
           Graph node = stack.peek(); 
           Graph child = getUnvisitedChildNode(node);
           if(child != null) 
           {
               child.visited = true;
               System.out.print(" " + child.val);
               stack.push(child);
           }
           else 
           {
               stack.pop();
           }
       }
       System.out.println(" ");
   }

   public static void main(String args[]) 
   {
	   System.out.println("Diagram of Tree:");
       System.out.println("-------1-------");
       System.out.println("---2-------3---");
       System.out.println("--4-5-----7----");
       System.out.println("-----6---9-8---");
      
       Search search = new Search();
      
       System.out.println("-----------------------------------");
       System.out.println("Depth first search");
       Graph root_DFS = search.addDataToTree();
       search.dfssearch(root_DFS);
      
      
       System.out.println("-----------------------------------");
       System.out.println("Breadth first search");
       Graph root_bfs = search.addDataToTree();
       search.bfssearch(root_bfs);
         
   }
}