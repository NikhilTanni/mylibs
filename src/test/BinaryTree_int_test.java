package test;

import java.util.ArrayList;

import mylibs.BinaryTree_int;

public class BinaryTree_int_test {

	public static void main(String[] args)
	{
		BinaryTree_int bst=new BinaryTree_int();
		bst.insert(10);
		bst.insert(5);
		bst.insert(15);
		bst.insert(20);
		
		ArrayList<Integer> temp;
		
		String[] mode= {"Pre Order","In Order","Post Order"};
		
		for(int j=1;j<4;j++)
		{
			System.out.print(mode[(j-1)]+" : ");
			temp=bst.getdata(j);
			for(int i=0;i<temp.size();i++)
			{
				System.out.print(temp.get(i)+" ");
			}
			System.out.println();
		}

	}

}
