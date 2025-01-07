/***
 * Class to model the entity Appointment
 * Caterina Black
 * version 0.1
 * Date of creation: January 30, 2023
 * Last Date Modified: January 30, 2023
 */
public class Appointment extends Event{
    private String contact;


    /***
     * Constructor with no parameters
     */
    public Appointment(){
        super(); //CHECK 
        contact = "none";
    }

    /***
	 * Constructor with four parameters
	 * @param	description for the description of the Appointment
	 * @param	location for the location of the Appointment
	 * @param	date for the date of the Appointment
	 * @param	time for the time of the Appointment
     * @param   contact for the contact of the Appointment
	 */
    public Appointment(String description, String location, String date, String time, String contact) throws InvalidDateTimeException{
        super(description, location, date, time);
        this.contact = contact;
    }

    /***
	 * Getter for the contact of the appointment
	 * @param	no parameters
	 * @return	the value of the data member contact
	 */
    public String getContact(){
        return contact;
    }

    /***
	 * Setter for the contact of the appointment
	 * @param	contact to set the data member contact
	 * no return value
	 */
    public void setContact(String contact){
        this.contact = contact;
    }

    //returns type of event created in this subclass
    @Override
    public String getType(){
        return "Appointment";
    }

    /***
	 * Method to get the Appointment information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        String out = super.toString();
        out += String.format("\t%s", getContact());
        return out;
    }
}