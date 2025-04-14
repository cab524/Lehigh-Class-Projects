/***
 * Class to model the entity Organizer
 * Caterina Black
 * version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 20, 2023
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class Organizer<E extends Comparable<E>>{
    private ArrayList<E> elements;
    private Comparator<E> comparator;

    /***
	 * Constructor with one parameter
	 * @param	cap for the capacity of array list
	 */
    public Organizer(int cap){
        elements = new ArrayList<>(cap);
        comparator = null;
    }

    /***
	 * Constructor with two parameters
	 * @param	cap for the capacity of array list
     * @param   comp for the comparator of array list
	 */
    public Organizer(int cap, Comparator<E> comp){
        elements = new ArrayList<>(cap);
        comparator = comp;
    }

    /**
     * Adds element to array list while keeping items sorted
     * @param   item the data member added to array list
     * @return  none
     */
    public void addElement(E item){ //add while keeping it sorted
        int i, c;
        for(i = 0; i < elements.size(); i++){
            if(comparator == null){
                c = ((Comparable<E>) item).compareTo(elements.get(i));
            } else {
                c = comparator.compare(item, elements.get(i));
            }
            if(c < 0){
                break;
            }
        }
        elements.add(i, item);

        /*elements.add(item);
        if(comparator != null){
            Collections.sort(elements,comparator);
        } else {
            Collections.sort(elements);
        }
        //may be the same?
        */
        
    } //while keeping it sorted
    
    /**
     * Finds element based on key
     * @param   key data member to search for
     * @return element to find again
     */
    public E findElement(E key){ 
        return findElement(key, 0, elements.size()-1);
    }

    /**
     * Finds element based on key
     * @param   key data member to search for
     * @param   low base index of array to look through
     * @param   high largest index of array to look through
     * @return element to find again (recursion)
     */
    public E findElement(E key, int low, int high){
        if(low > high){
            //System.out.println("Bye");
            return null;
        } else { 
            int mid = (low + high) / 2;
            if(key.compareTo(elements.get(mid)) == 0){
                //System.out.println("HI");
                return elements.get(mid);
            } else if(key.compareTo(elements.get(mid)) < 0){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            return findElement(key, low, high);
        }
        /*
        if(low < high){
            //check the mid
            //if the key is the mid return the key
            //else if the key is less than the mid, move to the left (high changes to mid-1)
            //else the key comes after the mid, move to the right (low changes to mid+1)
        } else {
            return null;
        }
        */


        //NOTES SORTED BY TITLE IS BACKWARD ALONG WITH EVERYTHING ELSE
    }
        

    /**
     * Removes element from array list 
     * @param   item the data member removed from array list
     * @return  removed item
     */
    public E removeElement(E item){
        elements.remove(item);
        return item;
    }

    /**
    * Setter for comparator and also sorts comparator
    * @param   c to set the data member comparator
    * @return none
     */
    public void setComparator(Comparator<E> c){ 
        comparator = c;
        elements.sort(comparator);
    }

    /***
	 * Method to get the array list info as a string
	 * @param  no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        String out ="";
        for(int i = 0; i < elements.size(); i++){
            // System.out.println(elements.get(i));
            out+= elements.get(i)+"\n";
        }
        return out;
        //return elements.toString();
    }


}