/***
 * Class to model the entity Date
 * Caterina Black
 * version 0.1
 * Date of creation: February 6, 2022
 * Last Date Modified: February 10, 2022
 */

public class Date{
    private int month;
    private int day;
    private int year;

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
        return month + "/" + day + "/" + year;
    }

    
}