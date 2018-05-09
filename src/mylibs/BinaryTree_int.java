package mylibs;
/**
 * @program BinaryTree_int
 * 
 * This program(as library) is designed to BinaryTree (only int values) implementation
 * key points while using into your program:
 * 	1) dont forget to import this program
 * 	2) create object as BinaryTree_int "some name" =new BinaryTree_int();
 * 	3) functions : 
 * 		-insert(int) - insert(data)
 * 		-delete(int) - delete(data)
 * 		-getdata(int-mode) - return ArrayList<Integer>;
 * 			modes:
 * 				1- Preorder
 * 				2- Inorder
 * 				3- postorder
 * 
 * @author Nikhil Tanni
 * @project B-I-G
 * @version 1.0
 * 
 */
import java.util.ArrayList;

class BSTNode_int
{
	BSTNode_int left, right;
	int data;
	
	public BSTNode_int(int n)
	{
		left=null;
		right=null;
		data=n;
	}
	
	public void setleft(BSTNode_int n)
	{
		left=n;
	}
	public void setright(BSTNode_int n)
	{
		right=n;
	}
	public BSTNode_int getleft()
	{
		return left;
	}
	public BSTNode_int getright()
	{
		return right;
	}
	public void setdata(int d)
	{
		data=d;
	}
	public int getdata()
	{
		return data;
	}
}

class BST_int
{
	private BSTNode_int root;
	
	public BST_int()
	{
		root=null;
	}
	public boolean isempty()
	{
		return root==null;
	}
	public void insert(int data)
	{
		root=insert(root, data);
	}
	private BSTNode_int insert(BSTNode_int node, int data)
	{
		if(node==null)
		{
			node = new BSTNode_int(data);
		}
		else if(data <= node.getdata())
		{
			node.left=insert(node.left, data);
		}
		else
		{
			node.right = insert(node.right, data);
		}
		return node;
	}
	
	public void delete(int k)
	{
		if(isempty())
		{
			System.out.println("Tree Empty");
		}
		else if(search(k) == false)
		{
			System.out.println("Sorry " + k + " is not present");
		}
		else
		{
			root = delete(root,k);
			System.out.println(k+" deleted from tree");
		}
	}
	private BSTNode_int delete(BSTNode_int root, int k)
	{
		BSTNode_int p,p2,n;
		if(root.getdata() == k)
		{
			BSTNode_int lt, rt;
			lt=root.getleft();
			rt=root.getright();
			if(lt==null && rt==null)
			{
				return null;
			}
			else if(lt==null)
			{
				p=rt;
				return p;
			}
			else if(rt==null)
			{
				p=lt;
				return p;
			}
			else
			{
				p2=rt;
				p=rt;
				while(p.getleft()!=null)
				{
					p=p.getleft();
				}
				p.setleft(lt);
				return p2;
			}
		}
		if(k<root.getdata())
		{
			n=delete(root.getleft(),k);
			root.setleft(n);
		}
		else
		{
			n=delete(root.getright(),k);
			root.setright(n);
		}
		return root;
	}
	
	public int countnodes()
	{
		return countnodes(root);
	}
	private int countnodes(BSTNode_int r)
	{
		if(r==null)
		{
			return 0;
		}
		else
		{
			int l=1;
			l+=countnodes(r.getleft());
			l+=countnodes(r.getright());
			return l;
		}
	}
	
	public boolean search(int val)
	{
		return search(root, val);
	}
	private boolean search(BSTNode_int r, int val)
	{
		boolean found=false;
		while((r!=null)&&!found)
		{
			int rval=r.getdata();
			if(val<rval)
			{
				r=r.getleft();
			}
			else if(val>rval)
			{
				r=r.getright();
			}
			else
			{
				found=true;
				break;
			}
			found=search(r,val);
		}
		return found;
	}
	
	
	
	ArrayList<Integer> temp_pre=new ArrayList<Integer>();
	ArrayList<Integer> temp_post=new ArrayList<Integer>();
	ArrayList<Integer> temp_ino=new ArrayList<Integer>();
	
	public ArrayList preorder()
	{
		temp_pre.clear();
		return preorder(root);
	}
	private ArrayList preorder(BSTNode_int r)
	{
		if(r!=null)
		{
			temp_pre.add(r.getdata());
			//System.out.println(r.getdata() + " ");
			preorder(r.getleft());
			preorder(r.getright());
		}
		return temp_pre;
	}
	
	public ArrayList postorder()
	{
		temp_post.clear();
		return postorder(root);
	}
	private ArrayList postorder(BSTNode_int r)
	{
		if(r!=null)
		{
			postorder(r.getleft());
			postorder(r.getright());
			//System.out.println(r.getdata()+" ");
			temp_post.add(r.getdata());
		}
		return temp_post;
	}
	
	public ArrayList inorder()
	{
		temp_ino.clear();
		return inorder(root);
	}
	private ArrayList inorder(BSTNode_int r)
	{
		if(r!=null)
		{
			inorder(r.getleft());
			//System.out.println(r.getdata()+" ");
			temp_ino.add(r.getdata());
			inorder(r.getright());
		}
		return temp_ino;
	}
}





public class BinaryTree_int {

	BST_int bst;
	public BinaryTree_int()
	{
		bst=new BST_int();
		
	}
	
	public void insert(int i)
	{
		bst.insert(i);
	}
	public void delete(int i)
	{
		bst.delete(i);
	}
	public boolean search(int i)
	{
		return bst.search(i);
	}
	public ArrayList getdata(int i)
	{
		if(i==1)
		{
			return bst.preorder();
		}
		else if(i==2)
		{
			return bst.inorder();
		}
		else if(i==3)
		{
			return bst.postorder();
		}
		return null;
	}
}
