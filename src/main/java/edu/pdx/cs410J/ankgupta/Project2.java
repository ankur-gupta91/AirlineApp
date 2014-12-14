package edu.pdx.cs410J.ankgupta;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ankur on 7/15/2014.
 */
public class Project2{
    public static void main(String[] args)
    {
        Class c = AbstractAirline.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
        if (args.length == 0) {
            System.err.println("No command line arguments");
            System.exit(1);
        }

        int i;
        boolean print = false;
        boolean textfile = false;
        String filename = null;
        for(i=0 ; i < args.length ; ++i) {
            if (args[i].equals("-print")) {
                print = true;
            }
            else if(args[i].equals("-README")){
                System.out.println("Ankur Gupta. Project 2. Building a simple flight and airline application. Enter in options:");
                System.out.println(" -print , -README or textFile file");
                System.out.println("Followed by the following in this exact order:");
                System.out.println("  name , flight #, source, departure date, departure time, destination, arrival date, arrival time");
                System.exit(0);
            }
            else if(args[i].equals("-textFile")){
                textfile = true;
                filename = args[i+1];
            }
        }
        if(args.length == 1 && print == true){
            System.err.println("Empty arguments");
            System.exit(1);
        }

        i = 0;
        if(print == true)
        {
            i = 1;
        }
        if(textfile == true)
        {
            i += 2;
        }
//
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

        if(args.length > i + 8){
            System.err.println("Unknown extra command lines");
            System.exit(1);
        }

        Airline airline = null;

        if(textfile == true)
        {
            File f = new File(filename);
            if (filename != null)
            {
                if(f.exists()) {
                    Flight flight = new Flight(flightNumber, src, departDate, dest, arriveDate);
                    airline = new TextParser(filename).parse(filename);

                    try {
                        TextParser tp = new TextParser(filename);
                        String temp = tp.getName(filename);
                        if (!name.equals(temp)) {
                            System.err.println("Not the right file, trying to save to wrong file based on airline name");
                            System.exit(1);
                        }
                    } catch (IOException ie){
                        System.err.println("Error in name matching");
                        System.exit(1);
                    }

                    airline.addFlight(flight);
                    new TextDumper(filename).dump(airline);
                }
                else
                {
                    airline = new Airline(name);
                    Flight flight = new Flight(flightNumber, src, departDate, dest, arriveDate);
                    airline.addFlight(flight);
                    new TextDumper(filename).dump(airline);
                }
            }
            else
            {
                System.err.println("Can't dump to file, different name");
                System.exit(1);
            }
        }


/*        Flight flight = new Flight(flightNumber, src, departDate, dest, arriveDate);
        Airline airline = new Airline(name);
        airline.addFlight(flight);



        if(textfile == true)
        {
            if (filename != null)
            {
                new TextDumper(filename).dump(airline);
            }
            else
            {
                System.err.println("Can't dump to file, different name");
                System.exit(1);
            }
        }*/
        if(print == true){
            printflight(name, flightNumber, src, departDate, dest, arriveDate);
        }

        System.exit(0);
    }

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
        int num = 0;
        try {
            num = Integer.parseInt(flightNumber);
        }
        catch (NumberFormatException ex) {
            System.err.print("Invalid flight number");
            System.exit(1);
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
        int month, day, year, extra;
        month = 0;
        day = 0;
        year = 0;
        try{
           month = Integer.parseInt(dates[0]);
           day = Integer.parseInt(dates[1]);
           year = Integer.parseInt(dates[2]);
           if(dates.length > 3){
               System.err.println("Date malformated, please use mm/dd/yyyy");
               System.exit(1);
           }
        } catch(NumberFormatException n){
            System.err.println("Invalid date format, please use mm/dd/yyyy");
            System.exit(1);
        }

        if(month > 12 || month < 01){
            System.err.println("Please enter valid month");
            System.exit(1);
        }
        if(day > 31 || day < 0){
            System.err.println("Please enter valid day");
            System.exit(1);
        }
        if(year < 1000 || year > 9999){
            System.err.println("Please enter year in format 'yyyy'");
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
        int hour = 0;
        int minute = 0;

        try {
            hour = Integer.parseInt(times[0]);
            minute = Integer.parseInt(times[1]);
        } catch(NumberFormatException e){
            System.err.println("Please use integers for time");
            System.exit(1);
        }

        if(hour > 23 || hour < 00) {
            System.out.println("Please use 24 hour time");
            System.exit(1);
        }

        if(minute > 59 || minute < 00 ) {
            System.out.println("Please use 24 hour time");
            System.exit(1);
        }

        return time;
    }

}
