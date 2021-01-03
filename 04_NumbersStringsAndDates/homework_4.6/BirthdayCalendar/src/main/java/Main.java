import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 03;
        int month = 01;
        int year = 2021;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {

        StringBuilder result = new StringBuilder();
        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();
        int i = 0;
        while (birthday.isBefore(today) || birthday.isEqual(today)) {
            result.append(""+i+" - "+DateTimeFormatter.ofPattern("dd.MM.yyyy - E", new Locale("eng")).format(birthday)+System.lineSeparator());
            birthday = birthday.plusYears(1);
            i++;
        }
        return result.toString();

    }
}
