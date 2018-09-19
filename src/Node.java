
public class Node<T> {

	//Node for the Linked list
T data;
int frequency;
Node<T> next;

Node (T d) {data=d; frequency=1; next=null;}

public T getData() {
	return data;
}


public void setData(T data) {
	this.data = data;
}


public int getFrequency() {
	return frequency;
}


public void setFrequency(int frequency) {
	this.frequency = frequency;
}


public Node<T> getNext() {
	return next;
}

public void setNext(Node<T> next) {
	this.next = next;
}
 
	}


