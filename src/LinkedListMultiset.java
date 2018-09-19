import java.io.*;
 /**
 *
 *@author Subbbiksa ShanmughaSundaram s3653207
 *@author Richa Rawat s3671659
 *
**/
public class LinkedListMultiset<T> extends Multiset<T>
{
	private Node head;
	//declaration
	
	public LinkedListMultiset() 
	{
		head = null;
	} // end of linkedlist
	
	//add method for linkedlist
	public void add(T item) 
	{
	    //if head is empty
		if(head == null) 
		{	
			head = new Node(item);
			return;
		}
		//case 2 is if the item in the head equals the value 
		if(head.itemEquals(item)) 
		{
			head.increaseCount();//call the method increaseCount
			return;
		}
		Node curr = head;
		
		// current node is made to point to the beginning of the list
		while(curr.hasNext()) 
		{
			//if the present node has any item next
			if(curr.getNext().itemEquals(item)) 
			{
				curr.getNext().increaseCount();
				return;
			}
			curr = curr.getNext();
			//points to the next node in the list
		}
			Node insertionNode = new Node(item);
			curr.setNext(insertionNode);
			//if not found new insertion is done
	} 
	//end of method add
	public int search(T value) 
	{
		Node curr = head;
		//if the present node is equal to the head
		while(curr != null) 
		{
			if(curr.itemEquals(value)) 
			{
				return curr.getCount();//call the getCount() function
			}
			//point to the next node of the present node
			curr = curr.getNext();
		}
		return 0;
	} 
	//end of method search	
	public void removeOne(T item) 
	{
		if(head == null) 
			//if the head is empty
			return;
		//else if it matches a value
		if(head.itemEquals(item)) 
		{
			if(head.getCount() > 1)
		
				//more occurrences of the same value in the list
				head.setCount(head.getCount()-1);
			else 
				//point to next node
				head = head.getNext();
			return;
		}
		
		Node curr = head;
		while(curr.hasNext()) 
		{
			//if the present node has next node to it
			if(curr.getNext().itemEquals(item)) 
			{
				if(curr.getNext().getCount() > 1) 
					
					//more instances
					curr.getNext().setCount(curr.getNext().getCount() - 1);
				else 
					curr.setNext(curr.getNext().getNext());
				break;
			}
			curr = curr.getNext();
		}
	} 	
	public void removeAll(T item) 
	{
		if(head == null) 
	
			//head is null condition is checked
			return;
		if(head.itemEquals(item)) 
		{
			head = head.getNext();
			//if the head contains another node next to it
			return;
		}
		Node curr = head;
		while(curr.hasNext()) 
			//if it contains nodes in the list
		{	if(curr.getNext().itemEquals(item)) 
			{	curr.setNext(curr.getNext().getNext());
				break;
				//breaks out of the while loop
			}
			curr = curr.getNext();
		}
	} // end of removeAll()
	public void print(PrintStream out) 
	{
		Node current = head;	
		while(current!=null) 
		{
			out.println(current.getItem() + printDelim + current.getCount());
			//print present node 
			current = current.getNext();
			//print next node
		}
	} // end of print()
	//start of class Node for the list
	class Node
	{
		private T item;
		private Node next;
		private int count;

		public Node(T item) 
		{
			this.item = item;
			count = 1;
			next = null;
		}

		public T getItem() 
		{
			return item;
		}

		public void setItem(T item) 
		{
			this.item = item;
		}
		public Node getNext() 
		{
			return next;
		}
		public void setNext(Node next) 
		{
			this.next = next;
		}
		public boolean itemEquals(T item) 
		{
			return this.getItem().equals(item);
		}
		public void increaseCount() 
		{
			this.count++;
		}
		public int getCount() 
		{
			return count;
		}
		public void setCount(int count)
		{
			this.count = count;
		}
		public boolean hasNext()
		{
			if(next == null)
				return false;
			else
				return true;
		}
	}
} // end of class LinkedList
 
