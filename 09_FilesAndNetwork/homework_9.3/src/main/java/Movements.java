import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Movements {

    private List<BankStatement> bankStatements;

    public Movements(String pathMovementsCsv) {
        parseFile(pathMovementsCsv);
    }

    public double getExpenseSum() {
        return bankStatements.stream().map(s->s.getOutcome()).reduce((o1,o2)->(o1+o2)).orElse(0d);
    }

    public double getIncomeSum() {
        return bankStatements.stream().map(s->s.getIncome()).reduce((o1,o2)->(o1+o2)).orElse(0d);

    }

    private void parseFile(String filePath) {

        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));

            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withQuoteChar('"')
//                    .withIgnoreQuotations(true)
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                        .withCSVParser(parser)
                    .build();

            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(BankStatement.class);

            CsvToBean ctb = new CsvToBean();
            ctb.setCsvReader(csvReader);
            ctb.setMappingStrategy(mappingStrategy);

            bankStatements = ctb.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
