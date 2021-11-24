
public class Practice2 
{
/*1.Priority Queue
 * 2. Qeuue
 * 3. Tree
 * 4. Stack, good for back tracking
 * 5. a) Root
 * b) Edges/links/branches
 * c) parent
 * d)Siblings
 * 6)1+ bigger or smaller subtree
 * 7) a) Full tree- all nodes have two children or no children
 * c) Perfect tree- if the height of th tree is h, there should be 2^h - 1 nodes
 * b) complete tree- bottom row is filled from left to right rule
 * 8) it will be multiple choice rather than draw
 * 10)B
 * 12) keep the smallest thing in the root node; always a complete tree; perform swaps as you go up; smaller items bubble to top; heavier items go up
 * 13) when we remove something in a heap, we remove the first item, which is the root. we take the last item and add it to the top, and then readjust
 * 14) Huffman coding--put the items from smallest to largest, then do the process
 * 15) 10010100111
 * 16) public static <E> sizeTree (Node<E> root)
 * {
 * 	if (root = null)
 * {
 * return 0;
 * }
 * int mySize = 1;
 * int leftSize = sizeTree(root.left);
 * int rightSize = size(root.right);
 * return leftSize + mySize + rightSize;
 * 
 * private static class Node<E>()
 * {
 * E item;
 * Node<E> left;
 * Node<E> right;
 * public Node(E item)
 * {
 * this.item = item;
 * }
 * }
 * NOTE: there will be one tree problem with two parts that rely on each other
 * 17) public static <E> boolean isFull(Node<E> root)
 * {
 * if (root == null)
 * {
 * 	return true;
 * }
 * if (root.left == null && root.right == null)
 * {
 * return true;
 * }
 * if (root.left == null && root.right != null)
 * {
 * return false;
 * }
 * if (root.left != null && root.right == null)
 * {
 * return false;
 * }
 * Now, we are finally in the case where we have two children
 * else {
 * return isFull(root.left) && isFull(root.right);
 * }
 * }
 * 18) much tougher than an exam question
 * public static <E> boolean equals(Node<E> treeA, Node<E> treeB)
 * {
 * if (treeA == null && treeB == null)
 * {
 * return true;
 * }
 * else if (treeA != null && treeB = null)
 * return false;
 * else if (treeA == null && treeB != null)
 * else
 * {
 * if(!treeA.item.equals(treeB.item)
 * {
 * 	return false;
 * }
 * return equals(treeA.left, treeB.left) && equals (treeA.right,treeB.right);
 * }
 * }
 * 19) there will always be some variation of this problem on the exam
 * public static Map<Character, Integer> count(String words)
 * {
 * Map<Character, Integer> map = new HashMap<>();
 * for (char_letter: word.toCharArray())
 * {
 * if (!map.containsKey(letter))
 * {
 * map.put(letter, 1);
 * }
 * else
 * {
 * int previous = map.get(letter);
 * map.put(letter, previous + 1);
 * }
 * }
 * }
 * 21) Collisions in Hash tables--extend it like an arraylist; happens when you have two
 * things trying to get into the same space. Two strategies for handling it. Open addressing--if space is full, take next available space.
 * Other way is with a linkedlisy (chaining method), making the slot a list, and we append the item to the end of the list. 
 * 22) Worst case runtime of the insertion algorithm in a binary search tree is n. (the average run time for this would be log base 2 n).
 * Worst case runtime of the deletion algorithm in a binary search tree is n. 
 */
}
