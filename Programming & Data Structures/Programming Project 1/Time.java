/***
 * Class to model the entity Time
 * Caterina Black
 * version 0.1
 * Date of creation: February 6, 2023
 * Last Date Modified: February 10, 2023
 */

public class Time implements Comparable<Time>{
    private int hours;
    private int minutes;

    /***
     * Constuctor with no parameters
     */
    public Time(){
        super();
        hours = 0;
        minutes = 0;
    }

    /***
	 * Constructor with four parameters
	 * @param	date for the time of time
	 */
    public Time(String date) throws InvalidDateTimeException{
        String regex = "\\d{2}:\\d{2}";
        if(date.matches(regex)){
            String[] dateItems = date.split(":");
            hours = Integer.parseInt(dateItems[0]);
            minutes = Integer.parseInt(dateItems[1]); 
        }
    }

    /***
	 * Getter for the hours in time
	 * @param	no parameters
	 * @return	the value of the data member hours
	 */
    public int getHours(){
        return hours;
    }

    /***
	 * Getter for the hours in time
	 * @param	no parameters
	 * @return	the value of the data member minutes
	 */
    public int getMinutes(){
        return minutes;
    }

    /***
	 * Setter for the hours in time
	 * @param	hours to set the data member hours
	 * no return value
	 */
    public void setHours(int h) throws InvalidDateTimeException{
        hours = h;
    }

    /***
	 * Setter for the minutes in time
	 * @param	minutes to set the data member minutes
	 * no return value
	 */
    public void setMinutes(int m) throws InvalidDateTimeException{
        minutes = m;
    }

    /***
	 * Method to get the Time information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return String.format("%02d:%02d", hours, minutes);
    }

    /***
	 * Method to compare events based on time
	 * @param    t grabs time of event to compare them
	 * @return   returns 1 if time of event is greater than event being compared, 0 if it equal, and -1 if time of event is less than event being compared to it
	 */
    public int compareTo(Time t){
        if(t.getHours() > this.getHours()){
                        return 1;
        } else if(t.getHours() == this.getHours()){
            if(t.getMinutes() > this.getMinutes()){
                return 1;
            } else if(t.getMinutes() == this.getMinutes()){
                return 0;
            } else if(t.getMinutes() < this.getMinutes()){
                return -1;
            }
        } else if(t.getHours() < this.getHours()){
            return -1;
        }
        return 0;
    }
}