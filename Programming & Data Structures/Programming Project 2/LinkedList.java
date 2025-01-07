/***
 * Class to model the entity LinkedList
 * Caterina Black
 * version 0.1
 * Date of creation: April 13, 2023
 * Last Date Modified: April 21, 2023
 */

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
    Linked List data structure (Singly Linked)
 */
public class LinkedList<E>{
    // Data members: references to the head and tail nodes
	private Node head, tail;
    // data member: size of the list
	int size;
    /**
        Inner class Node
     */ 
	private class Node{
		E value;
		Node next, previous;
        /**
            Constructor with one parameter
            sets the value and initializes next to null
            @param initialValue the value of the node
         */
		Node(E initialValue){
			value = initialValue; 
            next = null;
            previous = null;
		}
	}
    /**
        default constructor
        sets the head and tail to null and size to 0
     */ 
	public LinkedList() { 
		head = tail = null;
		size = 0;
	}

    /**
        Adding an item at the head of the list
        @param value of the node to be added
        @return true if the node was added successfully
    */
    public boolean addFirst(E value) {
		Node newNode = new Node(value);
		if(head == null) {
            head = tail = newNode; 
        }
		else { 
            newNode.next = head;
            newNode.previous = null;
			head = newNode;
		}
		size++; 
        return true;
    }
    /**
        Adding an item at the end of the list
        @param value of the node to be added
        @return true if the node was added successfully
    */
    public boolean addLast(E value) {
		Node newNode = new Node(value);
		if(head == null) { 
            head = tail = newNode; 
        }
		else { 
            tail.next = newNode; 
            newNode.previous = tail;
            tail = newNode; 
        }
		size++; 
        return true;
    }
    /**
        Adding an item at the tail of the list (inherited from Collection)
        @param value of the node to be added
        @return true if the node was added successfully
    */
    public boolean add(E value) {
		return addLast(value);
    }
    /**
        Retrieving the value of the first node in the list
        @return the value of the node at the head of the list
        @throws NoSuchElementException if the list is empty
        time complexity: O(1)
     */
    public E getFirst() {
		if (head == null)
			throw new NoSuchElementException();
		return head.value;
    }
    /**
        Retrieving the value of the last node in the list
        @return the value of the node at the tail of the list
        @throws NoSuchElementException if the list is empty
        time complexity: O(1)
     */
    public E getLast() {
		if (head == null)
			throw new NoSuchElementException();
		return tail.value;
    } 
    /**
        Removing the first node from the list
        @return true if the node was removed successfully
        @throws NoSuchElementException if the list is empty
     */
    public boolean removeFirst() {
		if (head == null) 
            throw new NoSuchElementException();
		head = head.next;
		if(head == null)
            tail = null;
		size--; 
        return true;
    }
    /**
        Removing the last node from the list
        @return true if the node was removed successfully
        @throws NoSuchElementException if the list is empty
    */
    public boolean removeLast() {
        if (head == null)
            throw new NoSuchElementException();
        if (size == 1)
            return removeFirst();
        
        //previous.next = null;
        Node newTail = tail.previous;
        newTail.next = null;
        tail = newTail;
        size--;
        return true;
    }
    /**
        Get the value of the node at position index
        @param index of the node being accessed
        @return the value of the node at index
        @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
        time complexity: O(n)
     */
    public E get(int index){
        if(index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        if(index == 0){
            return getFirst();
        }
        if(index == size-1){
            return getLast();
        }
        Node node = head;
        for(int i=0; i<index; i++){
            node = node.next;
        }
        return node.value;
    }
    /**
        Set the value of the node at position index
        @param index of the node being modified
        @return the old value of the modified node
        @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
        time complexity: O(n)
     */
    public E set(int index, E value){
        if(index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        E old;
        if(index == 0){
            old = head.value;
            head.value = value;
            return old;
        }
        if(index == size-1){
            old = tail.value;
            tail.value = value;
            return old;
        }
        Node node = head;
        for(int i=0; i<index; i++){
            node = node.next;
        }
        old = node.value;
        node.value = value;
        return old;
    }

    /**
        @override toString from class Object
        @return formatted string with all the values in the list
        time complexity: O(n)
     */
    public String toString() {
		String output = "[";
		Node node = head;
		while(node != null) {
			output += node.value + " ";
			node = node.next;
		}
		output += "]";
		return output;
    }
    /**
        clear the list
        sets head and tail to null and size to 0
        time complexity: O(1)
     */
    public void clear() {
        head = tail = null; 
        size = 0; 
    }
    /**
        check if the list is empty
        @return true if the list is empty, false otherwise
        time complexity: O(1)
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    /**
        get the size of the list
        @return the number of nodes in the list
        time complexity: O(1)
     */
    public int size() {
        return size; 
    } 
    /**
        Get an iterator for the list
        @return an iterator object pointing to the first node in the list
        time complexity: O(1)
     */
    public Iterator<E> iterator(){
		  return new LinkedListIterator();
    }
    /**
        Inner class that implements the interface Iterator
     */
    private class LinkedListIterator implements Iterator<E>{
        // data member: reference to the node referenced by the iterator
		private Node current = head;
        /**
            check if the iterator has a next node
            @return true if the iterator has access to the next node, false otherwise
            time complexity: O(1)
         */
		public boolean hasNext() {
			return (current != null);
		}
        /**
            get the value of the current node and move the iterator to the next node
            @return the value of the current node
            time complexity: O(1)
         */
	    public E next() {
            if(current == null)
			  throw new NoSuchElementException();
			E value = current.value;
			current = current.next; 
            return value;
		}
    }
    private class LinkedListListIterator implements ListIterator<E>{
        // data member: reference to the node referenced by the iterator
		private Node current = head;
        /**
            check if the iterator has a next node
            @return true if the iterator has access to the next node, false otherwise
            time complexity: O(1)
         */
		public boolean hasNext() {
			return (current != null);
		}
        /**
         * iterates through list to get current value at specified index
         * @param index value iterated to
         * @return value of index
         * O(index)
         */
        public E LLLIterator(int index){
            current = head;
            for(int i = 0; i < index; i++){
                current = current.next;
            }
            E value = current.value;
            return value;
        }
        /**
         * iterator that returns value of head
         * @param no
         * @return head node
         * O(1)
         */
        public E LLLIterator(){
            current = head;
            E value = current.value;
            return value;
        }
        /**
            get the value of the current node and move the iterator to the next node
            @return the value of the current node
            time complexity: O(1)
         */
	    public E next() {
            if(current == null)
			  throw new NoSuchElementException();
			E value = current.value;
			current = current.next; 
            return value;
		}
        /**
            check if the iterator has a previous
            @return true if the iterator has access to the previous node, false otherwise
            time complexity: O(1)
         */
        public boolean hasPrevious(){
            return (current != null); 
        }
        /**
         * get the value of the current node and move the iterator to the previous node
         * @return the value of the current node
         */
        public E previous(){
            if(current == null){
                throw new NoSuchElementException();
            }
            E value = current.value;
            current = current.previous;
            return value; //returns previous node which is new current and moves the iterator to the previous node
        }

        /**
        Method to add a new item at the end of the list becomes unsopported operation exception
        @param value the value of the item to be added
        */
        public void add(E value){ throw new UnsupportedOperationException();}
        /**
        Method to remove a new item at the end of the list becomes unsopported operation exception
        @param  none
        */
        public void remove(){ throw new UnsupportedOperationException();}
        /** 
         * Method to Set the value of the element becomes unsupported operation exception
         * @param value the value of the item to be set
        */
        public void set(E value){ throw new UnsupportedOperationException();}
        /**
         * Method to return value of next index becomes unsupported operation exception
         * @param  none
         */
        public int nextIndex(){ throw new UnsupportedOperationException();}
        /**
         * Method to return value of the previous index becomes unsopported operation exception
         * @param  none
         */
        public int previousIndex(){ throw new UnsupportedOperationException();}
    }
    
    /**
        Method to get a bidirectional iterator for the list
        @return an iterator object pointing to the first node of the list
        O(1)
     */
    public ListIterator<E> listIterator(){
        LinkedListListIterator iter = new LinkedListListIterator();
        iter.LLLIterator();
        return iter;
    }
    /**
        Method to get a bidirectional iterator for the list
        @param index where the iterator should start
        @return the iterator object pointing to the element at index
        @throws ArrayIndexOutOfBoundsExeption if index is less than 0 or index is greater than size
        O(index)
     */
    public ListIterator<E> listIterator(int index){
        if((index < 0) || (index > size)){
            throw new ArrayIndexOutOfBoundsException();
        }
        LinkedListListIterator iter = new LinkedListListIterator();
        iter.LLLIterator(index);
        return iter;
    }
}