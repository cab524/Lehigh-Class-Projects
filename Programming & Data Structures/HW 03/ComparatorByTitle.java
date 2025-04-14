/***
 * Class to model the entity ComparatorByTitle
 * Caterina Black
 * version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 20, 2023
 */

import java.util.Comparator;
// extends Comparable<E>
public class ComparatorByTitle implements Comparator<Note>{
    /**
     * Comparator that compares title of notes
     * @param   note1 for the first note to be the base of comparison
     * @param   note2 for the second note to compare
     * @return  int based on comparison
     */
    public int compare(Note n1, Note n2){
        String note1 = n1.getTitle();
        String note2 = n2.getTitle();
        return note1.compareTo(note2);
    }
}