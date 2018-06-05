/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;

import java.util.GregorianCalendar;
import java.util.LinkedList;


public class CalDayTest
{
    public CalDay newCalDay()
    {
        return new CalDay(new GregorianCalendar(1970, 0, 1));
    }

    @Test
    public void testGetAppts() throws Throwable
    {
        CalDay cal = newCalDay();
        LinkedList<Appt> appts = cal.getAppts();
        assertNotNull(appts);
        assertEquals(0, cal.getSizeAppts());
    }

    @Test
    public void testNewCalDayConstructorWCal() throws Throwable
    {
        CalDay cal = newCalDay();
        assertEquals(1, cal.getDay());
        assertEquals(1970, cal.getYear());
        assertTrue(cal.isValid());
        assertEquals(0, cal.getMonth());
    }

    @Test
    public void testNewCalDayConstructorWoCal() throws Throwable
    {
        CalDay cal = new CalDay();
        assertFalse(cal.isValid());
    }

    @Test
    public void testAddAppt() throws Throwable
    {
        CalDay cal = newCalDay();
        Appt appt1 = new Appt(24, 30, 1, 1, 1970, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        Appt appt2 = new Appt(11, 45, 6, 5, 2018, "Title", "Description", "xyz@gmail.com");
        Appt appt3 = new Appt(17, 45, 6, 5, 2018, "Title2", "Description2", "xyz@gmail.com");
        appt1.setValid();
        cal.addAppt(appt1);
        assertEquals(0, cal.getSizeAppts());
        appt1.setStartHour(15);
        appt1.setValid();
        cal.addAppt(appt1);
        assertEquals(1, cal.getSizeAppts());
        cal.addAppt(appt2);
        assertEquals(2, cal.getSizeAppts());
        try {
            cal.addAppt(appt3);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("NullPointerException: " + e.getMessage());
            fail();
        }
    }

    @Test
    public void testIterator() throws Throwable
    {
        CalDay cal1 = newCalDay();
        assertNotNull(cal1.iterator());
        CalDay cal2 = new CalDay();
        assertNull(cal2.iterator());
    }

    @Test
    public void testToString() throws Throwable
    {
        CalDay cal1 = new CalDay();
        CalDay cal2 = newCalDay();
        Appt appt = new Appt(15, 30, 1, 1, 1970, "Title", "Description", "xyz@gmail.com");
        assertEquals("", cal1.toString());
        cal2.addAppt(appt);
        assertEquals("\t --- 1/1/1970 --- \n --- -------- Appointments ------------ --- \n\t1/1/1970 at 3:30pm ,Title, Description\n \n", cal2.toString());
    }

    @Test
    public void testGetFullInformationApp() throws Throwable
    {
        CalDay cal1 = newCalDay();
        CalDay cal2 = newCalDay();
        CalDay cal3 = newCalDay();
        CalDay cal4 = newCalDay();
        CalDay cal5 = newCalDay();
        Appt appt1 = new Appt(0, 0, 1, 1, 1970, "Title", "Description", "xyz@gmail.com");
        Appt appt2 = new Appt(1, 1, 1970, "Title2", "Description2", "xyz@gmail.com");
        Appt appt3 = new Appt(11, 59, 1, 1, 1970, "Title3", "Description3", "xyz@gmail.com");
        Appt appt4 = new Appt(15, 30, 1, 1, 1970, "Title4", "Description4", "xyz@gmail.com");
        Appt appt5 = new Appt(12, 9, 1, 1, 1970, "Title5", "Description5", "xyz@gmail.com");
        cal1.addAppt(appt1);
        assertTrue(appt1.hasTimeSet());
        assertEquals("1-1-1970 \n\t12:00AM Title Description ", cal1.getFullInformationApp(cal1));
        cal2.addAppt(appt2);
        assertEquals("1-1-1970 \n\tTitle2 Description2 ", cal2.getFullInformationApp(cal2));
        cal3.addAppt(appt3);
        assertEquals("1-1-1970 \n\t11:59AM Title3 Description3 ", cal3.getFullInformationApp(cal3));
        cal4.addAppt(appt4);
        assertEquals("1-1-1970 \n\t3:30PM Title4 Description4 ", cal4.getFullInformationApp(cal4));
        cal5.addAppt(appt5);
        assertEquals("1-1-1970 \n\t12:09PM Title5 Description5 ", cal5.getFullInformationApp(cal5));
    }
}