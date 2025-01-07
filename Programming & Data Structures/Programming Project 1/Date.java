/***
 * Class to model the entity Date
 * Caterina Black
 * version 0.1
 * Date of creation: February 6, 2023
 * Last Date Modified: February 10, 2023
 */

public class Date implements Comparable<Date>{
    private int month;
    private int day;
    private int year;

    /***
     * Constuctor with no parameters
     */
    public Date(){
        super();
        month = 0;
        day = 0;
        year = 0;
    }

    /***
	 * Constructor with four parameters
	 * @param	date for the date of the date
	 */
    public Date(String date) throws InvalidDateTimeException{
       String regex = "\\d{2}/\\d{2}/\\d{4}";
       if(date.matches(regex)){
            String[] dateItems = date.split("/");
            month = Integer.parseInt(dateItems[0]);
            day = Integer.parseInt(dateItems[1]); 
            year = Integer.parseInt(dateItems[2]);
       }
    }

    /***
	 * Getter for the month in date
	 * @param	no parameters
	 * @return	the value of the data member month
	 */
    public int getMonth(){
        return month;
    }

    /***
	 * Getter for the day in date
	 * @param	no parameters
	 * @return	the value of the data member day
	 */
    public int getDay(){
        return day;
    }

    /***
	 * Getter for the year in date
	 * @param	no parameters
	 * @return	the value of the data member year
	 */
    public int getYear(){
        return year;
    }

    /***
	 * Setter for the month in date
	 * @param	month to set the data member month
	 * no return value
	 */
    public void setMonth(int m) throws InvalidDateTimeException{
        month = m;
    }

    /***
	 * Setter for the day in date
	 * @param	day to set the data member day
	 * no return value
	 */
    public void setDay(int day) throws InvalidDateTimeException{
        this.day = day;
    }

    /***
	 * Setter for the year in date
	 * @param	year to set the data member year
	 * no return value
	 */
    public void setYear(int year) throws InvalidDateTimeException{
        this.year = year;
    }

    /***
	 * Method to get the Date information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return String.format("%02d/%02d/%04d", month, day, year);
    }

    /***
	 * Method to compare events based on date
	 * @param    d grabs date of event to compare them
	 * @return   returns 1 if date of event is greater than event being compared, 0 if it equal, and -1 if date of event is less than event being compared to it
	 */
    public int compareTo(Date d){
        if(d.getYear() > this.getYear()){ 
            return 1;
        } else if(d.getYear() == this.getYear()){
            if(d.getMonth() > this.getMonth()){
                return 1;
            } else if(d.getMonth() == this.getMonth()){
                if(d.getDay() > this.getDay()){
                    return 1;
                } else if(d.getDay() == this.getDay()){
                    return 0;
                } else if(d.getDay() < this.getDay()){
                    return -1;
                }
            } else if(d.getMonth() < this.getMonth()){
                return -1;
            }
        } else if(d.getYear() < this.getYear()){
            return -1;
        }
        return 0;
    }
}