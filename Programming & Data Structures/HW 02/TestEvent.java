import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;

public class TestEvent{
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        Event[] eve = new Event[50];
        int num = readEvents(eve, "events.txt");

        printEvents(eve, num);


    }
    public static int readEvents(Event[] list, String filename){
        File f = new File(filename);
        //Scanner fScan;
        int i = 0;
        try{
            Scanner fScan = new Scanner(f);
            while(fScan.hasNextLine());{//has .next line
                String type, location, description, host, date, time, contact;
                int guests;
                type = fScan.nextLine();
                if(type.equals("appointment")){
                    description = fScan.nextLine();
                    location = fScan.nextLine();
                    date = fScan.nextLine();
                    time = fScan.nextLine();
                    contact = fScan.nextLine();
                    list[i] = new Appointment(description, location, date, time, contact);
                    i++;
                } else {
                    description = fScan.nextLine();
                    location = fScan.nextLine();
                    date = fScan.nextLine();
                    time = fScan.nextLine();
                    host = fScan.nextLine();
                    guests = Integer.parseInt(fScan.nextLine());
                    list[i] = new Meeting(description, location, date, time, host, guests);
                    i++;
                }            
            }
            fScan.close();
        } catch (FileNotFoundException e){
            System.out.println("Cannot Open File");
            //System.exit(0);
        } catch (InvalidDateTimeException e){
            System.out.println(e.getMessage());
        }
        return i;
    }

    //Difference between find event and events

    public static void printEvents(Event[] list, int num){
        for(int i = 0; i < num; i++){
            System.out.println(list[i]); //wont an error occur since not all of eve is filled
        }  
    }

    /*
    //whats the difference between findevent and find events?
    public static void findEvent(){
        int count = 0;

        //gets count of amount of times an event is at a certain location
        for(int i = 0; i < list.length; i++){
            String d = list[i].getDescription();
            if(d.equals(name) == true){
                //System.out.println(type + ": " + list[i]);
                count++;
            } else {
                count = count + 0;
            }
        }
        //prints number of times their was an event at a certain location
        if(count > 1 && type == "Location"){
            System.out.println(count + " events found at this Location.");
        }

        //prints event information if found
        for(int i = 0; i < list.length; i++){
            String l = list[i].getLocation();
            String d = list[i].getDescription();
            if(l.equals(name) == true || d.equals(name) == true){
                System.out.println(type + ": " + list[i]);
                //count++;
            } else {
                count = count + 0;
            }
        }

        //prints if no location or description is found.
        if(count == 0){
            if(type == "Description"){
                System.out.println("No event found with " + type + ": " + name);
            } else {
                System.out.println("No event found at " + name);
            }
        }
    }

    public static void findEvents(){

    }

    //sort the events by date and time
    public static void SortEvents(){
        for(int i = 0; i < list.length; i++){
            if(i < list.length){
                Event e = list[i];
                int j = i;
                while(j > 0 && e.getDate().compareTo(list[j - 1].getDate()) < 0){
                    while(e.getTime().compareTo(list[j - 1].getTime()) < 0){
                        list[j] = list[j - 1];
                        j--;
                    }
                }
                list[j] = e;
            }
        }
    }
    */
}