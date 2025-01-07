/***
 * Class to model the entity Event
 * Caterina Black
 * version 0.1
 * Date of creation: February 6, 2023
 * Last Date Modified: February 10, 2023
 */

public abstract class Event implements Comparable<Event>{
    //Data Members
    protected String description;
    protected String location;
    protected Time tim;
    protected Date dat;

    /***
     * Constructor with no parameters
     */
    public Event(){
        description = "none";
        location = "none";
        tim = new Time();
        dat = new Date();
    }

    /***
	 * Constructor with four parameters
	 * @param	d for the description of the event
	 * @param	l for the location of the event
     * @param	t for the time of the event
     * @param	dat for the date of the event
	 */
    public Event(String d, String l, String dat, String t) throws InvalidDateTimeException{
        description = d;
        location = l;
        tim = new Time(t);
        this.dat = new Date(dat);
    }

    /***
	 * Getter for the description of the event
	 * @param	no parameters
	 * @return	the value of the data member description
	 */
    public String getDescription(){
        return description;
    }

    /***
	 * Getter for the location of the event
	 * @param	no parameters
	 * @return	the value of the data member location
	 */
    public String getLocation(){
        return location;
    }

    /***
	 * Getter for the date of the event
	 * @param	no parameters
	 * @return	the value of the data member date
	 */
    public Date getDate(){
        return dat;
    }

    /***
	 * Getter for the time of the event
	 * @param	no parameters
	 * @return	the value of the data member time
	 */
    public Time getTime(){
        return tim;
    }

    /***
	 * Setter for the description of the event
	 * @param	d to set the data member description
	 * no return value
	 */
    public void setDescription(String d){
        description = d;
    }

    /***
	 * Setter for the location of the event
	 * @param	l to set the data member location
	 * no return value
	 */
    public void setLocation(String l){
        location = l;
    }

    /***
	 * Setter for the date of the event
	 * @param	d to set the data member date
	 * no return value
	 */
    public void setDate(Date d) throws InvalidDateTimeException{
        dat = d;
    }

    /***
	 * Setter for the time of the event
	 * @param	t to set the data member time
	 * no return value
	 */
    public void setTime(Time t) throws InvalidDateTimeException{
        tim = t;
    }

    public abstract String getType(); //gets type of subclasses

    /***
	 * Method to get the event information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        String out;
        out = String.format("%-20s\t%-20s\t%-20s\t%-10s\t%-5s", 
                            getType(), getDescription(), getLocation(), getDate(), getTime());
	    return out;
    }

    /***
	 * Method to compare events based on time and date
	 * @param    e grabs event to compare them
	 * @return   returns 1 if event is greater than event being compared, 0 if it equal, and -1 if event is less than event being compared to it
	 */
    public int compareTo(Event e){
        if(e.getDate().compareTo(this.getDate()) == 1){
            return 1;
        } else if(e.getDate().compareTo(this.getDate()) == 0){
            if(e.getTime().compareTo(this.getTime()) == 1){
                return 1;
            } else if(e.getTime().compareTo(this.getTime()) == 0){
                return 0;
            } else if(e.getTime().compareTo(this.getTime()) == -1){
                return -1;
            }
        } else if(e.getDate().compareTo(this.getDate()) == -1){
            return -1;
        }
        return 0;
    }
}