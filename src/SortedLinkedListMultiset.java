import java.io.*;
/**
 * @author Subbiksa Shanmugha Sundaram s3653207
 *
 **/


public class SortedLinkedListMultiset<T extends Comparable<T>> extends Multiset<T>
{
	private Node head;
	private Node tail;

	public SortedLinkedListMultiset() 
	{
		head = null;
		tail = null;
	} // end of SortedLinkedListMultiset()


	public void add(T item) 
	{//if the list is empty
		if(head == null) 
		{  //new node is created 
			head = new Node(item);
			//tail points to the head
			tail = head;
			return;
		}

		int headResultset = head.getItem().compareTo(item);
		//head item is compared and the result is stored
		if(headResultset == 0) 
		{ //call the method increaseCount to increment the value of count
				head.increaseCount();
				return;
		}
		//if the parameter is smaller than head's item  
		else if(headResultset > 0) 
		{////creation of new node
			Node insertion = new Node(item);
			//point to the next to the head
			insertion.setNext(head);
			head = insertion;
			return;
		}
		//tail is compared to the next item and stored in the tail result
		int tailResultset = tail.getItem().compareTo(item);
		////if the end result is zero 
		if(tailResultset == 0) 
		{//increaseCount is called to increment the count by 1
			tail.increaseCount();
			return;
		}
		else if(tailResultset < 0)
		{
			//insert a new node
			Node insertion = new Node(item);
			tail.setNext(insertion);
			tail = insertion;
			return;
		}
		else 
		{////point the curr reference to head
			Node curr = head;
			//start of while loop
			while(curr.hasNext()) 
			{	int nodeResultset = curr.getNext().getItem().compareTo(item);
			//the node result compared to the next item
				if(nodeResultset == 0) 
				{	curr.getNext().increaseCount();
					return;
				}
				else if(nodeResultset < 0) 
				{
					curr = curr.getNext();
				}
				else 
				{	Node insertion = new Node(item);
					insertion.setNext(curr.getNext());
					curr.setNext(insertion);
					return;
				}
			}
		}
	} // end of add()

	public int search(T item) 
	{	int tailResultset = tail.getItem().compareTo(item);
		if(tailResultset < 0)
			return 0;
		else 
		{//start from head
			Node curr = head;
			//present node is made to point to the head
			while(curr != null) 
			{
				int nodeResultset = curr.getItem().compareTo(item);
				if(nodeResultset == 0)
					return curr.getCount();
				else if(nodeResultset < 0)
					curr = curr.getNext();
				else
				{//searched item not found
					return 0;
				}
			}
		}
		return 0;
	} // end of add()


	public void removeOne(T item) 
	{
		int headResultset = head.getItem().compareTo(item);
		if(headResultset == 0) 
		{//if head has more than one occurrence
			if(head.getCount() > 1) 
				//remove one occurrence
				head.setCount(head.getCount() - 1);
			//if head only has one occurrence
			else
				head = head.getNext();
			return;
		}
		else if(headResultset > 0) 
			//not successful
			return;

		int tailResultset = tail.getItem().compareTo(item);
		if(tailResultset < 0)
			return;
		else 
		{//start from head
			Node curr = head;

			//while loop
			while(curr.hasNext()) 
			{	int nodeResultset = curr.getNext().getItem().compareTo(item);
				//if the two values are same
				if(nodeResultset == 0) 
				{	if(curr.getNext().getCount() > 1) 
					{
						curr.getNext().setCount(curr.getNext().getCount() - 1);
						break;
					}
					else 
					{	Node next = curr.getNext().getNext();
						curr.setNext(next);
						if(next == null) 
						tail = curr;
					}

					return;
				}
				else if(nodeResultset < 0) 
					//continue finding
					curr = curr.getNext();
				else 
					//unsuccesful
					break;
			}
		}
	} // end of removeOne()


	public void removeAll(T item) 
	{
		int headResultset = head.getItem().compareTo(item);

		if(headResultset == 0) 
		{
			head = head.getNext();
			return;
		}
		else if(headResultset > 0) 
			return;

		int tailResultset = tail.getItem().compareTo(item);

		if(tailResultset < 0)
			return;
		else 
		{
			Node curr = head;

			while(curr.hasNext()) 
			{
				int nodeResultset = curr.getNext().getItem().compareTo(item);

				if(nodeResultset ==  0) 
				{
					Node next = curr.getNext().getNext();
					curr.setNext(next);

					//if the node to be deleted is the tail
					if(next == null) 
						//Update the tail
						tail = curr;

					return;
				}
				else if(nodeResultset < 0) 
					curr = curr.getNext();
				else 
					//Item not found
					break;
			}
		}
	} // end of removeAll()


	public void print(PrintStream out) 
	{//start from head
		Node curr = head;

		//while the curr node is not null
		//execute this loop
		while(curr != null) 
		{
			//print the curr node's value
			out.println(curr.getItem() + printDelim + curr.getCount());
			//then move the curr node pointer to its next node
			curr = curr.getNext();
		}
	} // end of print()

	private class Node
	{
		private T item;
		private Node next;
		private int count;

		public int getCount() 
		{
			return count;
		}

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

		public Node getNext() 
		{
			return next;
		}

		public void setNext(Node next) 
		{
			this.next = next;
		}

		public boolean hasNext()
		{
			if(next == null)
				return false;
			else
				return true;
		}

		public void increaseCount() 
		{
			this.count++;
		}

		public void setCount(int count)
		{
			this.count = count;
		}
	}
} // end of class SortedLinkedListMultiset