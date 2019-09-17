package com.olli;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class GetHalle {
    private static final String COMMA_DELIMITER = ";";

    public String getStrasse() {
        return Strasse;
    }

    public String getPLZ() {
        return PLZ;
    }

    public String getOrt() {
        return Ort;
    }

    String Strasse;
String PLZ;
String Ort;
    public GetHalle(String s) {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/osc/Documents/IntelliJProjects/CsvToIcs/ics/Hallenverzeichnis.csv"))) {
            String line;
            System.out.println(s);
            while ((line = br.readLine()) != null) {
                if(line.contains("("+s+")")){
                    Strasse = line.split(";")[1].split(",")[0];
                    PLZ = line.split(";")[1].split(",")[1].split(" ")[1];
                    Ort = line.split(";")[1].split(",")[1].split(" ")[2];
                    System.out.println(line);
                    break;
                }
            }
            System.out.println(Strasse + PLZ + Ort);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
