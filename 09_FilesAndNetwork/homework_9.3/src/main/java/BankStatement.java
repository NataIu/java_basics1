import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@ToString
public class BankStatement {

    private String accountType;//Тип счёта // например, "Текущий счёт"
    private String accountNumber;//Номер счета // например, "40817813206170024534"
    private String currency;//Валюта // например, "RUR"
    private Date transactionDate;//Дата операции // например, "31.05.17"
    private String transactionReference;//Референс проводки // например, "CRD_1U34U7"
    private String description;//Описание операции // например, "548673++++++1028    809216  /RU/CARD2CARD ALFA_MOBILE>MOSCOW          31.05.17 31.05.17 1500.00       RUR MCC6536"
    private double income;//Приход // например, "1500"
    private double outcome;//Расход // например, "0"
    private String organisationName; //автоматически получаем по описанию операции

    public BankStatement(ArrayList<String> fragments) {
        setAccountType(fragments.get(0));
        setAccountNumber(fragments.get(1));
        setCurrency(fragments.get(2));
        setTransactionDateString(fragments.get(3));
        setTransactionReference(fragments.get(4));
        setDescription(fragments.get(5));
        setIncomeString(fragments.get(6));
        setOutcomeString(fragments.get(7));
    }


    public void setDescription(String description) {
        this.description = description;
        this.organisationName = getOrganisationNameFromDescription(description);
    }

    private String getOrganisationNameFromDescription(String description) {
        String name = "";
        int beginIndex = (description.indexOf("/") == -1) ? description.indexOf("\\") : description.indexOf("/");
        name = description.substring(beginIndex);
        if (name.indexOf("  ") > 0) {
            name = name.substring(0, name.indexOf("  "));
        }
        return name;
    }

    public void setIncomeString(String incomeString) {
        double value = convertValueToDouble(incomeString);
        setIncome(value);
    }

    public void setOutcomeString(String outcomeString) {
        double value = convertValueToDouble(outcomeString);
        setOutcome(value);
    }

    private double convertValueToDouble(String incomeString) {
        //т.к. не число в формате "100,0" автоматом не преобразуется в double, то проделаем это вручную
        return Double.valueOf(incomeString.replaceAll(",", "."));
    }

    public void setTransactionDateString(String transactionDateString) {
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
