package edu.pdx.cs410J.ankgupta;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirportNames;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Airline class based on AbstractAirline
 * Created by Ankur on 7/5/2014.
 */
public class Airline extends AbstractAirline {
    private final String name;
    private List<Flight> flightList;

    public Airline(String name){
        this.name = name;
        flightList = new LinkedList<Flight>();
    }

    /**
     *
     * @return the name of the Airline
     */
    public String getName() {
        return name;
    }


    /**
     *
     * @param newflight so that we can add a flight's schedule to the airline
     *
     */
    public void addFlight(AbstractFlight newflight) {
        try {
            flightList.add((Flight)newflight);
        }
        catch(ClassCastException c) {
            System.out.println("Failed sort");
        }
    }

    /**
     *
     * @return the flight schedule
     */
    public java.util.Collection getFlights() {
        return flightList;
    }


    /**
     * Compiles the string of flight information to be added to the file
     * @return the string of the airline information
     */
    public String writetofile() {
        String file = "";
        for(Flight f : flightList){
            file += name + " " + f.getNumber() + " " + f.getSource() +
                    " " + f.getDepartureString() + " " + f.getDestination() +
                    " " + f.getArrivalString() + "\n";
        }
        return file;
    }

    /**
     * used for testing of the number of objects in the list
     * @return count
     */
    public int listCount(){
        int count = flightList.size();
        return count;
    }

    /**
     *
     * @return nothing
     */
    public java.lang.String toString() /* compiled code */ {
        return null;
    }

    /**
     * Used for testing, prints all the flights in the list
     */
    public void printAll(){
        for(Flight f : flightList){
            System.out.println(name + " " + f.getNumber() + " " + f.getSource() +
                    " " + f.getDepartureString() + " " + f.getDestination() +
                    " " + f.getArrivalString());
        }
    }
}
