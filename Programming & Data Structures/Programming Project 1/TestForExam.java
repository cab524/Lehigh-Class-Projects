import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
public class TestForExam{
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        Event[] eve = new Event[50];

        //do while for menu operations
        int choice;
        do{
            printMenu();
            choice = getChoice(scan);
            int num = readEvents(eve, "events.txt");
            switch(choice){
                case 1: //view all events
                    printEvents(num, eve);
                    break;
                case 2: //search event by description
                    System.out.println("Enter a description.");
                    String des = scan.next();
                    findEvent(eve, num, des);
                    break;
                case 3: //search event by date
                    System.out.println("Enter a Date.");
                    String d = scan.next();
                    findEvents(eve, num, d);
                    break;
                case 4: //sorts array by date and time
                    sortEvents(eve, num);
                    printEvents(num, eve);
                    break;
                case 5: //exit
                    System.out.println("Goodbye.");
                    System.exit(0);
            }
        } while(choice != 5);
    }

    //READS IN EVENTS FROM FILE
    public static int readEvents(Event[] list, String filename){
        int i = 0;
        File file = new File(filename);
        try{
            Scanner fScan = new Scanner(file);
            while(fScan.hasNextLine()){
                String type, description, location, date, time, contact, host;
                int guests;
                type = fScan.nextLine();
                if(type.equalsIgnoreCase("appointment")){
                    description = fScan.nextLine();
                    location = fScan.nextLine();
                    date = fScan.nextLine();
                    time = fScan.nextLine();
                    contact = fScan.nextLine();
                    list[i++] = new Appointment(description, location, date, time, contact);
                }
                else if(type.equalsIgnoreCase("meeting")){
                    description = fScan.nextLine();
                    location = fScan.nextLine();
                    date = fScan.nextLine();
                    time = fScan.nextLine();
                    host = fScan.nextLine();
                    guests = Integer.parseInt(fScan.nextLine());
                    list[i++] = new Meeting(description, location, date, time, host, guests);
                }
            }
            fScan.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        catch(InvalidDateTimeException e){
            System.out.println(e.getMessage());
        }

        return i;
    }

    //PRINTS EVENT MENU
    public static void printEvents(int num, Event[] list){
        System.out.printf("%s\t\t\t%-20s\t%-20s\t%-10s\t%-5s\t%-5s\t%-5s\n", "Type", "Description", "Location", "Date", "Time", "Host/Contact", "Guests");
        for(int i = 0; i < num; i++){
            System.out.println(list[i]);
        }  
    }

    public static void sortEvents(Event[] list, int num){
        for(int i = 0; i < num; i++){
            if(i < num){
                Event e = list[i];
                int j = i;
                while(j > 0 && e.compareTo(list[j-1]) == 1){
                    list[j] = list[j - 1];
                    j--;
                }
                list[j] = e;
            }
        }
    }

    //FINDS EVENTS BASED ON DATE ENTERED
    public static void findEvents(Event[] list, int num, String date){
        String regex = "\\d{2}/\\d{2}/\\d{4}";
        int month = 0;
        int day = 0;
        int year = 0;
        if(date.matches(regex)){
            String[] dateItems = date.split("/");
            month = Integer.parseInt(dateItems[0]);
            day = Integer.parseInt(dateItems[1]); 
            year = Integer.parseInt(dateItems[2]);
        }

        int count = 0;
        //GETS NUMBER OF EVENTS FOUND
        for(int i = 0; i < num; i++){
            if((list[i].getDate().getYear() == year) && (list[i].getDate().getMonth() == month) && (list[i].getDate().getDay() == day)){
                count++;
            } else {
                count += 0;
            }
        }

        //PRINTS NUMBER OF EVENTS FOUND IF FOUND
        if(count > 0){
            System.out.println(count + " events found.");
            System.out.printf("%s\t\t\t%-20s\t%-20s\t%-10s\t%-5s\t%-5s\t%-5s\n", "Type", "Description", "Location", "Date", "Time", "Host/Contact", "Guests");
        }

        //PRINTS EVENT IF FOUND
        for(int i = 0; i < num; i++){
            if((list[i].getDate().getYear() == year) && (list[i].getDate().getMonth() == month) && (list[i].getDate().getDay() == day)){
                System.out.println(list[i]);
                //count++;
            } else {
                count += 0;
            }
        }

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
    }


    //FINDS AND EVENT BASED ON ITS DESCRIPTION
    public static void findEvent(Event[] list, int num, String name){
        int count = 0;
        //prints event information if found
        for(int i = 0; i < num; i++){
            String d = list[i].getDescription();
            if(d.equals(name) == true){
                System.out.println(list[i]);
                count++;
            } else {
                count = count + 0;
            }
        }

        //prints if no description is found.
        if(count == 0){
            System.out.println("No event found with Description: " + name);
        }
    }


    //MAKES SURE YOU ENTER A VALID OPERATION CHOICE
    public static int getChoice(Scanner scan){
        int x = -1; 
        boolean flag = true;

        do{
            if(scan.hasNextInt()){
                x = scan.nextInt();
                scan.nextLine();
                if(x >= 1 && x <=5){
                    flag = false;
                } else {
                    System.out.println("Invalid option (1-5)");
                }
            } else {
                scan.nextLine();
                System.out.println("Not an integer (1-5)");
            }
        }while(flag);
        return x;
    }


    //PRINTS MENU OF OPERATIONS
    public static void printMenu(){
        System.out.println("Select and Operation");
        System.out.println("1: View all Events");
        System.out.println("2: Search Event by Description");
        System.out.println("3: Search Events by Date");
        System.out.println("4: Sort Events by Date and Time");
        System.out.println("5: Exit");
    }

    //Downcasting for Exam (String) o
}