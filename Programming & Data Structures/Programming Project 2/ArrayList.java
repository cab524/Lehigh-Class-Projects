/***
 * Class to model the entity ArrayList
 * Caterina Black
 * version 0.1
 * Date of creation: April 13, 2023
 * Last Date Modified: April 21, 2023
 */

import java.util.Iterator;
import java.util.ListIterator;

/**
    Generic class to impelment an array based list
 */
public class ArrayList<E>{
   // data member: array for the list elements
   private E[] elements;
   // data member: size of the list
   private int size;
   /**
        Default constructor creates the array with a default length of 10 and sets size to 0
    */
   public ArrayList() {
	   elements = (E[]) new Object[10];
	   size = 0;
   }
   /**
        Constructor with one parameter creates the array with length equal to capacity and sets size to 0
        @param capacity length of the arrat elements
    */
   public ArrayList(int capacity) {
     elements = (E[]) new Object[capacity];
     size = 0;
   }
   /**
        Method to add a new item at the end of the list
        @param item the value of the item to be added
        @return true if item was added successfully, false otherwise
    */
    public boolean add(E item) {
		return add(size, item);	// adding at the first free index
    }
    /**
        Method to add a new item a given position index
        @param index the position where item should be added
        @param item the value of the element to be added
        @return true if item was added successfully, false otherwise
     */
    public boolean add(int index, E item){
		if(index > size || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		ensureCapacity();
		for(int i=size-1; i>=index; i--){
			elements[i+1] = elements[i];
        }
		elements[index] = item;
		size++;
		return true;
    }
    /**
        Get the value of the element at index
        @param index of the element being accessed
        @return the value of the element at index
        @throws ArrayIndexOutofBounds if the index is not valid
     */
    public E get(int index) {
		  checkIndex(index);
		  return elements[index];
    }
    /**
        Set the value of the element at index
        @param index of the element being modified
        @param value new value of the element at index
        @return the old value of the element at index
     */
    public E set(int index, E value) {
		  checkIndex(index);
		  E oldItem = elements[index];
		  elements[index] = value;
		  return oldItem;
    }
    /**
        Get the size of the list
        @return the number of elements in the list
     */
    public int size() { 
      return size; 
    }
    /**
        Clear the list by setting size to 0
     */
    public void clear() { 
      size = 0; 
    }
    /**
        Predicate to check if the list is empty
        @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() { 
      return (size == 0);
    }
   /**
        Remove an object from the list
        @param o the value of the element to be removed
        @return true if an element with value o is found and removed, false if no element has the value o
    */
    public boolean remove(Object o) {
      E item = (E) o; // casting down o to type E
      for(int i=0; i<size; i++)
		    if(elements[i].equals(item)){
            remove(i);
            return true;
        }
      return false;
    }
    /**
        Remove the element at a given index
        @param index the position of the element to be removed
        @return true if the elements was removed successfully, false otherwise
     */
    public boolean remove(int index) {
      checkIndex(index);
      E item = elements[index];
      for(int i=index; i<size-1; i++)
			  elements[i] = elements[i+1];
      size--;
      return true;
    }

    /**
        Search method in the list
        @param o the value of the element being searched
        @return true if an element with value o is found, false otherwise
     */
    public boolean contains(Object o){
        E value = (E) o;
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            if(iter.next().equals(value)){
                return true;
            }
        }
        return false;
    }

    /**
        Resize the length of the array elements to the size of the list
     */
    public void trimToSize() {
		  if (size != elements.length) {
			    E[] newElements = (E[]) new Object[size];// capacity = size
			    for(int i=0; i<size; i++)
				    newElements[i] = elements[i];
			    elements = newElements;
		  }
    }
   /**
        Grow the length of the array elements by 1.5 if the list is full
    */
    private void ensureCapacity() {
	    if(size >= elements.length) {
          int newCap = (int) (elements.length * 1.5);
		      E[] newElements = (E[]) new Object[newCap];
		      for(int i=0; i<size; i++)
				    newElements[i] = elements[i];
		      elements = newElements;
	    }
    }
    /**
        Check if the index is valid
        @param index to be checked
        @throws ArrayIndexOutOFBounds is indes is out of bounds
     */
    private void checkIndex(int index){
		  if(index < 0 || index >= size)
			    throw new ArrayIndexOutOfBoundsException(
              "Index out of bounds. Must be between 0 and "+(size-1));
    }
    /**
        @override toString from class Object
        @return a formatted string containing the elements of the list
     */
    public String toString() {
		  String output = "[";
		  for(int i=0; i<size-1; i++)
			    output += elements[i] + " ";
		  output += elements[size-1] + "]";
		  return output;
    }
    /**
        @override iterator() from the interface Collection
        @return iterator object positioned at the beginning of the list
     */
    public Iterator<E> iterator(){
		  return new ArrayIterator();
    }
    /**
        Inner class to implement the interface Iterator<E>
     */
    private class ArrayIterator implements Iterator<E>{
        // data member current: the index of the element at which the iterator is pointing
	    private int current = 0;
        /**
            @return true if current did not reach the end of the list, false otherwise
         */
	    public boolean hasNext() { 
            return current < size; 
        }
        /**
            @return the value of the current element and moves the index current to the next element
         */
	    public E next() { 
            return elements[current++]; 
        }
    }

    private class ArrayArrayIterator implements ListIterator<E>{
        // data member current: the index of the element at which the iterator is pointing
	    private int current = 0;
        /**
            @return true if current did not reach the end of the list, false otherwise
         */
	    public boolean hasNext() { 
            return current < size; 
        }
        /**
            @return the value of the current element and moves the index current to the next element
            O(1)
         */
	    public E next() { 
            return elements[current++]; 
        }
        /**
            @return true if current is not the first value, false otherwise
         */
        public boolean hasPrevious(){
            return current > 0;
        }
        /**
            @return the value of the current element and moves the index current to the previous element
            O(1)
         */
        public E previous(){
            return elements[current--];
        }
        /**
            @return the value of the current element
            O(1)
         */
        public E ArrayListIterator(){
            return elements[current];
        }
        /**
            @return the value of the current element at specified index
            O(index)
         */
        public E ArrayListIterator(int index){
            for(int i = 0; i < index; i++){
                elements[current] = elements[current++];
            }
            return elements[current];
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
        Method listIterator with no parameters
        @return an iterator object pointing to the first element in the list
        O(1)
     */
    public ListIterator<E> listIterator(){
        ArrayArrayIterator iter = new ArrayArrayIterator();
        iter.ArrayListIterator();
        return iter;
    }
    /**
        Method listIterator with a parameter
        @param index the position where the the iterator object should be positioned
        @return an iterator object pointing to the element at index in the list
        @throws ArrayIndexOutOfBoundsException if index is less than 0 or index is greater than the size of the list
        O(index)
     */
    public ListIterator<E> listIterator(int index){
        if((index < 0) || (index > size)){
            throw new ArrayIndexOutOfBoundsException();
        }
        ArrayArrayIterator iter = new ArrayArrayIterator();
        iter.ArrayListIterator(index);
        return iter;
    }
    
}