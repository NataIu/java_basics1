import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 31;
        int month = 12;
        int year = 1990;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {

        StringBuilder result = new StringBuilder();
        long currentTime = System.currentTimeMillis();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - E", Locale.ENGLISH);
        Calendar calendar = new GregorianCalendar(year,month-1,day);

        int i = 0;
        while (calendar.getTimeInMillis() <= currentTime) {
            result.append(""+i+" - "+dateFormat.format(calendar.getTime())+System.lineSeparator());
            calendar.add(Calendar.YEAR,1);
            i++;
        }

        return result.toString();

    }
}
