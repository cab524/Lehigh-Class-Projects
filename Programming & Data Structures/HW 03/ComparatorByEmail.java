/***
 * Class to model the entity ComparatorByEmail
 * Caterina Black
 * version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 20, 2023
 */

import java.util.Comparator;

public class ComparatorByEmail implements Comparator<Contact>{
    /**
     * Comparator that compares emails of contacts
     * @param   contact1 for the first contact to be the base of comparison
     * @param   contact2 for the second contact to compare
     * @return  int based on comparison
     */
    public int compare(Contact c1, Contact c2){
        String contact1 = c1.getEmail();
        String contact2 = c2.getEmail();
        return contact1.compareTo(contact2);
    }
}