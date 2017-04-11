package backEnd;

public class CIS163Q<T> {
	
	private Node<T> head;
	private Node<T> tail;
	private int size;

	public CIS163Q(){
		head = null;
		tail = null;
		size = 0;
	}

	private class Node<T>{
		protected T element;
		protected Node<T> next;
		
		protected Node(T element){
			this.element = element;
		}
	}

	public void enQ(T element){
		Node<T> t = new Node<T>(element);
		if(size==0){
			head = t;
			head.next = t;
			tail = t;
		} else {
			tail.next = t;
			tail = t;
		}
		size++;
	}

	public T deQ() throws EmptyQException{
		if(size == 0){
			throw new EmptyQException("The Q is empty");
		}
		Node<T> temp = head;
		head = head.next;
		size--;
		return temp.element;
	}
	
	public int size(){
		return size;
	}

}
