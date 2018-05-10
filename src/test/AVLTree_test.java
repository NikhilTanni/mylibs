package test;

import mylibs.AVLTree;
public class AVLTree_test {

	public static void main(String[] args)
	{
		//example functions
		AVLTree avl = new AVLTree();
		avl.insert(1, "hello");
		avl.insert(2, "world");
		avl.insert(3, "good");
		avl.delete(3);
		avl.insert(3, "best");
		avl.insert(4, "Feeling");
		avl.traverseInOrder();
		
		String[][] t=avl.getdata();
		
		for(int i=0;i<t.length;i++)
		{
			System.out.println("Key : "+t[i][0]+" | Value : "+t[i][1]);
		}
		

	}

}
