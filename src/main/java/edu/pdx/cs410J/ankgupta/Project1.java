package edu.pdx.cs410J.ankgupta;

import com.sun.org.apache.xml.internal.utils.StringToIntTable;
import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class for the CS410J airline Project
 */

public class Project1 {

    /**
     *
     * @param args are the command line arguments that are given through the command line
     *  @return clean system exit, assuming that no errors occurred.
     *  This is the main method that reads in the command line arguments and creates the appropriate variables and objects
     */
    public static void main(String[] args) {
        Class c = AbstractAirline.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
        if(args.length == 0){
            System.out.println("No command line arguments");
            System.exit(0);
        }
        if(args.length < 6){
            System.out.println("Not enough command line arguments");
            System.exit(0);
        }

        ArrayList<String> argslist = new ArrayList<String>();

        boolean print = false;

        int i = 0;

        for(String s: args){
            if(s.contains("-print")){
                print = true;
            }
            if(s.contains("-README")){
                System.out.println("Ankur Gupta. Project 1. Building a simple flight and airline application. Enter in options:");
                System.out.println(" -print  or -README");
                System.out.println("Followed by the following in this exact order:");
                System.out.println("  name , flight #, source, departure date, departure time, destination, arrival date, arrival time");
                System.exit(0);
            }
        }

        if(print == true){ i = 1; }

        String name = validateName(args[i]);
        int flightNumber = validateNumber(args[i+1]);
        String src = validateAirport(args[i+2]);
        String departDate = validateDate(args[i+3]);
        String departTime = validateTime(args[i+4]);
        departDate = departDate.concat(" " + departTime);
        String dest = validateAirport(args[i+5]);
        String arriveDate = validateDate(args[i+6]);
        String arriveTime = validateTime(args[i+7]);
        arriveDate = arriveDate.concat(" " + arriveTime);
        Flight flight = new Flight(flightNumber, src, departDate, dest, arriveDate);
        Airline airline = new Airline(name);
        airline.addFlight(flight);

        if(print == true){
            printflight(name, flightNumber, src, departDate, dest, arriveDate);
        }

        System.exit(0);
    }

    /**
     *
     * @param name of flight
     * @param num of flight
     * @param src of the flight
     * @param ddate departure date and time of flight
     * @param dest destination of flight
     * @param adate arrival date and time of flight
     * Print the flight details that were just added into the Airline's schedule
     */
    public static void printflight(String name, int num, String src, String ddate,String dest, String adate){
        System.out.println(name + " " + num + " " + src + " " + ddate + " " + dest + " " + adate);
    }

    /**
     *
     * @param name name of the flight
     * @return after validating the name, returns the name, if multi-worded removes ""
     */
    public static String validateName(String name){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        return sb.toString();
    }

    /**
     *
     * @param flightNumber validates the flight number is an integer
     * @return the flight number after validation
     * checks the flight number is a proper integer
     */
    public static int validateNumber(String flightNumber){
        int num;
        try {
            num = Integer.parseInt(flightNumber);
        }
        catch (NumberFormatException ex) {
            System.out.println("Invalid flight number");
            throw new AssertionError("Bad");
        }
        return num;
    }

    /**
     *
     * @param src the source of the flight
     * @return the source after validating the correct 3-letter airport code
     */
    public static String validateAirport(String src){
        for(char c : src.toCharArray()){
            if(!Character.isLetter(c)){
                System.out.println("Please use characters for airport code");
                System.exit(1);
            }
        }
        if(src.length() != 3){
            System.out.println("Only use 3 letters for airport code");
            System.exit(1);
        }
        return src;
    }

    /**
     *
     * @param timestamp check the value of the date is valid
     * @return the date after validation
     */
    public static String validateDate(String timestamp){
        String [] dates = timestamp.split("/");
        int month = Integer.parseInt(dates[0]);
        int day = Integer.parseInt(dates[1]);
        int year = Integer.parseInt(dates[2]);

        if(month > 12 || month < 01){
            System.out.println("Please enter valid month");
            System.exit(1);
        }
        if(day > 31 || day < 0){
            System.out.println("Please enter valid day");
            System.exit(1);
        }
        if(year < 0000 || year > 9999){
            System.out.println("Please enter year in format 'yyyy'");
            System.exit(1);
        }

        return month + "/" + day + "/" + year;
    }

    /**
     *
     * @param time validate the time of the flight
     * @return the time after validation
     */
    public static String validateTime(String time){
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);

        if(hour >= 24 || hour <= 0) {
            System.out.println("Please use 24 hour time");
            System.exit(1);
        }

        if(minute > 59 || minute <= 0 ) {
            System.out.println("Please use 24 hour time");
            System.exit(1);
        }

        return time;
    }
}