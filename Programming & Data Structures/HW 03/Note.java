/***
 * Class to model the entity Note
 * Caterina Black
 * version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 20, 2023
 */

public class Note implements Comparable<Note>{
    private Date date;
    private String title;
    private String description;

    /***
	 * Constructor with three parameters
	 * @param	date for the date of the note
	 * @param	title for the title of the note
     * @param   description for the description of the note
	 */
    public Note(Date date, String title, String description){
        this.date = date;
        this.title = title;
        this.description = description;
    }

    /***
	 * Getter for the date of note
	 * @param	no parameters
	 * @return	the value of the data member date
	 */
    public Date getDate(){ return date;}
    /***
	 * Getter for the title of note
	 * @param	no parameters
	 * @return	the value of the data member title
	 */
    public String getTitle(){ return title;}
    /***
	 * Getter for the description of note
	 * @param	no parameters
	 * @return	the value of the data member description
	 */
    public String getDescription(){ return description;}
    /**
     * Setter for the date of note
	 * @param	date to set the data member date
	 * no return value
     */
    public void setDate(Date date){ this.date = date;}
    /**
     * Setter for the title of note
	 * @param	title to set the data member title
	 * no return value
     */
    public void setTitle(String title){ this.title = title;}
    /**
     * Setter for the description of note
	 * @param	description to set the data member description
	 * no return value
     */
    public void setDescription(String description){ this.description = description;}

    /***
	 * Method to get the note info as a string
	 * @param  no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return String.format("%-10s\t%-20s\t%-20s", date, title, description);
    }

    /***
	 * Checks whether the note dates are equal
	 * @param	o creates instance of note for comparison
	 * @return	true or false
	 */
    public boolean equals(Object o){
        if(o instanceof Note){;
            return ((Note) o).getDate().equals(date);
        }
        return false;
    }

    /***
	 * Compares notes based on date
	 * @param	n creates instance of note for comparison
	 * @return	int based on comparison
	 */
    public int compareTo(Note n){
        return date.compareTo(n.getDate());
    }
}