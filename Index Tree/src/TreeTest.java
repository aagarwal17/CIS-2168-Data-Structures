public class TreeTest {

      

       private IndexNode root;

      

       public TreeTest()

       {

             root = null;

       }

      

       // method to add a word in the tree

       public void add(String word, int lineNumber)

       {

             root = add(root,word,lineNumber);

       }

      

       // recursive method to add a word in the IndexTree

       private IndexNode add(IndexNode root, String word, int lineNumber)

       {

             if(root == null)

             {

                    root = new IndexNode(word,lineNumber);

             }else

             {

                    if(word.compareTo(root.word) < 0)

                    {

                           root.left = add(root.left,word,lineNumber);

                    }else if(word.compareTo(root.word) > 0)

                    {

                           root.right = add(root.right,word,lineNumber);

                    }else

                    {

                           if(!root.list.contains(lineNumber))

                           {

                                 root.occurrences++;

                                 root.list.add(lineNumber);

                           }

                    }

             }

            

             return root;

       }

      

       //returns true if the word is in the index

       public boolean contains(String word)

       {

             if(root == null)

                    return false;

             IndexNode node = root;

             while(node != null)

             {

                    if(node.word == word)

                           return true;

                    else if(word.compareTo(node.word) < 0)

                           node = node.left;

                    else

                           node = node.right;

             }

            

             return false;

       }

      

       // method to delete a word

       public void delete(String word)

       {

             if(root == null)

             {

                    System.out.println(" Tree Empty");

             }else if(!contains(word))

                    System.out.println(word+" not found in tree");

             else {

                    root = delete(root,word);

                    System.out.println(word+" deleted from tree");

             }

       }

      

       //remove the word and all entries for the word

       private IndexNode delete(IndexNode root,String word)

       {

             IndexNode p,n;

            

             if( word.equals(root.word))

             {

                    IndexNode lt,rt;

                    lt = root.left;

                    rt = root.right;

                   

                    if(lt == null && rt == null)

                           return null;

                    else if(lt == null)

                           return rt;

                    else if(rt == null)

                           return lt;

                    else {

                           p = rt;

                           while(p.left != null)

                                 p = p.left;

                           p.left = lt;

                           return rt;

                    }

             }

             else if(word.compareTo(root.word) < 0)

             {

                    n = delete(root.left,word);

                    root.left = n;

             }

             else

             {

                    n = delete(root.right,word);

                    root.right = n;

             }

            

             return root;

       }

      

       // prints all the words in the index in inorder order

       public void printIndex()

       {

             printIndex(root);

       }

      

       // recursive method to print the words in alphabetical order

       private void printIndex(IndexNode root)

       {

             if(root == null)

             {

                    return;

             }

             printIndex(root.left);

             System.out.println(root);

             printIndex(root.right);

       }

      

       public static void main(String[] args) {

      

             // test the IndexTree

             IndexTree index = new IndexTree();

            

             index.add("Guava", 15);

             index.add("Orange", 20);

             index.add("Lemon", 10);

             index.add("Apple", 5);

             index.add("Orange", 25);

             index.add("Apple", 7);

             index.add("Pineapple", 2);

             index.add("Banana", 12);

            

            

             index.printIndex();

            

             index.delete("Orange");

             index.printIndex();

       }

}