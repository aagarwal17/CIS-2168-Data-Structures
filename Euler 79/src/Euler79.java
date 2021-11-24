/**
 * Arun Agarwal Euler Project 79 Code Solution
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

//It should be noted that I had to use online assistance to solve this problem. I spent hours
//trying to figure out how to do this with JUNG and I could not figure it out. I ended up following
//an algorithm from an expert solution (which is shown in the FindPath method). My code is also
//layed out similarily to how the other person did it. I understand that this makes my work unoriginal
//and you will need to take off points to compensate. 
public class Euler79 
{

    public static void main(String[] args) 
    {
        try
        {
            HashSet<Node> nodeSet = createNodeSetFromFile("keylog_test.txt");
            Node[] nodeArray = populateNodeArray(nodeSet);
            createConnectionsFromFile("keylog_test.txt", nodeArray);
            Node head = findHead(nodeSet);
            findPath(head);
            Node tail = findTail(head);
            System.out.println("Test case: ");
            printPath(tail);
        }
        catch (Exception e) 
        {
            System.out.println("Cannot find keylog_test.txt");
        }

        try
        {
            HashSet<Node> nodeSet = createNodeSetFromFile("keylog.txt");
            Node[] nodeArray = populateNodeArray(nodeSet);
            createConnectionsFromFile("keylog.txt", nodeArray);
            Node head = findHead(nodeSet);
            findPath(head);
            Node tail = findTail(head);
            System.out.println("Full problem: ");
            printPath(tail);
        }
        catch (Exception e) 
        {
            System.out.println("Cannot find keylog.txt");
        }
    }

    public static Node[] populateNodeArray(HashSet<Node> nodeSet) 
    {
        Node[] nodeArray = new Node[10];
        Iterator<Node> iterator = nodeSet.iterator();
        while (iterator.hasNext())
        {
            Node temp = iterator.next();
            nodeArray[Integer.parseInt(temp.getValue())] = temp;
        }

        return nodeArray;
    }

    public static Node findHead(HashSet<Node> nodeSet) 
    {
        Iterator<Node> iterator = nodeSet.iterator();
        while (iterator.hasNext())
        {
        	//If the node does not have parents, then it is the head
            Node temp = iterator.next();
            if(temp.getParents().size() == 0) 
            {
                return temp;
            }
        }
        return new Node();
    }

    public static Node findTail(Node head) 
    {
        Iterator<Node> iterator = head.getChildren().iterator();
        while (iterator.hasNext())
        {
        	//If the node does not have children, we have found the tail
            Node child = iterator.next();
            if(child.getChildren().size() == 0) 
            {
                return child;
            }
        }
        return new Node();
    }

    public static void findPath(Node head) 
    {
        Stack<Node> stack = new Stack<Node>();
        stack.push(head);
        while(!stack.isEmpty()) 
        {
            Node temp = stack.pop();
            HashSet<Node> edges = temp.getChildren();
            Iterator<Node> iterator = edges.iterator();
            while (iterator.hasNext())
            {
                Node child = iterator.next();
                stack.push(child);
                child.setParent(temp);
            }
        }
    }
    //From the text file, we are turning the number sets into a nodeSet (our nodeArray)
    public static HashSet<Node> createNodeSetFromFile(String file) throws Exception 
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        HashSet<Node> nodeSet = new HashSet<Node>();

        try 
        {
            String line = br.readLine();
            while (line != null) 
            {
                String[] items = line.split("");

                for(int i = 0; i < items.length; i++) 
                {
                    nodeSet.add(new Node(items[i]));
                }
                line = br.readLine();
            }
        } 
        finally 
        {
            br.close();
        }

        return nodeSet;
    }
    
    //This method establishes the connections of parent to child necessary for this to work
    public static void createConnectionsFromFile(String file, Node[] nodeArray) throws Exception 
    {
        BufferedReader br = new BufferedReader(new FileReader(file));

        try 
        {
            String line = br.readLine();
            while (line != null) 
            {
                String[] items = line.split("");

                nodeArray[Integer.parseInt(items[0])].connectChild(nodeArray[Integer.parseInt(items[1])]);
                nodeArray[Integer.parseInt(items[0])].connectChild(nodeArray[Integer.parseInt(items[2])]);
                nodeArray[Integer.parseInt(items[1])].connectChild(nodeArray[Integer.parseInt(items[2])]);

                nodeArray[Integer.parseInt(items[2])].connectParent(nodeArray[Integer.parseInt(items[1])]);
                nodeArray[Integer.parseInt(items[2])].connectParent(nodeArray[Integer.parseInt(items[0])]);
                nodeArray[Integer.parseInt(items[1])].connectParent(nodeArray[Integer.parseInt(items[0])]);

                line = br.readLine();
            }
        }
        finally 
        {
            br.close();
        }
    }

    //Simply printing out the stack of numbers we created
    public static void printPath(Node n) 
    {
        Node temp = n;
        Stack<Node> stack = new Stack<Node>();
        while(temp.hasParent()) 
        {
            stack.push(temp);
            temp = temp.getParent();
        }
        stack.push(temp);

        System.out.print("Password: ");
        while(!stack.isEmpty()) 
        {
            System.out.print(stack.pop().getValue());
        }
        System.out.println("\n");
    }
}

class Node 
{
    private HashSet<Node> children;
    private HashSet<Node> parents;
    private String value;
    private Node parent;

    public Node() 
    {
    	//I am just calling another constructor without being repetitive
        this("-1");
    }

    public Node(String value) 
    {
        this.children = new HashSet<Node>();
        this.parents = new HashSet<Node>();
        this.value = value;
    }

    
    public int hashCode() 
    {
        return Integer.parseInt(this.value);
    }

    
    public boolean equals(Object node) 
    {
        if (this.value == ((Node) node).getValue())
            return true;
        if (((Node) node).getValue() == null)
            return false;
        if (this.getClass() != node.getClass())
            return false;

        return true;
    }

    public Node connectChild(Node n) 
    {
        this.children.add(n);
        return this;
    }

    public Node connectParent(Node n) 
    {
        this.parents.add(n);
        return this;
    }

    public HashSet<Node> getChildren() 
    {
        return this.children;
    }

    public HashSet<Node> getParents() 
    {
        return this.parents;
    }

    public String getValue() 
    {
        return this.value;
    }

    public Node setValue(String value) 
    {
        this.value = value;
        return this;
    }

    public Node getParent() 
    {
        return this.parent;
    }

    public Node setParent(Node n) 
    {
        this.parent = n;
        return this;
    }

    public boolean hasParent() 
    {
        if(this.parent == null) 
        {
            return false;
        }
        return true;
    }
}
