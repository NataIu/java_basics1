import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@ToString
public class BankStatement {

    @CsvBindByPosition(position = 0)
    private String accountType;//Тип счёта // например, "Текущий счёт"

    @CsvBindByPosition(position = 1)
    private String accountNumber;//Номер счета // например, "40817813206170024534"

    @CsvBindByPosition(position = 2)
    private String currency;//Валюта // например, "RUR"

    @CsvBindByPosition(position = 3)
    private String transactionDateString;//Дата операции // например, "31.05.17"
    private Date transactionDate;//Дата операции // например, "31.05.17"

    @CsvBindByPosition(position = 4)
    private String transactionReference;//Референс проводки // например, "CRD_1U34U7"

    @CsvBindByPosition(position = 5)
    private String description;//Описание операции // например, "548673++++++1028    809216  /RU/CARD2CARD ALFA_MOBILE>MOSCOW          31.05.17 31.05.17 1500.00       RUR MCC6536"

    @CsvBindByPosition(position = 6)
    private String incomeString;//Приход // например, "1500"
    private double income;//Приход // например, "1500"

    @CsvBindByPosition(position = 7)
    private String outcomeString;//Расход // например, "0"
    private double outcome;//Расход // например, "0"

    public void setIncomeString(String incomeString) {
        this.incomeString = incomeString;
        double value = convertValueToDouble(incomeString);
        setIncome(value);
    }

    public void setOutcomeString(String outcomeString) {
        this.outcomeString = outcomeString;
        double value = convertValueToDouble(outcomeString);
        setOutcome(value);
    }

    private double convertValueToDouble(String incomeString) {
        //т.к. не число в формате "100,0" автоматом не преобразуется в double, то проделаем это вручную
        return Double.valueOf(incomeString.replaceAll(",","."));
    }

    public void setTransactionDateString(String transactionDateString) {
        this.transactionDateString = transactionDateString;
        Date date = parseDate(transactionDateString);
        setTransactionDate(date);
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
