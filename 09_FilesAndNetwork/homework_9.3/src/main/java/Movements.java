import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class Movements {

    private List<BankStatement> bankStatements = new ArrayList<>();
    private static final Character SEPARATOR_SYMBOL = ',';
    private static final Character QUOTATION_SYMBOL = '"';
    private static final int EXPECTED_FIELDS_QUANTITY = 8;

    public Movements(String pathMovementsCsv) {
        parseFile(pathMovementsCsv);
    }

    public double getExpenseSum() {
        return bankStatements.stream().map(s -> s.getOutcome()).reduce((o1, o2) -> (o1 + o2)).orElse(0d);
    }

    public double getIncomeSum() {
        return bankStatements.stream().map(s -> s.getIncome()).reduce((o1, o2) -> (o1 + o2)).orElse(0d);
    }

    private void parseFile(String filePath) {

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {

            String line = "";
            reader.readLine(); //пропустим строку с заголовками
            while ((line = reader.readLine()) != null) {
                parseLine(line);
            }
        } catch (IncorrectFieldsQuantityException | IOException e) {
            e.printStackTrace();
        }

    }

    private void parseLine(String line) throws IncorrectFieldsQuantityException {
        ArrayList<String> fragments = parseLineToStringArray(line);
        checkLineAsStringArray(line, fragments);
        convertStringArrayToBankStatement(fragments);
    }

    private ArrayList<String> parseLineToStringArray(String line) throws IncorrectFieldsQuantityException {
        ArrayList<String> fragments = new ArrayList<>();

        while (line.length() > 0) {
            String tmp = "";
            int index1 = 0;
            int index2 = 0;
            if (QUOTATION_SYMBOL.equals(line.charAt(0))) {
                line = line.substring(1);
                index1 = line.indexOf(QUOTATION_SYMBOL);
                index2 = Math.min(index1+2, line.length());
                tmp = line.substring(0, index1);
                line = line.substring(index2);
            } else {
                index1 = line.indexOf(SEPARATOR_SYMBOL);
                if (index1 < 0) {
                    index1 = line.length();
                 }
                index2 = Math.min(index1+1, line.length());
                tmp = line.substring(0, index1);
                line = line.substring(index2);
            }
            fragments.add(tmp);
        }

        return fragments;
    }

    private void checkLineAsStringArray(String line, ArrayList<String> fragments) throws IncorrectFieldsQuantityException {

        if (fragments.size() != EXPECTED_FIELDS_QUANTITY) {
            throw new IncorrectFieldsQuantityException(line, EXPECTED_FIELDS_QUANTITY);
        }
    }

    private void convertStringArrayToBankStatement(ArrayList<String> fragments) {
        BankStatement statement = new BankStatement(fragments);
        bankStatements.add(statement);
    }


}
