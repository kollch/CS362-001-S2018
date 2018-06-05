package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;


/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {

    private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
    private static final int NUM_TESTS = 100;

    /**
     * Return a randomly selected method to be tests !.
     */
    public static String RandomSelectMethod(Random random) {
        String[] methodArray = new String[]{"setTitle", "setRecurrence"};// The list of the of methods to be tested in the Appt class

        int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

        return methodArray[n]; // return the method name
    }

    /**
     * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
     */
    public static int RandomSelectRecur(Random random) {
        int[] RecurArray = new int[]{Appt.RECUR_BY_WEEKLY, Appt.RECUR_BY_MONTHLY, Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

        int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n]; // return the value of the  appointments to recur
    }

    /**
     * Return a randomly selected appointments to recur forever or Never recur  !.
     */
    public static int RandomSelectRecurForEverNever(Random random) {
        int[] RecurArray = new int[]{Appt.RECUR_NUMBER_FOREVER, Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

        int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n]; // return appointments to recur forever or Never recur
    }

    /**
     * Generate Random Tests that tests Appt Class.
     */
    //@Test
    public void radnomtest() throws Throwable {

        long startTime = Calendar.getInstance().getTimeInMillis();
        long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


        System.out.println("Start testing...");

        try {
            for (int iteration = 0; elapsed < TestTimeout; iteration++) {
                long randomseed = System.currentTimeMillis(); //10
                //			System.out.println(" Seed:"+randomseed );
                Random random = new Random(randomseed);

                int startHour = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                int startMinute = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                int startDay = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                int startYear = ValuesGenerator.getRandomIntBetween(random, 2018, 2018);
                String title = "Birthday Party";
                String description = "This is my birthday party.";
                String emailAddress = "xyz@gmail.com";

                //Construct a new Appointment object with the initial data
                //Construct a new Appointment object with the initial data
                Appt appt = new Appt(startHour,
                        startMinute,
                        startDay,
                        startMonth,
                        startYear,
                        title,
                        description,
                        emailAddress);

                if (!appt.getValid()) continue;
                for (int i = 0; i < NUM_TESTS; i++) {
                    String methodName = ApptRandomTest.RandomSelectMethod(random);
                    if (methodName.equals("setTitle")) {
                        String newTitle = (String) ValuesGenerator.getString(random);
                        appt.setTitle(newTitle);
                    } else if (methodName.equals("setRecurrence")) {
                        int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 8);
                        int[] recurDays = ValuesGenerator.generateRandomArray(random, sizeArray);
                        int recur = ApptRandomTest.RandomSelectRecur(random);
                        int recurIncrement = ValuesGenerator.RandInt(random);
                        int recurNumber = ApptRandomTest.RandomSelectRecurForEverNever(random);
                        appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
                    }
                }

                elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
                if ((iteration % 10000) == 0 && iteration != 0)
                    System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);

            }
        } catch (NullPointerException e) {

        }

        System.out.println("Done testing...");
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

    public boolean isValid(Appt appt)
    {
        int startMonth = appt.getStartMonth();
        int startHour = appt.getStartHour();
        int startMinute = appt.getStartMinute();
        int startYear = appt.getStartYear();
        int startDay = appt.getStartDay();
        if (startMonth < 1 || startMonth > 12) {
            return false;
        } else if (startHour < 0 || startHour > 23) {
            return false;
        } else if (startMinute < 0 || startMinute > 59) {
            return false;
        } else if (startYear <= 0) {
            return false;
        } else {
            int NumDaysInMonth = CalendarUtil.NumDaysInMonth(startYear, startMonth - 1);
            if (startDay < 1 || startDay > NumDaysInMonth)
                return false;
        }
        return true;
    }

    @Test
    public void testSetValid() throws Throwable
    {
        for (int i = 0; i < 1000000; i++) {
            Appt appt = createAppt();
            appt.setValid();
            if (isValid(appt)) {
                assertTrue(appt.getValid());
            } else {
                assertFalse(appt.getValid());
            }
        }
    }

    @Test
    public void testSetRecurDays() throws Throwable
    {
        long randomseed = System.currentTimeMillis();
        Random random = new Random(randomseed);
        int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 8);
        int[] recurDays = ValuesGenerator.generateRandomArray(random, sizeArray);
        int recur = ApptRandomTest.RandomSelectRecur(random);
        int recurIncrement = ValuesGenerator.RandInt(random);
        int recurNumber = ApptRandomTest.RandomSelectRecurForEverNever(random);
        Appt appt = createAppt();
        for (int i = 0; i < 1000; i++) {
            boolean isNull = random.nextBoolean();
            if (isNull) {
                appt.setRecurrence(null, recur, recurIncrement, recurNumber);
                assertNotNull(appt.getRecurDays());
            } else {
                appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
                assertEquals(appt.getRecurDays(), recurDays);
            }
        }
    }

    @Test
    public void testIsOn() throws Throwable
    {
        for (int i = 0; i < 1000000; i++) {
            Appt appt = createAppt();
            assertTrue(appt.isOn(appt.getStartDay(), appt.getStartMonth(), appt.getStartYear()));
            long randomseed = System.currentTimeMillis();
            Random random = new Random(randomseed);
            int randDay = ValuesGenerator.getRandomIntBetween(random, 0, 32);
            int randMonth = ValuesGenerator.getRandomIntBetween(random, 0, 13);
            int randYear = ValuesGenerator.getRandomIntBetween(random, -100, 2018);
            if (randDay == appt.getStartDay() && randMonth == appt.getStartMonth() && randYear == appt.getStartYear()) {
                continue;
            }
            assertFalse(appt.isOn(randDay, randMonth, randYear));
        }
    }
}
