package edu.pdx.cs410J.ankgupta;

import edu.pdx.cs410J.AbstractAirline;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Used to parse the file to check th name is correct and file is correct so
 * we can add the flight information to the right file.
 * Created by Ankur on 7/15/2014.
 */
public class TextParser {
    String filename;
    String airplanename;

    public TextParser(String filename){
        this.filename = filename;
    }

    /**
     * Sort out the name of the airline so we can check before adding flight
     * @param name name of airline
     * @return the name from file, of the airline
     * @throws IOException
     */
    public String getName(String name) throws IOException{

        String airplanename = "";
        String info;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(name));
            while((info = reader.readLine()) != null){
                //info = reader.readLine();
                String[] array = info.split(" ");
                String[] args = new String[8];
                args[0] = array[0];
                airplanename = args[0];
            }

            reader.close();
        } catch (Exception e) {

        }
        return airplanename;
    }

    /**
     * Parses through the existing file and loads all the info into the Airline's flight list
     * @param name is the file name & location
     */
    public Airline parse(String name){
        Airline airline = null;
        boolean created = false;
        try {
            String info = "";
            BufferedReader reader = new BufferedReader(new FileReader(name));
            while ((info = reader.readLine()) != null) {
                String[] array = info.split(" ");
                String airplanename = validateName(array[0]);
                if(created == false){
                    airline = new Airline(airplanename);
                    created = true;
                }
                int flightNumber = validateNumber(array[1]);
                String src = validateAirport(array[2]);
                String departDate = validateDate(array[3]);
                String departTime = validateTime(array[4]);
                departDate = departDate.concat(" " + departTime);
                String dest = validateAirport(array[5]);
                String arriveDate = validateDate(array[6]);
                String arriveTime = validateTime(array[7]);
                arriveDate = arriveDate.concat(" " + arriveTime);

                Flight flight = new Flight(flightNumber, src, departDate, dest, arriveDate);
                airline.addFlight(flight);
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Error reading, malformatted text file");
            System.exit(1);
        }

        return airline;
    }

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
            System.err.print("Parse, Invalid flight number");
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
                System.out.println("Parse, Please use characters for airport code");
                System.exit(1);
            }
        }
        if(src.length() != 3){
            System.out.println("Parse, Only use 3 letters for airport code");
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
                System.err.println("Parse, Date malformated, please use mm/dd/yyyy");
                System.exit(1);
            }
        } catch(NumberFormatException n){
            System.err.println("Parse, Invalid date format, please use mm/dd/yyyy");
            System.exit(1);
        }

        if(month > 12 || month < 01){
            System.err.println("Parse, Please enter valid month");
            System.exit(1);
        }
        if(day > 31 || day < 0){
            System.err.println("Parse, Please enter valid day");
            System.exit(1);
        }
        if(year < 1000 || year > 9999){
            System.err.println("Parse, Please enter year in format 'yyyy'");
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
            System.err.println("Parse, Please use integers for time");
            System.exit(1);
        }

        if(hour > 23 || hour < 00) {
            System.out.println("Parse, Please use 24 hour time");
            System.exit(1);
        }

        if(minute > 59 || minute < 00 ) {
            System.out.println("Parse, Please use 24 hour time");
            System.exit(1);
        }

        return time;
    }
}
