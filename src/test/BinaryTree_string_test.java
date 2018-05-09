package test;


import mylibs.BinaryTree_string;

public class BinaryTree_string_test {

	public static void main(String[] args)
	{
		
		BinaryTree_string bst=new BinaryTree_string();
		bst.insert(10,"10");
		bst.insert(5,"20");
		bst.insert(15,"30");
		bst.insert(20,"40");
		
		String[][] temp;
		
		String[] mode= {"Pre Order","In Order","Post Order"};
		
		for(int j=1;j<4;j++)
		{
			System.out.print(mode[(j-1)]+" : \n");
			temp=bst.getdata(j);
			for(int i=0;i<temp.length;i++)
			{
				System.out.println("\t"+temp[i][0]+" : "+temp[i][1]);
			}
			System.out.println();
		}

	}

}
