package edu.pdx.cs410J.ankgupta;

import edu.pdx.cs410J.AbstractFlight;

import java.util.Date;

/**
 * Created by Ankur on 7/5/2014.
 */

/**
 * Class defining the flight object and its associated variables
 */
public class Flight extends AbstractFlight {
    private final int flightNumber;
    private final String src;
    private final String departTime;
    private final String dest;
    private final String arriveTime;
    private final Date departDate;
    private final Date arriveDate;

    public Flight(int flightNumber, String src, String departTime, String dest, String arriveTime){
        this.flightNumber = flightNumber;
        this.src = src;
        this.departTime = departTime;
        this.dest = dest;
        this.arriveTime = arriveTime;
        departDate = null;
        arriveDate = null;
    }

    /**
     *
     * @return the flight's number
     */
    public int getNumber(){
        return flightNumber;
    }

    /**
     *
     * @return the flights departure source
     */
    public java.lang.String getSource(){
        return src;
    }

    /**
     *
     * @return null, since this is an optional functiont to implement
     */
    public java.util.Date getDeparture() { /* compiled code */
        return null;
    }

    /**
     *
     * @return the flights departure time
     */
    public String getDepartureString(){
        return departTime;
    }

    /**
     *
     * @return the flight's destination
     */
    public java.lang.String getDestination(){
        return dest;
    }

    /**
     *
     * @return null since its an optional function
     */
    public java.util.Date getArrival() { /* compiled code */
        return null;
    }

    /**
     *
     * @return the flight's arrival time.
     */
    public String getArrivalString(){
        return arriveTime;
    }

    //   public String toString() { /* compiled code */
    //       return this.flightNumber + this.src + this.departTime + this.dest + this.arriveTime;
    //   }
}
