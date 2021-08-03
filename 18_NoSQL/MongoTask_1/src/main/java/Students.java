import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import lombok.Getter;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Getter
public class Students {

    private List<Student> list;

    public void initByFile(String filePath) {

            try {

                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVParser parser = new CSVParserBuilder()
                        .withSeparator(',')
                        .build();
                CSVReader csvReader = new CSVReaderBuilder(reader)
                        .withCSVParser(parser)
                        .build();

                ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
                mappingStrategy.setType(Student.class);

                CsvToBean ctb = new CsvToBean();
                ctb.setCsvReader(csvReader);
                ctb.setMappingStrategy(mappingStrategy);

                list = ctb.parse();

            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
