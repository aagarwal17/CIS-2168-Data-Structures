import java.util.*;
public class Final 
//there will be a linkedlist problem where, given two linked lists, do something with them
{
	private static class Node<E>
	{
		E data;
		Node<E> left;
		Node<E> right;
		
		public Node(E data)
		{
			this.data = data;
		}
	}
	
	
	public static <E> Node<E> mirror(Node<E> root)
	{
		if (root == null)
		{
			return null;
		}
		
		Node<E> newRoot = new Node<>(root.data);
		newRoot.left = mirror(root.right);
		newRoot.right = mirror(root.left);
		
		return newRoot;
	}
	
	//O(n) time since we see each node once
	
	
	
	//Given a text file with two fields (username, how long the login lasted in milliseconds); find the average login time for each user
	public static Map<String, Double> averageLoginTimes(String fileName) throws Exception
	{
		Map<String, Double> loginTimes = new HashMap<>();
		Map<String, Integer> numLogins = new HashMap<>();
			Scanner scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				String[] fields = line.split(" ");
				String username = fields[0];
				double time = Double.parseDouble(fields[1]);
				
				if(!numLogins.containsKey(username))
				{
					numLogins.put(username, 1);
					loginTimes.put(username,time);
				}
				else //we have seen that username in the file before
				{
					numLogins.put(username, numLogins.get(username) + 1);
					loginTimes.put(username,loginTimes.get(username) + time);
				}
			}
		
			scanner.close();
			
			Map<String, Double> averages = new HashMap<>();
			for(String username : numLogins.keySet())
			{
				averages.put(username,  loginTimes.get(username)/numLogins.get(username));
			}
		
		return averages;
	}
	
	
	public static <E> void BFS(Node<E> root)
	{
		Queue<Node<E>> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty())
		{
			Node<E> current = q.poll();
			if(current.left != null)
			{
				q.offer(current.left);
			}
			if(current.right != null)
			{
				q.offer(current.right);
			}
			System.out.println(current.data);
		}
	}
}
