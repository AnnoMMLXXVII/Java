package main.java;

/*******************************/
/*
 * Student number: /*Student full name: /
 *******************************/

public class LinkedList<E> {

	private static class Node<T> {
		private T value;
		private Node<T> prev;
		private Node<T> next;

		private Node(T value, Node<T> prev, Node<T> next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}

	private Node<E> head;

	public LinkedList() {
		head = new Node<E>(null, null, null);
		head.prev = head;
		head.next = head;
	}

	public void addFirst(E element) {

		Node<E> before, after;

		before = head;
		after = before.next;
		before.next = new Node<E>(element, before, after);
		after.prev = before.next;

	}

	public E get(int pos) {

		Node<E> p;
		p = head;

		while (pos >= 0) {
			p = p.next;
			pos--;
		}

		return p.value;
	}
	/*******************************************************************/
//Do Not change anything above this line.

//Implement ONLY eliminateValues() after this line. Do Not write ANY other methods.

//Do Not make a call to ANY method above this line.

	/*******************************************************************/
	public void noFirstOrSimilar() {
		// you code here. You can't write code anywhere else.
		if (head == null) {
			return;
		}
		Node<E> temp = head; 				// Create another reference to the head
		E key = temp.next.value; 			// Store Head Value as Key
		while (temp.next.value != null) {	
			Node<E> local = temp;
			while (local != null) {
				local = local.next;
				if(local.value == null || local.value != key) {
					break;
				}
			}
			temp.next = local;
			temp = temp.next;
		}
	}

}










