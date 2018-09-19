import java.io.*;

/**
*@author Richa Rawat s3671659
*
**/
public class BstMultiset<T extends Comparable<T>> extends Multiset<T>
{
	Node root; 
	
	public BstMultiset() 
	{
		//constructor of class BstMultiset
		root = null;
	} // end of BstMultiset()

	public void add(T item) 
	{
		//empty bst
		if(root == null)
			root = new Node(item);
		else
			//insert new node
			root.insertNode(item);
	} // end of add()

	public int search(T item) 
	{
		//empty bst
		if(root == null)
			return 0;
		else 
		{
			Node node = root.searchNode(item);
			//search not success
			if(node == null) 
				return 0;
			//search not success
			else 
				
				return node.getCount();
		}
	} // end of add()


	public void removeOne(T item) 
	{
		// empty bst
		if(root == null) 
			return;
		//search for removal
		Node node = root.searchNode(item);
		
		//search not success
		if(node == null)
			return;
		//more occurrence
		if(node.count > 1) 
		{//delete
			node.count--;

			return;
		}
		//if the search occurs only once
		else 
		{
			Node parent = node.getParent();
			//the root does not have a parent
			boolean isRoot = (parent == null);
			
			boolean isleftChild = (!isRoot && (node == node.getParent().getLeft()));
			
			//if it is a leaf node
			if(node.isLeaf()) 
			{
				if(isRoot) 
					//delete the root
					this.root = null;
				//if the node to be deteled is only a leaf node but not the root
				else 
				{//if the node is the left child of its parent
					if(isleftChild)
						node.getParent().setLeft(null);
					else
						node.getParent().setRight(null);
				}
			}
			//if the node is not a leaf 
			else 
			{//and it does not have a right child
				if(node.getRight() == null) 
				{
					//if the node to be deleted is the root 
					if(isRoot)
					{	this.root = node.getLeft();
						this.root.setParent(null);
					}
					//if the node to be deleted is not the root 
					else
					{
						if(isleftChild) 
							node.getParent().setLeft(node.getLeft());
						else
							node.getParent().setRight(node.getLeft());
						
						node.getLeft().setParent(node.getParent());
					}
				}
				//rightchild exists
				else 
				{
					Node min = node.getRight().getRightMin();
					//if the minimum node is a leaf
					if(min.isLeaf()) 
					{
						node.exchange(min);
						
						//if the min node is the left child of parent
						if(min.getParent().left == min)
					
							min.getParent().setLeft(null);
					
						else
							min.getParent().setRight(null);
						
						//if the node to be deleted is the root node
						if(isRoot) 
							this.root = node;
					}
					// not a leaf
					else 
					{
						node.exchange(min);
						//obtain parent
						Node minParent = min.getParent();
						
						if(minParent.getLeft() == min) 
							minParent.setLeft(min.getRight());
						else 
							minParent.setRight(min.getRight());
						// right child exists
						if(min.getRight()!=null)
							min.getRight().setParent(minParent);
					}
				}
			}
		}
	} // end of removeOne()
	
	public void removeAll(T item) 
	{
		if(root == null) 
			return;
		else 
		{
			Node node = root.searchNode(item);
			
			if(node == null)
				return;
			else 
			{
				Node parent = node.getParent();
				boolean isRoot = (parent == null);
				
				boolean isleftChild = (!isRoot && (node == node.getParent().getLeft()));
				
				if(node.isLeaf()) 
				{
					if(isRoot) 
						this.root = null;
					else 
					{
						if(isleftChild) 
							node.getParent().setLeft(null);
						else
							node.getParent().setRight(null);
					}
				}
				else 
				{
					if(node.getRight() == null) 
					{
						if(isRoot)
						{
							this.root = node.getLeft();
							this.root.setParent(null);
						}
						else
						{
							if(isleftChild) 
								node.getParent().setLeft(node.getLeft());
							else
								node.getParent().setRight(node.getLeft());
							
							node.getLeft().setParent(node.getParent());
						}
					}
					else 
					{
						Node min = node.getRight().getRightMin();
						
						if(min.isLeaf()) 
						{
							node.exchange(min);
							
							if(min.getParent().left == min)
								min.getParent().setLeft(null);
							else
								min.getParent().setRight(null);
							
							if(isRoot) 
								this.root = node;
						}
						else 
						{
							node.exchange(min);
							
							Node minParent = min.getParent();
							
							if(minParent.getLeft() == min) 
							{
								minParent.setLeft(min.getRight());
								
								if(min.getRight()!=null)
									min.getRight().setParent(minParent);
							}
							else 
							{
								minParent.setRight(min.getRight());
								
								if(min.getRight()!=null)
									min.getRight().setParent(minParent);
							}
						}
					}
				}
			}
		}
	} // end of removeAll()

	/**
	 * inorder traversal
	 */
	public void print(PrintStream out)
	{
		Node node = root;
		
		if (node != null)
			inOrderTraversal(node, out);
	}// end of print()

	
	 // inorder Traversal of the BST
	 
	private void inOrderTraversal(Node node, PrintStream out)
	{
	    if(node.getLeft() != null)
	    	inOrderTraversal(node.getLeft(), out);
	    
	    out.println(node.getItem() + printDelim + node.getCount());
	    
	    if(node.getRight() != null)
	    	inOrderTraversal(node.getRight(), out);
	}
	
	private class Node
	{
		private T item;
		private Node parent;
		private Node left;
		private Node right;
		private int count;
		
		public Node(T item) 
		{
			this.item = item;
			parent = null;
			left = null;
			right = null;
			count = 1;
		}
		
		public T getItem() 
		{
			return item;
		}

		public void setItem(T item) 
		{
			this.item = item;
		}
		
		public Node getParent() 
		{
			return parent;
		}

		public void setParent(Node parent) 
		{
			this.parent = parent;
		}

		public Node getLeft() 
		{
			return left;
		}

		public void setLeft(Node left) 
		{
			this.left = left;
		}

		public Node getRight()
		{
			return right;
		}

		public void setRight(Node right) 
		{
			this.right = right;
		}

		public int getCount() 
		{
			return count;
		}

		public void setCount(int count) 
		{
			this.count = count;
		}

		public boolean isLeaf()
		{
			return (this.getLeft() == null && this.getRight() == null);
		}
		
		public void insertNode(T item) 
		{
			int resultset = this.getItem().compareTo(item);
			//if values are equal
			if(resultset == 0)
				//increment the count
				this.count++;
			else if(resultset > 0) 
			{
				if(this.getLeft() != null) 
					this.getLeft().insertNode(item);
				//no left child
				else 
				{	//instantiate a new node 
					Node left = new Node(item);
					this.setLeft(left);
					left.setParent(this);
				}
			}
			else 
			{
				if(this.getRight() != null)
					//call by right child of the subtree
					this.getRight().insertNode(item);
				else 
				{   //instantiate a new node 
					Node right = new Node(item);
					this.setRight(right);
					right.setParent(this);
				}
			}
		}

		public Node searchNode(T item) 
		{	int resultset = this.getItem().compareTo(item);
			//two values are equal
			if(resultset == 0) 
				return this;
			else if(resultset > 0 )
			{	if(this.getLeft() != null) 	
					return this.getLeft().searchNode(item);
				else
					//search not success
					return null;
			}
			else 
			{
				if(this.getRight()!=null) 
					// search the right subtree of its right child
					return this.getRight().searchNode(item);
				//no right child
				else 
					//search not success
					return null;
			}
		}
		
		public Node getRightMin() 
		{//if left child exist
			if(this.getLeft() != null)
				//search left child in the left subtree 
				return this.getLeft().getRightMin();
			//no left child
			else 
				return this;
		}
		
		public void exchange(Node min) 
		{//most minimum node is put in the left subtree
			Node temp = copy();
			
			this.setItem(min.getItem());
			this.setCount(min.getCount());
			
			min.setItem(temp.getItem());
			min.setCount(temp.getCount());
		}
		//saving to a temp node
		public Node copy() 
		{
			Node temp = new Node(this.getItem());
			
			temp.setCount(this.getCount());
			
			temp.setParent(this.getParent());
			
			temp.setLeft(this.getLeft());
			
			temp.setRight(this.getRight());
			
			return temp;
		}
	}
} // end of class BstMultiset