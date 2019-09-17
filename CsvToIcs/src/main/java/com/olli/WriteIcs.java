package com.olli;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class WriteIcs {
    public WriteIcs(List<String> record) throws IOException {
        String date = record.get(0);
        String time = record.get(1);
        //String halle =
        GetHalle halle = new GetHalle(record.get(2));


        String subject = "AlteHerren: " + record.get(4) + " - " + record.get(5);

        System.out.println(date);

        // Start Date is on: April 1, 2008, 9:00 am
        java.util.Calendar startDate = new GregorianCalendar();
        int day_of_month = Integer.parseInt(date.split("\\.")[1].trim());
        int month = Integer.parseInt(date.split("\\.")[2].trim())-1;
        int year = Integer.parseInt(date.split("\\.")[3].trim());
        int hour = Integer.parseInt(time.split(":")[0].trim());
        int minute = Integer.parseInt(time.split(":")[1].trim());

        System.out.println("###### " + day_of_month);

        startDate.set(java.util.Calendar.MONTH, month);
        startDate.set(java.util.Calendar.DAY_OF_MONTH, day_of_month);
        startDate.set(java.util.Calendar.YEAR, year);



        startDate.set(java.util.Calendar.HOUR_OF_DAY, hour-3);
        startDate.set(java.util.Calendar.MINUTE, minute);
        startDate.set(java.util.Calendar.SECOND, 0);

        // End Date is on: April 1, 2008, 13:00
        java.util.Calendar endDate = new GregorianCalendar();
        endDate.set(java.util.Calendar.MONTH, month);
        endDate.set(java.util.Calendar.DAY_OF_MONTH, day_of_month);
        endDate.set(java.util.Calendar.YEAR, year);
        endDate.set(java.util.Calendar.HOUR_OF_DAY, hour+3);
        endDate.set(java.util.Calendar.MINUTE, minute);
        endDate.set(java.util.Calendar.SECOND, 0);

        // Create the event
        String eventName = subject;
        DateTime start = new DateTime(startDate.getTime());
        DateTime end = new DateTime(endDate.getTime());
        VEvent meeting = new VEvent(start, end, eventName);

        String uidValue = UUID.randomUUID()+"";// + "@" + InetAddress.getLocalHost().getHostName();
        meeting.getProperties().add(new Uid(uidValue));
        meeting.getProperties().add(new Location(halle.getStrasse()+"\n"+halle.getPLZ()+"\n"+halle.getOrt()));
        meeting.getProperties().add(new Description("Anwurf: " + time));
//LOCATION:Privat\nLuise-Kaschnitz-Weg 18\n30629 Hannover\nGermany
// Create a calendar
        net.fortuna.ical4j.model.Calendar icsCalendar = new net.fortuna.ical4j.model.Calendar();
        icsCalendar.getProperties().add(new ProdId("ICS-Generator"));
        icsCalendar.getProperties().add(CalScale.GREGORIAN);
        icsCalendar.getProperties().add(Version.VERSION_2_0);
        icsCalendar.getComponents().add(meeting);
        System.out.println(icsCalendar);
        FileOutputStream fout = new FileOutputStream("/Users/osc/Documents/IntelliJProjects/CsvToIcs/ics/"+day_of_month +"-" + month + "-" + year +".ics");

        CalendarOutputter outputter = new CalendarOutputter();
        outputter.output(icsCalendar, fout);

    }
}
