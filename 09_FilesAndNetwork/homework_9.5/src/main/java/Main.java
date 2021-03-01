import core.Line;
import core.Station;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    private static final String MOS_METRO_PATH = "https://www.moscowmap.ru/metro.html#lines";
    public static HashMap<String, Line> linesListFromSite = new HashMap<String, Line>();
    public static HashMap<String, Line> linesListFromJson = new HashMap<String, Line>();
    private static final String LOCAL_FILE_PATH = "myFiles/metro.json";
    private static Logger logger;


    public static void main(String[] args) {

        logger = LogManager.getRootLogger();

        getMetroSchemeFromWebSite();
        saveMetroSchemeTJsonFile();
        getStationsNumberOnLine();

    }

    public static void getMetroSchemeFromWebSite() {
        //1. Получает HTML-код страницы «Список станций Московского метрополитена» https://www.moscowmap.ru/metro.html#lines с помощью библиотеки jsoup.
        //2. Парсит полученную страницу и получает из неё:
        //Линии московского метро (получаете имя линии, номер линии, цвет парсить не надо).
        //Станции московского метро (получаете имя станции, номер линии).

        String linesDataPath = "div.js-toggle-depend>span";
        String stationsDataPath = "div.js-metro-stations.t-metrostation-list-table";
        Document doc = null;
        try {
            doc = Jsoup.connect(MOS_METRO_PATH).maxBodySize(0).get();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        Elements lines = doc.select(linesDataPath);
        lines.stream()
                .forEach(s -> {
                    String lineNumber = s.attributes().get("data-line");
                    String lineName = s.childNodes().get(0).toString();
                    Line line = new Line(lineNumber, lineName);
                    linesListFromSite.put(lineNumber, line);
                });

        Elements linesWithStations = doc.select(stationsDataPath);
        for (Element lineWithStations : linesWithStations) {
            String lineNumber = lineWithStations.attr("data-line");
            Line currentLine = linesListFromSite.get(lineNumber);
            lineWithStations.children().stream().forEach(s -> {
                String stationName = s.children().get(0).children().get(1).text();
                Station station = new Station(stationName, currentLine);
                currentLine.getStations().add(station);
            });
        }

    }

    public static void saveMetroSchemeTJsonFile() {
        //3. Создаёт и записывает на диск JSON-файл со списком станций по линиям и списком линий по формату JSON-файла из проекта SPBMetro (файл map.json).
        JSONObject jsonObject = new JSONObject();

        JSONObject jsonObjectStations = new JSONObject();
        JSONArray jsonObjectLinesArray = new JSONArray();

        for (Map.Entry<String, Line> lineEntry : linesListFromSite.entrySet()) {
            JSONArray stationsListArray = new JSONArray();
            lineEntry.getValue().getStations().stream()
                    .forEach(s -> stationsListArray.add(s.getName()));
            jsonObjectStations.put(lineEntry.getValue().getNumber(), stationsListArray);

            JSONObject lineNumberObject = new JSONObject();
            lineNumberObject.put("number", lineEntry.getValue().getNumber());
            lineNumberObject.put("name", lineEntry.getValue().getName());

            jsonObjectLinesArray.add(lineNumberObject);

        }
        jsonObject.put("stations", jsonObjectStations);
        jsonObject.put("lines", jsonObjectLinesArray);

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(LOCAL_FILE_PATH))) {
            writer.write(jsonObject.toJSONString());
            writer.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        System.out.println(jsonObject);

    }

    public static void getStationsNumberOnLine() {
        //4. Читает файл и выводит в консоль количество станций на каждой линии.

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);

            linesListFromJson.entrySet().stream().forEach(s -> System.out.println(s.getValue().getName() + ": " + s.getValue().getStations().size()));

        } catch (Exception e) {
            logger.error(e.getMessage());
        }


    }

    private static void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    (String) lineJsonObject.get("number"),
                    (String) lineJsonObject.get("name")
            );
            linesListFromJson.put((String) lineJsonObject.get("number"), line);
        });
    }

    private static void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            String lineNumber = (String) lineNumberObject;
            Line line = linesListFromJson.get(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                line.getStations().add(station);
            });
        });
    }


    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOCAL_FILE_PATH));
            lines.forEach(line -> builder.append(line));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return builder.toString();
    }

}
