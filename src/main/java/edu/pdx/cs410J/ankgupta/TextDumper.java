package edu.pdx.cs410J.ankgupta;

import edu.pdx.cs410J.AbstractAirline;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.*;

/**
 * Created by Ankur on 7/15/2014.
 */
public class TextDumper
{
    String name;

    public TextDumper(String name){
        this.name = name;
    }

    /**
     * Take in an airplane as an argument and add it to a file
     * if the file does not exist create the file
     * if the file exists add the file to the end of the existing one
     * @param abstractAirline
     */
    public void dump(AbstractAirline abstractAirline) {
        try {


            if(name.contains("/")) {
                String[] split = name.split("/");
                String filePath = "";
                filePath = split[0];
                String textFile = split[1];
                File directory = new File(filePath);
                directory.mkdir();
            }

            File file = new File(name);
            if (!file.exists()) {
                FileWriter writer = new FileWriter(new File(name));
                Airline airline = (Airline)abstractAirline;
                writer.append(airline.writetofile());
                writer.close();
            } else {
                TextParser tp = new TextParser(name);
                String temp = tp.getName(name);
                String compare = abstractAirline.getName();
                if(!compare.equals(temp)){
                    System.err.println("Not the right file, trying to save to wrong file based on airline name");
                    System.exit(1);
                }

                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(name , false)));
                Airline airline = (Airline)abstractAirline;
                pw.append(airline.writetofile());
                pw.close();
            }
        } catch (IOException io) {
            System.out.print("Problem writing to file" + name);
        }
    }
}
