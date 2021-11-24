public class Test {
public static void main(String[] args) {
int arr[] = {5,19,6,12,48,6,23,39,2,18};
int[] a2 = arr;
a2[3] = arr[2];
System.out.println(a2[3]);
arr[2] = arr[3];
System.out.println(arr[2] + " " + arr[3]);
arr[3] = a2[3];
System.out.println(arr[2] + " " + arr[3]);
}
}