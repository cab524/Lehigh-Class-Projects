/***
 * Class to model the entity Meeting
 * Caterina Black
 * version 0.1
 * Date of creation: January 30, 2023
 * Last Date Modified: January 30, 2023
 */

public class Meeting extends Event{
    private String host;
    private int guests;

    //default constructor
    public Meeting(){
        super();
        host = "none";
        guests = 0;
    }

    /***
	 * Constructor with four parameters
	 * @param	description for the description of the meeting
	 * @param	location for the location of the meeting
	 * @param	date for the date of the meeting
	 * @param	time for the time of the meeting
     * @param   host for the host of the meeting
	 * @param   guests for the guests of the meeting
     */
    public Meeting(String description, String location, String date, String time, String host, int guests) throws InvalidDateTimeException{
        super(description, location, date, time);
        this.host = host;
        this.guests = guests;
    }

    /***
	 * Getter for the host of the meeting
	 * @param	no parameters
	 * @return	the value of the data member host
	 */
    public String getHost(){
        return host;
    }

    /***
	 * Getter for the guests of the meeting
	 * @param	no parameters
	 * @return	the value of the data member guests
	 */
    public int getGuests(){
        return guests;
    }

    /***
	 * Setter for the host of the meeting
	 * @param	host to set the data member host
	 * no return value
	 */
    public void setHost(String host){
        this.host = host;
    }

    /***
	 * Setter for the guests of the meeting
	 * @param	guests to set the data member guests
	 * no return value
	 */
    public void setGuests(int guests){
        this.guests = guests;
    }

    //returns type of event created in this subclass
    @Override
    public String getType(){
        return "Meeting";
    }

    /***
	 * Method to get the Appointment information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        String out = super.toString();
        out += String.format("\t%s\t%d", getHost(), getGuests());
        return out;
    }

}