package com.olli;


import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import net.fortuna.ical4j.model.property.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Ausgabe aus der main()-Methode");

        String calFile = "mycalendar.ics";
        String csvFile="/Users/osc/Documents/IntelliJProjects/CsvToIcs/ics/AlteHerren.csv";
        ReadCsv rc = new ReadCsv(csvFile);
        List<List<String>> records = rc.getRecords();
        

        for(int i=0; i<records.size();i++){
            new WriteIcs(records.get(i));
        }



    }

}
