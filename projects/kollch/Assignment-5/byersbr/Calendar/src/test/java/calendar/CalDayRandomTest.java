package calendar;


import java.util.GregorianCalendar;
import java.util.Random;
import org.junit.Test;


import static org.junit.Assert.*;


/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {

    /**
     * Generate Random Tests that tests CalDay Class.
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
    public void testAddAppt() throws Throwable
    {
        for (int i = 0; i < 1000000; i++) {
            CalDay calday = new CalDay(new GregorianCalendar(1970, 0, 1));
            long randomseed = System.currentTimeMillis();
            Random random = new Random(randomseed);
            int numIterations = random.nextInt(4);
            for (int j = 0; j < numIterations; j++) {
                Appt appt = createAppt();
                appt.setValid();
                calday.addAppt(appt);
                //assertEquals(calday.getSizeAppts(), j + 1);
            }
        }
    }
}
