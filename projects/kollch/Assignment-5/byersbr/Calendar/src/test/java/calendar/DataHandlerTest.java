/** A JUnit test class to test the class DataHandler. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;

public class DataHandlerTest
{
    @Test
    public void testSaveAppt() throws Throwable
    {
        Appt appt = new Appt(15, 30, 9, 13, 2018, "Title", "Description", "xyz@gmail.com");
        int[] recurdays = {2, 2, 2};
        appt.setRecurrence(recurdays, 2, 2, 2);
        DataHandler datahandler1 = new DataHandler();
        DataHandler datahandler2 = new DataHandler("calendar2.xml", false);
        appt.setValid();
        assertFalse(datahandler1.saveAppt(appt));
        appt.setStartMonth(10);
        appt.setValid();
        datahandler1.saveAppt(appt);
        assertTrue(datahandler2.saveAppt(appt));
    }

    @Test
    public void testDeleteAppt() throws Throwable
    {
        Appt appt = new Appt(15, 30, 9, 13, 2018, "Title", "Description", "xyz@gmail.com");
        appt.setValid();
        int[] recurdays = {2, 2, 2};
        appt.setRecurrence(recurdays, 2, 2, 2);
        DataHandler datahandler1 = new DataHandler();
        DataHandler datahandler2 = new DataHandler("calendar2.xml", false);
        assertFalse(datahandler1.deleteAppt(appt));
        appt.setStartMonth(10);
        appt.setValid();
        assertFalse(datahandler1.deleteAppt(appt));
        datahandler1.saveAppt(appt);
        datahandler1.deleteAppt(appt);
        datahandler2.saveAppt(appt);
        assertTrue(datahandler2.deleteAppt(appt));
    }

    @Test
    public void testGetApptRange() throws Throwable
    {
        DataHandler datahandler = new DataHandler();
        GregorianCalendar day1 = new GregorianCalendar(1970, 1, 1);
        GregorianCalendar day2 = new GregorianCalendar(2017, 2, 2);
        boolean exceptionCaught = false;
        try {
            List<CalDay> caldays = datahandler.getApptRange(day2, day1);
        } catch (DateOutOfRangeException e) {
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught);
        List<CalDay> emptycaldays1 = datahandler.getApptRange(day1, day2);
        assertEquals("\t --- 2/1/1970 --- \n --- -------- Appointments ------------ --- \n\n", emptycaldays1.get(0).toString());
        //datahandler.saveAppt(appt1);
        //Appt appt2 = new Appt(15, 0, 9, 10, 2015, "Title2", "Description2", "xyz@gmail.com");
        //datahandler.saveAppt(appt2);
        //datahandler.getApptRange(day1, day2);
    }

    @Test
    public void testTest() throws Throwable
    {
        DataHandler datahandler = new DataHandler();
        Appt appt1 = new Appt(15, 30, 9, 10, 2015, "Title", "Description", "xyz@gmail.com");
        int[] recurrdays = {2, 2, 2};
        appt1.setRecurrence(recurrdays, Appt.RECUR_BY_WEEKLY, 2, 2);
        datahandler.saveAppt(appt1);
        Appt appt2 = new Appt(16, 0, 9, 10, 2016, "Title2", "Description2", "xyz@gmail.com");
        appt2.setRecurrence(recurrdays, Appt.RECUR_BY_YEARLY, 2, 2);
        GregorianCalendar day1 = new GregorianCalendar(1970, 1, 1);
        GregorianCalendar day2 = new GregorianCalendar(2017, 2, 2);
        datahandler.saveAppt(appt2);
        Appt appt3 = new Appt(17, 0, 19, 10, 2016, "Title3", "Description3", "xyz@gmail.com");
        appt3.setRecurrence(recurrdays, Appt.RECUR_BY_MONTHLY, 2, 2);
        datahandler.saveAppt(appt3);
        Appt appt4 = new Appt(17, 0, 29, 10, 2016, "Title4", "Description4", "xyz@gmail.com");
        datahandler.saveAppt(appt4);
        datahandler.getApptRange(day1, day2);
    }

    @Test
    public void testNewDataHandlerConstructorWoArgs() throws Throwable
    {
        DataHandler datahandler1 = new DataHandler();
        datahandler1.save();
        DataHandler datahandler2 = new DataHandler();
        Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
        //add owners permission
        //perms.add(PosixFilePermission.OWNER_READ);
        //perms.add(PosixFilePermission.OWNER_WRITE);
        //perms.add(PosixFilePermission.OWNER_EXECUTE);
        //add group permissions
        //perms.add(PosixFilePermission.GROUP_READ);
        //perms.add(PosixFilePermission.GROUP_WRITE);
        //perms.add(PosixFilePermission.GROUP_EXECUTE);
        //add others permissions
        //perms.add(PosixFilePermission.OTHERS_READ);
        //perms.add(PosixFilePermission.OTHERS_WRITE);
        //perms.add(PosixFilePermission.OTHERS_EXECUTE);

        //Files.setPosixFilePermissions(Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + "calendar.xml"), perms);
        DataHandler datahandler3 = new DataHandler("calendar.xml", false);
        DataHandler datahandler4 = new DataHandler("src", true);
    }
}
