/***
 * Class to model the entity Contact
 * Caterina Black
 * version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 20, 2023
 */

public class Contact implements Comparable<Contact>{
    private String name;
    private String phone;
    private String email;

    /***
	 * Constructor with three parameters
	 * @param	name for the name of the contact
	 * @param	phone for the phone number of the contact
     * @param   email for the email of the contact
	 */
    public Contact(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /***
	 * Getter for the name of contact
	 * @param	no parameters
	 * @return	the value of the data member name
	 */
    public String getName(){ return name;}
    /***
	 * Getter for the phone number of contact
	 * @param	no parameters
	 * @return	the value of the data member phone
	 */
    public String getPhone(){ return phone;}
    /***
	 * Getter for the email of contact
	 * @param	no parameters
	 * @return	the value of the data member email
	 */
    public String getEmail(){ return email;}
    /**
     * Setter for the name of contact
	 * @param	name to set the data member name
	 * no return value
     */
    public void setName(String name){ this.name = name;}
    /**
     * Setter for the phone number of contact
	 * @param	phone to set the data member phone
	 * no return value
     */
    public void setPhone(String phone){ this.phone = phone;}
    /**
     * Setter for the email of contact
	 * @param	email to set the data member email
	 * no return value
     */
    public void setEmail(String email){ this.email = email;}

    /***
	 * Method to get the contact info as a string
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return String.format("%-30s\t%-20s\t%-20s", name, phone, email);
    }

    /***
	 * Checks whether the contact names are equal
	 * @param	o creates instance of contact for comparison
	 * @return	true or false
	 */
    public boolean equals(Object o){
        if(o instanceof Contact){;
            return ((Contact) o).getName().equals(name);
        }
        return false;
    }

    /***
	 * Compares the contact names
	 * @param	c creates instance of contact for comparison
	 * @return	int based on comparison
	 */
    public int compareTo(Contact c){
        return name.compareTo(c.getName());
    }
}