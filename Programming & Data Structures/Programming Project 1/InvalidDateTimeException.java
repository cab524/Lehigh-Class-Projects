/***
 * Class to model the entity InvalidDateTimeException
 * Caterina Black
 * version 0.1
 * Date of creation: February 6, 2023
 * Last Date Modified: February 10, 2023
 */

public class InvalidDateTimeException extends Exception{
    public InvalidDateTimeException(){
        this("Invalid Date/Time");
    }

    //type of exception that prints a given message
    public InvalidDateTimeException(String msg){
        super(msg);
    }
}