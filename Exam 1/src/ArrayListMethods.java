import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Arun Agarwal/ List Methods and Strings	
 *
 */
public class ArrayListMethods 
{

	//2.1: Uniqueness: 
	public static <E> boolean unique(List<E> list)
	{
		for (int i = 0; i < list.size() - 1; i++)
		{
			for (int j= i + 1; j < list.size(); j++)
			{
				if (list.get(i).equals(list.get(j)))
				{
					return false;
				}
			}
		}
		return true;	
	}
	
	//2.2: All Multiples:
	public static List allMultiples(List<Integer> list, int integer)
	{
		List<Integer> newList = new ArrayList<Integer>();

		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) % integer == 0)
			{
				newList.add(list.get(i));
			}
		}
		return newList;
	}
	
	//2.3: All Strings of Size:
	public static List<String> allStringsOfSize(List<String> list, int length)
	{
		List<String> newList = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).length() == length)
			{
				newList.add(list.get(i));
			}
		}
		return newList;
	}
	
	//2.4: isPermutation:
	//TO DO IN CLASS WEDNESDAY
	public static <E> boolean isPermutation(List<E> list1, List<E> list2) 
	{
		if (list1.size() != list2.size())
		{
			return false;
		}
		
		for (E item: list1)
		{
			int count1 = 0;
			int count2 = 0;
			
			//count the number of times item appears in A
			for (int i = 0; i < list1.size(); i++)
			{
				E otherItem = list1.get(i);
				if(item.equals(otherItem))
					count1++;
			}
			//count the number of times items appears in B
			for (int i = 0; i < list2.size(); i++)
			{
				E otherItem = list2.get(i);
				if(item.equals(otherItem))
					count2++;
			}	
			
			if(count1 != count2)
			{
				return false;
			}
		}
		
		return true;
		
		
       /** if(list1.size() == list2.size()) 
        {
            int count1;
            int count2;
            for(int i = 0; i < list1.size(); ++i) 
            {
                count1 = 0;
                count2 = 0;
                for(int j = 0; j < list1.size(); ++j) 
                {
                    if(list1.get(j).equals(list1.get(i))) 
                    {
                        count1++;
                    }
                }
                for(int j = 0; j < list1.size(); ++j) 
                {
                    if(list2.get(j).equals(list1.get(i))) 
                    {
                        count2++;
                    }
                }
                if(count1 != count2) 
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    **/}
	
	
	
	//2.5: String To List of Words:
	public static List<String> stringToListOfWords(String words)
	{
		String[] eachWord = words.split("\\s+");
		
		List<String> newList = new ArrayList<String>();
		
		//extra credit part to remove punctuation
		//also the part to get the items in the array into an arraylist
		for (int i = 0; i < eachWord.length; i++)
		{
			eachWord[i] = eachWord[i].replaceAll("[^a-zA-Z]", "");
			
			if (!eachWord[i].equals(""))
				newList.add(eachWord[i]);
        }
		return newList;
	}
	
	
	//2.6: Remove All Instances:
	public static <E> void removeAllInstances(List <E> list, E something) 
	{
			for (int i = 0; i < list.size(); i++)
			{
				if(list.get(i)==something)
				{
					list.remove(i);
					i--;
				}
			}
		//Iterator itr = al.iterator();
		//while (itr.hasNext())
		//{
		//	int x = (Integer) itr.next();
		// if (x == something)
		//		itr.remove();
	}
}