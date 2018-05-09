package mylibs;

/**
 * @program AVLTree
 * 
 * This program(as library) is designed to AVLTrees implementation
 * key points while using into your program:
 * 	1) dont forget to import this program
 * 	2) create object as AVLTree "some name" =new AVLTree();
 * 	3) functions : 
 * 		-insert(int,String) - insert(key,data)
 * 		-delete(int) - delete(key)
 * 		-getdata() - return type - String[][] 
 * 			String[][0]=key, String[][1]=data
 * 		-traverseInOrder() - to see console o/p
 * 
 * @author Nikhil Tanni
 * @project B-I-G
 * @version 1.0
 * 
 */

public class AVLTree {

    private static class Node {
        Node left, right;
        Node parent;
        String[] value ;
        int height = 0;

        public Node(String[] data, Node parent) {
            this.value = data;
            this.parent = parent;
        }

//        @Override
//        public String toString() {
//            return value + " height " + height + " parent " + (parent == null ?
//                    "NULL" : parent.value) + " | ";
//        }
        
        
        public String[] getvalue()
        {
        	return value;
        }
        public String getheight()
        {
        	return height+"";
        }
        public String getparent()
        {
        	return (parent==null ? "NULL" : parent.value+"");
        }

        void setLeftChild(Node child) {
            if (child != null) {
                child.parent = this;
            }

            this.left = child;
        }

        void setRightChild(Node child) {
            if (child != null) {
                child.parent = this;
            }

            this.right = child;
        }
    }

    private Node root = null;

    public void insert(int key, String data) {
        insert(root, key, data);
    }

    private int height(Node node) {
        return node == null ? -1 : node.height;
    }

    private void insert(Node node, int key, String value) {
    	String[] temp= {key+"",value};
        if (root == null) {
            root = new Node(temp, null);
            return;
        }
        
        
        if (key < Integer.parseInt(node.value[0])) {
            if (node.left != null) {
                insert(node.left, key, value);
            } else {
                node.left = new Node(temp, node);
            }

            if (height(node.left) - height(node.right) == 2) { //left heavier
                if (key < Integer.parseInt(node.left.value[0])) {
                    rotateRight(node);
                } else {
                    rotateLeftThenRight(node);
                }
            }
        } else if (key > Integer.parseInt(node.value[0])) {
            if (node.right != null) {
                insert(node.right, key, value);
            } else {
                node.right = new Node(temp, node);
            }

            if (height(node.right) - height(node.left) == 2) { //right heavier
                if (key > Integer.parseInt(node.right.value[0]))
                    rotateLeft(node);
                else {
                    rotateRightThenLeft(node);
                }
            }
        }

        reHeight(node);
    }

    private void rotateRight(Node pivot) {
        Node parent = pivot.parent;
        Node leftChild = pivot.left;
        Node rightChildOfLeftChild = leftChild.right;
        pivot.setLeftChild(rightChildOfLeftChild);
        leftChild.setRightChild(pivot);
        if (parent == null) {
            this.root = leftChild;
            leftChild.parent = null;
            return;
        }

        if (parent.left == pivot) {
            parent.setLeftChild(leftChild);
        } else {
            parent.setRightChild(leftChild);
        }

        reHeight(pivot);
        reHeight(leftChild);
    }

    private void rotateLeft(Node pivot) {
        Node parent = pivot.parent;
        Node rightChild = pivot.right;
        Node leftChildOfRightChild = rightChild.left;
        pivot.setRightChild(leftChildOfRightChild);
        rightChild.setLeftChild(pivot);
        if (parent == null) {
            this.root = rightChild;
            rightChild.parent = null;
            return;
        }

        if (parent.left == pivot) {
            parent.setLeftChild(rightChild);
        } else {
            parent.setRightChild(rightChild);
        }

        reHeight(pivot);
        reHeight(rightChild);
    }

    private void reHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private void rotateLeftThenRight(Node node) {
        rotateLeft(node.left);
        rotateRight(node);
    }

    private void rotateRightThenLeft(Node node) {
        rotateRight(node.right);
        rotateLeft(node);
    }

    public boolean delete(int key) {
        Node target = search(key);
        if (target == null) return false;
        target = deleteNode(target);
        balanceTree(target.parent);
        return true;
    }

    private Node deleteNode(Node target) {
        if (isLeaf(target)) { //leaf
            if (isLeftChild(target)) {
                target.parent.left = null;
            } else {
                target.parent.right = null;
            }
        } else if (target.left == null ^ target.right == null) { //exact 1 child
            Node nonNullChild = target.left == null ? target.right : target.left; 
            if (isLeftChild(target)) {
                target.parent.setLeftChild(nonNullChild); 
            } else {
                target.parent.setRightChild(nonNullChild);
            }
        } else {//2 children
            Node immediatePredInOrder = immediatePredInOrder(target);
            target.value = immediatePredInOrder.value;
            target = deleteNode(immediatePredInOrder);
        }

        reHeight(target.parent);
        return target;
    }

    private Node immediatePredInOrder(Node node) {
        Node current = node.left;
        while (current.right != null) {
            current = current.right;
        }

        return current;
    }

    private boolean isLeftChild(Node child) {
        return (child.parent.left == child);
    }

    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private int calDifference(Node node) {
        int rightHeight = height(node.right);
        int leftHeight = height(node.left);
        return rightHeight - leftHeight;
    }

    private void balanceTree(Node node) {
        int difference = calDifference(node);
        Node parent = node.parent;
        if (difference == -2) {
            if (height(node.left.left) >= height(node.left.right)) {
                rotateRight(node);
            } else {
                rotateLeftThenRight(node);
            }
        } else if (difference == 2) {
            if (height(node.right.right) >= height(node.right.left)) {
                rotateLeft(node);
            } else {
                rotateRightThenLeft(node);
            }
        }

        if (parent != null) {
            balanceTree(parent);
        }

        reHeight(node);
    }

    public Node search(int key) {
        return binarySearch(root, key);
    }

    private Node binarySearch(Node node, int key) {
        if (node == null) return null;

        if (key == Integer.parseInt(node.value[0])) {
            return node;
        }

        if (key < Integer.parseInt(node.value[0]) && node.left != null) {
            return binarySearch(node.left, key);
        }

        if (key > Integer.parseInt(node.value[0]) && node.right != null) {
            return binarySearch(node.right, key);
        }

        return null;
    }

    public void traverseInOrder() {
        //System.out.println("ROOT " + root.toString());
    	//String[] tmp=root.getvalue();
    	System.out.println("Root : ("+root.getvalue()[0]+" : "+root.getvalue()[1]+")");
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            //System.out.print(node.toString());
            String[] tmp=node.getvalue();
            System.out.print(" ("+tmp[0]+" : "+tmp[1]+")");
            inorder(node.right);
        }
    }
    
    public int countnodes()
    {
    	return countnodes(root);
    }
    public int countnodes(Node r)
    {
    	if(r==null)
    	{
    		return 0;
    	}
    	else
		{
			int l=1;
			l+=countnodes(r.left);
			l+=countnodes(r.right);
			return l;
		}
    }
    
    String[][] temp;
    int cnt=0;
    public String[][] getdata()
    {
    	temp=new String[countnodes()][2];
    	cnt=0;
    	getdata(root);
    	return temp;
    }
    public void getdata(Node r)
    {
    	if(r!=null)
    	{
    		getdata(r.left);
    		temp[cnt][0]=r.getvalue()[0];
    		temp[cnt][1]=r.getvalue()[1];
    		cnt++;
    		getdata(r.right);
    	}
    }

}
















