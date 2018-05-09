package mylibs;
/**
 * @program BinaryTree_String
 * 
 * This program(as library) is designed to BinaryTree(key with data) implementation
 * key points while using into your program:
 * 	1) dont forget to import this program
 * 	2) create object as BinaryTree_string "some name" =new BinaryTree_string();
 * 	3) functions : 
 * 		-insert(int,String) - insert(key,data)
 * 		-delete(int) - delete(key)
 * 		-getdata(int-mode) - return type String[][];
 * 			String[][0]=key, String[][1]=data
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

class BSTNode_str
{
	BSTNode_str left, right;
	String[] data;
	
	public BSTNode_str(String[] n)
	{
		left=null;
		right=null;
		data=n;
	}
	
	public void setleft(BSTNode_str n)
	{
		left=n;
	}
	public void setright(BSTNode_str n)
	{
		right=n;
	}
	public BSTNode_str getleft()
	{
		return left;
	}
	public BSTNode_str getright()
	{
		return right;
	}
	//public void setdata(int d)
	//{
	//	data=d;
	//}
	public String[] getdata()
	{
		return data;
	}
}

class BST_str
{
	private BSTNode_str root;
	
	public BST_str()
	{
		root=null;
	}
	public boolean isempty()
	{
		return root==null;
	}
	public void insert(int key, String data)
	{
		root=insert(root, key, data);
	}
	private BSTNode_str insert(BSTNode_str node, int key, String data)
	{
		String[] temp= {key+"",data};
		if(node==null)
		{
			node = new BSTNode_str(temp);
		}
		else if(key <= Integer.parseInt(node.getdata()[0]))
		{
			node.left=insert(node.left, key, data);
		}
		else
		{
			node.right = insert(node.right, key, data);
		}
		return node;
	}
	
	public void delete(int k)
	{
		if(isempty())
		{
			System.out.println("Tree Empty");
		}
		else if(search_bool(k) == false)
		{
			System.out.println("Sorry " + k + " is not present");
		}
		else
		{
			root = delete(root,k);
			System.out.println(k+" deleted from tree");
		}
	}
	private BSTNode_str delete(BSTNode_str root, int k)
	{
		BSTNode_str p,p2,n;
		if(Integer.parseInt(root.getdata()[0]) == k)
		{
			BSTNode_str lt, rt;
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
		if(k<Integer.parseInt(root.getdata()[0]))
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
	private int countnodes(BSTNode_str r)
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
	
	private String[] search_tmp= {};
	public String[] search(int val)
	{
		if(search(root, val))
		{
			return search_tmp;
		}
		else return search_tmp;
	}
	private boolean search_bool(int val)
	{
		return search(root, val);
	}
	private boolean search(BSTNode_str r, int val)
	{
		boolean found=false;
		while((r!=null)&&!found)
		{
			int rval=Integer.parseInt(r.getdata()[0]);
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
				search_tmp= r.getdata();
				break;
			}
			found=search(r,val);
		}
		return found;
	}
	String[][] temp_in,temp_pre,temp_pos;
	int cnt_in=0,cnt_pre=0,cnt_pos=0;
	public String[][] inorder()
	{
		temp_in=new String[countnodes()][2];
		cnt_in=0;
		inorder(root);
		return temp_in;
	}
	private void inorder(BSTNode_str r)
	{
		if(r!=null)
		{
			inorder(r.getleft());
			//System.out.println(r.getdata()[0]+" : "+r.getdata()[1]);
			temp_in[cnt_in][0]=r.getdata()[0];
			temp_in[cnt_in][1]=r.getdata()[1];
			cnt_in++;
			inorder(r.getright());
		}
	}
	
	public String[][] preorder()
	{
		temp_pre=new String[countnodes()][2];
		cnt_pre=0;
		preorder(root);
		return temp_pre;
	}
	private void preorder(BSTNode_str r)
	{
		if(r!=null)
		{
			//System.out.println(r.getdata()[0]+" : "+r.getdata()[1]);
			temp_pre[cnt_pre][0]=r.getdata()[0];
			temp_pre[cnt_pre][1]=r.getdata()[1];
			cnt_pre++;
			preorder(r.getleft());
			preorder(r.getright());
		}
	}
	
	public String[][] postorder()
	{
		temp_pos=new String[countnodes()][2];
		cnt_pos=0;
		postorder(root);
		return temp_pos;
	}
	private void postorder(BSTNode_str r)
	{
		if(r!=null)
		{
			postorder(r.getleft());
			postorder(r.getright());
			//System.out.println(r.getdata()[0]+" : "+r.getdata()[1]);
			temp_pos[cnt_pos][0]=r.getdata()[0];
			temp_pos[cnt_pos][1]=r.getdata()[1];
			cnt_pos++;
		}
	}
}

public class BinaryTree_string {
	
	BST_str bst;
	public BinaryTree_string()
	{
		bst=new BST_str();
	}
	
	public void insert(int i, String data)
	{
		bst.insert(i, data);
	}
	public void delete(int i)
	{
		bst.delete(i);
	}
	public String[] search(int i)
	{
		return bst.search(i);
	}
	
	public String[][] getdata(int i)
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
