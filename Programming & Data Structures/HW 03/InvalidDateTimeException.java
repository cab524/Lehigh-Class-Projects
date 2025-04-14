/***
 * Class to model the entity InvalidDateTimeException
 * Caterina Black
 * version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 8, 2023
 */

public class InvalidDateTimeException extends Exception{
    /**
    * Checks whether date is valid
    * @param  no parameters
    * @return  "invalid date format."
     */
    public InvalidDateTimeException(){
        super("Invalid Date Format.");
    }
    /**
    * Checks whether date is valid
    * @param  message to display data member message
    * @return  message
     */
    public InvalidDateTimeException(String message){
        super(message);
    }
}