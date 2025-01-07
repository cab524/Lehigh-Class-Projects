/***
 * Class to model the entity Calendar
 * Caterina Black
 * version 0.1
 * Date of creation: February 20, 2023
 * Last Date Modified: March 1, 2023
 */


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.PrintWriter;

public class Calendar{
    private Event[] events;
    private int count;


    /***
	 * Constructor with no parameters
	 */
    public Calendar(){
        events = new Event[100];
        count = 0;
    }

    /***
	 * Constructor with 1 parameter
	 * @param   filename for the file of the events
	 */
    public Calendar(String filename){
        events = new Event[100];
        count = readEvents(filename);
    }

    /***
	 * File that imports data into events list
	 * @param   filename for the file of the events
     * @return the size of list
	 */
    public int readEvents(String filename){
        int i = 0;
        File file = new File(filename);
        try{
            Scanner fScan = new Scanner(file);
            while(fScan.hasNextLine()){
                String description, location, type, contact, host, tim, dat, guests;
                type = fScan.nextLine(); //type
                //System.out.println("type " + type);
                description = fScan.nextLine(); //description
                //System.out.println("description " + description);
                location = fScan.nextLine(); //location
                //System.out.println("location " + location);
                dat = fScan.nextLine(); //date
                //System.out.println("date " + dat);
                tim = fScan.nextLine(); //time
                //System.out.println("time " + tim);
                if(type.equalsIgnoreCase("Meeting")){
                    host = fScan.nextLine();
                    //System.out.println("host " + host); 
                    guests = fScan.nextLine();
                    //System.out.println("guests " + guests);
                    int g = Integer.parseInt(guests);
                    events[i++] = new Meeting(description, location, dat, tim, host, g);
                } else if(type.equalsIgnoreCase("Appointment")){
                    contact = fScan.nextLine();
                    //System.out.println("contact " + contact);
                    events[i++] = new Appointment(description, location, dat, tim, contact);
                }
            }
            fScan.close();
        } catch(FileNotFoundException e){
            System.out.println("File not found.");
        } catch(InvalidDateTimeException e){
            System.out.println(e.getMessage());
        }

        return i;
    }

    /***
	 * File that saves new events into the text file by rewriting over the old one
	 * @param   filename for the file of the events
     * no return value
	 */
    public void saveEvents(String filename){
        File file = new File(filename);
        try{
            PrintWriter writeFile = new PrintWriter(file);
            for(int i = 0; i < count; i++){
                writeFile.println(events[i].getType());
                writeFile.println(events[i].getDescription());
                writeFile.println(events[i].getLocation());
                writeFile.println(events[i].getDate());
                writeFile.println(events[i].getTime());
                if(events[i] instanceof Meeting){ //instsance of 
                    writeFile.println(((Meeting)events[i]).getHost());
                    writeFile.print(((Meeting)events[i]).getGuests());
                } else if(events[i].getType().equalsIgnoreCase("Appointment")){
                    writeFile.print(((Appointment)events[i]).getContact());
                }
                if(i < (count - 1)){
                    writeFile.println();
                }
            }
            writeFile.close();
        } catch(FileNotFoundException e){
            System.out.println("Cannot write to file " + filename);
        }

    }

    /***
	 * method that adds event to events list
	 * @param   e to add event
     * @return true or false based on whether the even was added successfully
	 */
    public boolean addEvent(Event e){
        if(count < events.length){
            events[count++] = e;
            return true;
        } else {
            return false;
        }
    }

    /***
	 * method that removes event from events list
	 * @param   d to remove event
     * @return true or false based on whether the event was removed successfully
	 */
    public boolean removeEvent(String d){
        int k = 0;
        for(int i = 0; i < count; i++){
            if(events[i].getDescription().equals(d)){
                k = i;
                break;
            }
        }
        for(int j = k; k < count; j++){
            events[k] = events[k+1];
            count--;
            return true;
        }
        return false;
    }

    /***
	 * method that finds events based on date
	 * @param   date to find events
     * @return  events if found
	 */
    public void findEvents(String date) throws InvalidDateTimeException{
        String[] dateItems = date.split("/");
        int month = Integer.parseInt(dateItems[0]);
        int day = Integer.parseInt(dateItems[1]); 
        int year = Integer.parseInt(dateItems[2]);
        int c = 0;
        
        //GETS NUMBER OF EVENTS FOUND
        for(int i = 0; i < count; i++){
            if((events[i].getDate().getYear() == year) && (events[i].getDate().getMonth() == month) && (events[i].getDate().getDay() == day)){
                c++;
            } else {
                c += 0;
            }
        }

        //PRINTS NUMBER OF EVENTS FOUND IF FOUND
        if(c > 0){
            System.out.println(c + " events found.");
            System.out.printf("%s\t\t\t%-20s\t%-20s\t%-10s\t%-5s\t%-5s\t%-5s\n", "Type", "Description", "Location", "Date", "Time", "Host/Contact", "Guests");
        } else {
            System.out.println("No events found.");
        }
        
        //PRINTS EVENT IF FOUND
        for(int i = 0; i < count; i++){
            if((events[i].getDate().getYear() == year) && (events[i].getDate().getMonth() == month) && (events[i].getDate().getDay() == day)){
                System.out.println(events[i]);
            //count++;
            } else {
                c += 0;
            }
        }
        

        /*
        //TELLS USER IF THEY ENTERED AN INVALID DATE
        if(month < 1 || month > 12){
            System.out.println("Invalid Month. Month should be from 1 to 12.");
        } else if (day < 1 || day > 31){
            System.out.println("Invalid Day. Day should be from 1 to 31.");
        } else if (year < 1970 || year > 2030){
            System.out.println("Invalid Year. Year should be from 1970 to 2030.");
        } else if (count == 0){
            System.out.println("No event found on: " + date);
        }
        */
    }


    /***
	 * method that finds events based on its description
	 * @param   name to find event
     * @return event if found
	 */
    public void findEvent(String name){
        int k = 0;
        //prints event information if found
        for(int i = 0; i < count; i++){
            if(events[i].getDescription().equals(name)){
                System.out.print("Event found: ");
                System.out.println(events[i]);
                k++;
            } else {
                k += 0;
            }
        }

        //prints if no description is found.
        if(k == 0){
            System.out.println("null");
        }
    }

    /***
	 * views event from events
	 * @param   no parameters
     * no return value
	 */
    public void viewEvents(){
        System.out.printf("%s\t\t\t%-20s\t%-20s\t%-10s\t%-5s\t%-5s\t%-5s\n", "Type", "Description", "Location", "Date", "Time", "Host/Contact", "Guests");
        for(int i = 0; i < count; i++){
            System.out.println(events[i]);
        }
    }

    /***
	 * sorts events
	 * @param   no parameters
     * no return value
	 */
    public void sortEvents(){
        Arrays.sort(events, 0, count);
    }
}