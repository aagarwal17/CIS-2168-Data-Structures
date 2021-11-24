import java.util.*;

public class Exam 
{
	public static <E> void removePrefixStrings(List<String> list, String prefix)
	 {
		 int prefixLength = prefix.length();
		 
		 for (int i = 0; i < list.size(); i++)
		 {

			String word = list.get(i);
			if (word.length() >= prefix.length())
			{
				 word = word.substring(0,prefixLength);
				 if (prefix.equals(word))
				 {
					 list.remove(i);
					 i--;
				 }
			}
		 }
	 }
	
	
	
	public static void main (String [] args)
	{
		List <String> strlist = new ArrayList<String>();
		strlist.add("apples");
		strlist.add("add");
		strlist.add("I");
		strlist.add("end");
		strlist.add("add");
		strlist.add("to");
		strlist.add("is");
		strlist.add("bananas");
		strlist.add("like");
		strlist.add("eat");
		strlist.add("eat");
		strlist.add("I");
		String pre = "a";
		removePrefixStrings(strlist, pre);
		System.out.println(strlist);
	}
}
