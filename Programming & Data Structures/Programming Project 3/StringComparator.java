/***
 * Class to model the entity StringComparator
 * Caterina Black
 * version 0.1
 * Date of creation: May 3, 2023
 * Last Date Modified: May 3, 2023
 */

import java.util.Comparator;

public class StringComparator implements Comparator<String>{
    /**
      * Compares 2 Strings based on length
      * @param   s1 data member s1 being compared
      * @param   s2 data memeber s2 being compared
      * @return  length of difference between strings
      */
    public int compare(String  s1, String s2){
        String[] items1 = s1.split("@");
        String[] items2 = s2.split("@");
        String word1 = items1[0];
        String word2 = items2[0];
        return word1.compareTo(word2);
    }
}