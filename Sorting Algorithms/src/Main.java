/*
 * Arun Agarwal Sorting Main Method Class
 */
public class Main 
{
	public static void main(String[] args){
	   int t= 5000;
	   int t2=10000;
	   int t3=20000;
	   int t4=30000;
	   int t5=40000;
	  
	  
	   /**
	   * QUICK SORT
	   */
	   System.out.println("***********Quick Sort *****************");
	   Long b=System.currentTimeMillis();
	   QuickSort q1=new QuickSort(t);
	   q1.sort();
	   Long a=System.currentTimeMillis();
	   System.out.println("Quick sort takes "+(a-b)+" ms"+ " for " +t);
	   Long b2=System.currentTimeMillis();
	   QuickSort q2=new QuickSort(t2);
	   q2.sort();
	   Long a2=System.currentTimeMillis();
	   System.out.println("Quick sort takes "+(a2-b2)+" ms"+ " for " +t2);
	   Long b3=System.currentTimeMillis();
	   QuickSort q3=new QuickSort(t3);
	   q3.sort();
	   Long a3=System.currentTimeMillis();
	   System.out.println("Quick sort takes "+(a3-b3)+" ms"+ " for "+ t3);
	   Long b4=System.currentTimeMillis();
	   QuickSort q4=new QuickSort(t4);
	   q4.sort();
	   Long a4=System.currentTimeMillis();
	   System.out.println("Quick sort takes "+(a4-b4)+" ms" + " for "+ t4);
	   Long b5=System.currentTimeMillis();
	   QuickSort q5=new QuickSort(t5);
	   q5.sort();
	   Long a5=System.currentTimeMillis();
	   System.out.println("Quick sort takes "+(a5-b5)+" ms" + " for "+ t5);
	  
	  
	   /**
	   * InsertionSort
	   */
	   System.out.println("********************Insertion Sort ************************");
	   b=System.currentTimeMillis();
	   InsertionSort i1=new InsertionSort(t);
	   i1.sort();
	   a=System.currentTimeMillis();
	   System.out.println("Insertion sort takes "+(a-b)+ " ms"+ " for " +t);
	   b2=System.currentTimeMillis();
	   InsertionSort i2=new InsertionSort(t2);
	   i2.sort();
	   a2=System.currentTimeMillis();
	   System.out.println("Insertion sort takes "+(a2-b2)+" ms"+ " for " +t2);
	   b3=System.currentTimeMillis();
	   InsertionSort i3=new InsertionSort(t3);
	   i3.sort();
	   a3=System.currentTimeMillis();
	   System.out.println("Insertion sort takes "+(a3-b3)+" ms"+ " for "+ t3);
	   b4=System.currentTimeMillis();
	   InsertionSort i4=new InsertionSort(t4);
	   i4.sort();
	   a4=System.currentTimeMillis();
	   System.out.println("Insertion sort takes "+(a4-b4)+" ms" + " for "+ t4);
	   b5=System.currentTimeMillis();
	   InsertionSort i5=new InsertionSort(t5);
	   i5.sort();
	   a5=System.currentTimeMillis();
	   System.out.println("Insertion sort takes "+(a5-b5)+" ms" + " for "+ t5);
	  
	   /**
	   * MergeSort
	   */
	   System.out.println("*******************MergeSort************************");
	   b=System.currentTimeMillis();
	   MergeSort m1=new MergeSort(t);
	   m1.sort();
	   a=System.currentTimeMillis();
	   System.out.println("Merge sort takes "+(a-b)+ " ms"+ " for " +t);
	   b2=System.currentTimeMillis();
	   MergeSort m2=new MergeSort(t2);
	   m2.sort();
	   a2=System.currentTimeMillis();
	   System.out.println("Merge sort takes "+(a2-b2)+ " ms"+ " for " +t2);
	   b3=System.currentTimeMillis();
	   MergeSort m3=new MergeSort(t3);
	   m3.sort();
	   a3=System.currentTimeMillis();
	   System.out.println("Merge sort takes "+(a3-b3)+" ms"+ " for "+ t3);
	   b4=System.currentTimeMillis();
	   MergeSort m4=new MergeSort(t4);
	   m4.sort();
	   a4=System.currentTimeMillis();
	   System.out.println("Mege sort takes "+(a4-b4)+ " ms" + " for "+ t4);
	   b5=System.currentTimeMillis();
	   MergeSort m5=new MergeSort(t5);
	   m5.sort();
	   a5=System.currentTimeMillis();
	   System.out.println("Merge sort takes "+(a5-b5)+" ms" + " for "+ t5);
	   }
}