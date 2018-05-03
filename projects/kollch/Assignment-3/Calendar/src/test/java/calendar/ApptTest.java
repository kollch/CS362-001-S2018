/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import org.w3c.dom.Element;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;

public class ApptTest
{
    public Appt newAppt()
    {
        return new Appt(15, 30, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    }

    public Appt newApptSansTime() {
        return new Appt(9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    }

    @Test
    public void testApptSetters() throws Throwable
    {
        Appt appt = newAppt();
        appt.setXmlElement(null);
        assertNull(appt.getXmlElement());
        appt.setStartHour(1);
        assertEquals(1, appt.getStartHour());
        appt.setStartMinute(1);
        assertEquals(1, appt.getStartMinute());
        appt.setStartDay(1);
        assertEquals(1, appt.getStartDay());
        appt.setStartMonth(1);
        assertEquals(1, appt.getStartMonth());
        appt.setStartYear(2000);
        assertEquals(2000, appt.getStartYear());
    }

    @Test
    public void testSetValid() throws Throwable
    {
        Appt appt = newAppt();
        appt.setValid();
        assertTrue(appt.getValid());
        appt.setStartMonth(0);
        appt.setValid();
        assertFalse(appt.getValid());
        appt.setStartMonth(13);
        appt.setValid();
        assertFalse(appt.getValid());
        appt.setStartMonth(10);
        appt.setValid();
        assertTrue(appt.getValid());
        appt.setStartHour(-1);
        appt.setValid();
        assertFalse(appt.getValid());
        appt.setStartHour(24);
        appt.setValid();
        assertFalse(appt.getValid());
        appt.setStartHour(15);
        appt.setValid();
        assertTrue(appt.getValid());
        appt.setStartMinute(-1);
        appt.setValid();
        assertFalse(appt.getValid());
        appt.setStartMinute(60);
        appt.setValid();
        assertFalse(appt.getValid());
        appt.setStartMinute(30);
        appt.setValid();
        assertTrue(appt.getValid());
        appt.setStartYear(0);
        appt.setValid();
        assertFalse(appt.getValid());
        appt.setStartYear(-1);
        appt.setValid();
        assertFalse(appt.getValid());
        appt.setStartYear(2018);
        appt.setValid();
        assertTrue(appt.getValid());
        appt.setStartDay(-1);
        appt.setValid();
        assertFalse(appt.getValid());
        appt.setStartDay(9);
        appt.setValid();
        assertTrue(appt.getValid());
        appt.setStartDay(31);
        appt.setValid();
        assertTrue(appt.getValid());
        appt.setStartMonth(11);
        appt.setValid();
        assertFalse(appt.getValid());
        appt.setStartMonth(3);
        appt.setStartDay(29);
        appt.setValid();
        assertFalse(appt.getValid());
        appt.setStartYear(2016);
        appt.setValid();
        assertTrue(appt.getValid());
    }

    @Test
    public void testSetTitle() throws Throwable
    {
        Appt appt = newAppt();
        appt.setTitle(null);
        assertEquals("", appt.getTitle());
        appt.setTitle("Hello!");
        assertEquals("Hello!", appt.getTitle());
    }

    @Test
    public void testSetDescription() throws Throwable
    {
        Appt appt = newAppt();
        appt.setDescription(null);
        assertEquals("", appt.getDescription());
        appt.setDescription("This is a description.");
        assertEquals("This is a description.", appt.getDescription());
    }

    @Test
    public void testSetRecurrence() throws Throwable
    {
        Appt appt = newAppt();
        int[] days = {1, 2};
        appt.setRecurrence(days, 3, 2, 4);
        assertEquals(2, appt.getRecurDays().length);
        assertEquals(1, appt.getRecurDays()[0]);
        assertEquals(2, appt.getRecurDays()[1]);
        appt.setRecurrence(null, 3, 2, 4);
        assertNotNull(appt.getRecurDays());
        assertEquals(0, appt.getRecurDays().length);
        assertEquals(3, appt.getRecurBy());
        assertEquals(2, appt.getRecurIncrement());
        assertEquals(4, appt.getRecurNumber());
    }

    @Test
    public void testApptWTimeConstructor() throws Throwable
    {
        Appt appt = newAppt();
        assertEquals(15, appt.getStartHour());
        assertEquals(30, appt.getStartMinute());
        assertEquals(9, appt.getStartDay());
        assertEquals(10, appt.getStartMonth());
        assertEquals(2018, appt.getStartYear());
        assertEquals("Birthday Party", appt.getTitle());
        assertEquals("This is my birthday party", appt.getDescription());
        assertEquals("xyz@gmail.com", appt.getEmailAddress());
        assertEquals(2, appt.getRecurBy());
        assertFalse(appt.isRecurring());
        assertEquals(0, appt.getRecurIncrement());
        assertNull(appt.getXmlElement());
        assertTrue(appt.getValid());
    }

    @Test
    public void testApptWoTimeConstructor() throws Throwable
    {
        Appt appt = newApptSansTime();
        assertFalse(appt.hasTimeSet());
        assertEquals(9, appt.getStartDay());
        assertEquals(10, appt.getStartMonth());
        assertEquals(2018, appt.getStartYear());
        assertEquals("Birthday Party", appt.getTitle());
        assertEquals("This is my birthday party", appt.getDescription());
        assertEquals("xyz@gmail.com", appt.getEmailAddress());
        assertEquals(2, appt.getRecurBy());
        assertFalse(appt.isRecurring());
        assertEquals(0, appt.getRecurIncrement());
        assertNull(appt.getXmlElement());
        assertTrue(appt.getValid());
    }

    @Test
    public void testSetEmailAddress() throws Throwable
    {
        Appt appt1 = newAppt();
        assertEquals("xyz@gmail.com", appt1.getEmailAddress());
        Appt appt2 = new Appt(15, 30, 9, 10, 2018, "Title", "Description", null);
        assertEquals("", appt2.getEmailAddress());
    }

    @Test
    public void testIsOn() throws Throwable
    {
        Appt appt = newAppt();
        assertTrue(appt.isOn(9, 10, 2018));
        assertFalse(appt.isOn(9, 10, 2017));
        assertFalse(appt.isOn(9, 9, 2018));
        assertFalse(appt.isOn(8, 10, 2018));
        assertFalse(appt.isOn(9, 9, 2017));
        assertFalse(appt.isOn(8, 10, 2017));
        assertFalse(appt.isOn(8, 10, 2018));
        assertFalse(appt.isOn(8, 9, 2017));
    }

    @Test
    public void testHasTimeSet() throws Throwable
    {
        Appt appt1 = newAppt();
        Appt appt2 = newApptSansTime();
        assertTrue(appt1.hasTimeSet());
        assertFalse(appt2.hasTimeSet());
    }

    @Test
    public void testIsRecurring() throws Throwable
    {
        Appt appt = newAppt();
        appt.setRecurrence(null, 2, 2, Appt.RECUR_NUMBER_NEVER);
        assertFalse(appt.isRecurring());
        appt.setRecurrence(null, 2, 2, Appt.RECUR_NUMBER_FOREVER);
        assertTrue(appt.isRecurring());
    }

    @Test
    public void testToString() throws Throwable
    {
        Appt appt = newAppt();
        assertEquals("\t10/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", appt.toString());
        appt.setStartHour(0);
        assertEquals("\t10/9/2018 at 12:30am ,Birthday Party, This is my birthday party\n", appt.toString());
        appt.setStartMonth(13);
        appt.setValid();
        appt.setStartDay(10);
        appt.setStartYear(2017);
        appt.setTitle("Title");
        appt.setDescription("Description");
        assertEquals("\t13/10/2017 at 12:30am ,Title, Description\n", appt.toString());
    }
}
