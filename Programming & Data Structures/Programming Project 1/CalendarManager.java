/***
 * Class to model the entity Calendar Manager
 * Caterina Black
 * version 0.1
 * Date of creation: February 20, 2023
 * Last Date Modified: March 1, 2023
 */

import java.util.Scanner;

public class CalendarManager{
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        Calendar cal = new Calendar("events.txt");

        //how do i get print menu to stop deleting everything?

        
        int choice = 0;
        do{
            try{
                printMenu();
                choice = getChoice(scan);
                switch(choice){
                    case 1: //view all events
                        cal.viewEvents();
                        break;
                    case 2: //search event by description
                        System.out.println("Enter a description.");
                        String des = scan.next();
                        cal.findEvent(des);
                        break;
                    case 3: //search event by date
                        System.out.println("Enter a Date.");
                        String d = scan.next();
                        cal.findEvents(d);
                        break;
                    case 4: //adds a new event
                        String description, location, type, contact, host, tim, dat, guests;
                        System.out.println("Event a type (appointment/meeting):");
                        type = scan.nextLine(); //type
                        System.out.println("Enter the description:");
                        description = scan.nextLine(); //description
                        System.out.println("Enter the location:");
                        location = scan.nextLine(); //location
                        System.out.println("Enter the time (hh:mm):");
                        tim = scan.nextLine(); //time
                        System.out.println("Enter the date (mm/dd/yyyy):");
                        dat = scan.nextLine(); //date
                        if(type.equalsIgnoreCase("Meeting")){
                            System.out.println("Enter the name of the host:");
                            host = scan.nextLine();
                            System.out.println("Enter the number of guests:");
                            guests = scan.nextLine();
                            int g = Integer.parseInt(guests);
                            Meeting met = new Meeting(description, location, dat, tim, host, g);
                            if(cal.addEvent(met)){
                                System.out.println("Event added Successfully.");
                                break;
                            } else {
                                System.out.println("Event addition failed.");
                                break;
                            }
                        } else if(type.equalsIgnoreCase("Appointment")){
                            System.out.println("Enter the contact name:");
                            contact = scan.nextLine();
                            Appointment app = new Appointment(description, location, dat, tim, contact);
                            if(cal.addEvent(app)){
                                System.out.println("Event added Successfully.");
                                break;
                            } else {
                                System.out.println("Event addition failed.");
                                break;
                            }
                        }
                        
                        break;
                    case 5: //deletes event
                        System.out.println("Enter a description:");
                        String de = scan.nextLine();
                        if(cal.removeEvent(de)){
                            System.out.println("Event found and removed Succesfully.");
                        } else{
                            System.out.println("Event not found.");
                        }
                        break;
                    case 6: //sorts the events
                        cal.sortEvents();
                        cal.viewEvents();
                        break;
                    case 7: //exit
                        cal.saveEvents("events.txt");
                        System.out.println("Goodbye.");
                        System.exit(0);
                }
            } catch(InvalidDateTimeException e){
                System.out.println(e.getMessage());
            }
        } while(choice != 7);
    }

    /***
	 * checks if choice given is valid
	 * @param   scan to check if user entered a valid choice
     * @return chosen choice once it checks it is a valid option
	 */
    public static int getChoice(Scanner scan){
        int x = -1; 
        boolean flag = true;

        do{
            if(scan.hasNextInt()){
                x = scan.nextInt();
                scan.nextLine();
                if(x >= 1 && x <=7){
                    flag = false;
                } else {
                    System.out.println("Invalid option (1-7)");
                }
            } else {
                scan.nextLine();
                System.out.println("Not an integer (1-7)");
            }
        }while(flag);
        return x;
    }   

    

    /***
	 * prints operation menu
	 * @param   no no parameter
     * @return operation menu
	 */
    public static void printMenu(){
        System.out.println("Select and Operation");
        System.out.println("1: View all Events");
        System.out.println("2: Search Event by Description");
        System.out.println("3: Search Events by Date");
        System.out.println("4: Add a new Event");
        System.out.println("5: Remove an existing Event");
        System.out.println("6: Sort Events by Date and Time");
        System.out.println("7: Exit");
    }
}