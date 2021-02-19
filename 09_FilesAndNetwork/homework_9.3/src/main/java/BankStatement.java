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
    private double income;//Приход // например, "1500"

    @CsvBindByPosition(position = 7)
    private double outcome;//Расход // например, "0"

    public void setTransactionDateString(String transacyionDateString) {
        this.transactionDateString = transacyionDateString;
        Date date = parseDate(transacyionDateString);
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
