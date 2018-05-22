package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;


/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {

    /**
     * Generate Random Tests that tests DataHandler Class.
     */
    //@Test
    public void radnomtest() throws Throwable {


    }

    public Appt createAppt()
    {
        long randomseed = System.currentTimeMillis();
        //System.out.print(System.currentTimeMillis());
        Random random = new Random(randomseed);

        //int startHour = ValuesGenerator.getRandomIntBetween(random, -3, 26) - 1;
        int startHour = random.nextInt(26) - 1;
        int startMinute = ValuesGenerator.getRandomIntBetween(random, -1, 60);
        int startDay = ValuesGenerator.getRandomIntBetween(random, 0, 32);
        int startMonth = ValuesGenerator.getRandomIntBetween(random, 0, 13);
        int startYear = ValuesGenerator.getRandomIntBetween(random, -100, 2018);
        String title = "Birthday Party";
        String description = "This is my birthday party.";
        String emailAddress = "xyz@gmail.com";

        //Construct a new Appointment object with the initial data
        return new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description, emailAddress);
    }

    @Test
    public void testDeleteAppt() throws Throwable
    {
        for (int i = 0; i < 1000; i++) {
            Appt appt = createAppt();
            appt.setValid();
            long randomseed = System.currentTimeMillis();
            Random random = new Random(randomseed);
            int[] recurdays = ValuesGenerator.generateRandomArray(random, 3);
            appt.setRecurrence(recurdays, 2, 2, 2);
            DataHandler datahandler1 = new DataHandler();
            DataHandler datahandler2 = new DataHandler("calendar2.xml", false);
            assertFalse(datahandler1.deleteAppt(appt));
            int newMonth = random.nextInt(1) + 10;
            appt.setStartMonth(newMonth);
            appt.setValid();
            assertFalse(datahandler1.deleteAppt(appt));
            datahandler1.saveAppt(appt);
            datahandler1.deleteAppt(appt);
            datahandler2.saveAppt(appt);
            datahandler2.deleteAppt(appt);
        }
    }
}
