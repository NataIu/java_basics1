import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class Movements {

    private List<BankStatement> bankStatements = new ArrayList<>();
    private static final Character SEPARATOR_SYMBOL = ',';
    private static final Character QUOTATION_SYMBOL = '"';
    private static final int EXPECTED_FIELDS_QUANTITY = 8;

    public Movements(String pathMovementsCsv) {
        parseFile(pathMovementsCsv);
    }

    public double getExpenseSum() {
        return bankStatements.stream().map(BankStatement::getOutcome).reduce(0.0, Double::sum);
    }

    public double getIncomeSum() {
        return bankStatements.stream().map(BankStatement::getIncome).reduce(0.0, Double::sum);
    }

    public HashMap<String, Double> getOutcomeSumByCompanies() {
        StringBuilder builder = new StringBuilder();
        HashMap<String, Double> companiesOutcome = new HashMap<>();
        bankStatements.stream().forEach(s -> {
            Double currentSum = 0d;
            String currentOrganisationName = s.getOrganisationName();
            if (companiesOutcome.containsKey(currentOrganisationName)) {
                currentSum = companiesOutcome.get(currentOrganisationName).doubleValue();
            }
            companiesOutcome.put(s.getOrganisationName(), currentSum + s.getOutcome());
        });

        return companiesOutcome;
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

    private  ArrayList<String> parseLineToStringArray(String line) throws IncorrectFieldsQuantityException {
        String reg = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] splittedData  = line.split(reg);

        List fragmentsList = ((ArrayList<String>) Arrays.stream(splittedData)
                .map(s -> {
                    if (s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"') {
                        s = s.substring(1, s.length() - 1);
                    }
                    return s;
                })
                .collect(Collectors.toList()));
        ArrayList<String> fragments = new ArrayList<>(fragmentsList);

        return fragments;
    }

    private void checkLineAsStringArray(String line,ArrayList<String> fragments) throws IncorrectFieldsQuantityException {

        if (fragments.size() != EXPECTED_FIELDS_QUANTITY) {
            throw new IncorrectFieldsQuantityException(line, EXPECTED_FIELDS_QUANTITY);
        }
    }

    private void convertStringArrayToBankStatement(ArrayList<String> fragments) {
        BankStatement statement = new BankStatement(fragments);
        bankStatements.add(statement);
    }


}
