import core.Line;
import core.Station;
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
    private static Logger logger;


    public static void main(String[] args) {

        logger = LogManager.getRootLogger();
        StationIndex stationIndexFromSite = new StationIndex();
        StationIndex stationIndexFromJson = new StationIndex();
        String filePath = "myFiles/metro.json";


        getMetroSchemeFromWebSite(stationIndexFromSite);
        saveMetroSchemeToJsonFile(stationIndexFromSite, filePath);

        readStationsFromJsonFile(stationIndexFromJson, filePath);

        printStationsQuantityOnLine(stationIndexFromJson);
        printConnectionsQuantity(stationIndexFromJson);

    }

    public static void getMetroSchemeFromWebSite(StationIndex stationIndexFromSite) {
        //1. Получает HTML-код страницы «Список станций Московского метрополитена» https://www.moscowmap.ru/metro.html#lines с помощью библиотеки jsoup.
        //2. Парсит полученную страницу и получает из неё:
        //Линии московского метро (получаете имя линии, номер линии, цвет парсить не надо).
        //Станции московского метро (получаете имя станции, номер линии).
        //Переходы между станциями
        
        Document doc = null;
        try {
            doc = Jsoup.connect(MOS_METRO_PATH).maxBodySize(0).get();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        readLinesFromSite(stationIndexFromSite, doc);
        readStationsAndConnectionsFromSite(stationIndexFromSite, doc);

    }

    private static void readLinesFromSite(StationIndex stationIndexFromSite, Document doc) {
        //прочитаем с сайта все линии и создадим их как объекты
        String linesDataPath = "div.js-toggle-depend>span";
        Elements lines = doc.select(linesDataPath);
        lines.stream()
                .forEach(s -> {
                    String lineNumber = s.attributes().get("data-line");
                    String lineName = s.childNodes().get(0).toString();
                    Line line = new Line(lineNumber, lineName);
                    stationIndexFromSite.addLine(line);
                });
    }

    private static void readStationsAndConnectionsFromSite(StationIndex stationIndexFromSite, Document doc) {
        //прочитаем с сайта все станции и переходы между ними и создадим их как объекты
        String stationsDataPath = "div.js-metro-stations.t-metrostation-list-table";
        Elements linesWithStations = doc.select(stationsDataPath);

        createStationsTakenFromSite(stationIndexFromSite, linesWithStations);
        createConnectionsTakenFromSite(stationIndexFromSite, linesWithStations);
    }

    private static void createStationsTakenFromSite(StationIndex stationIndexFromSite, Elements linesWithStations) {
        for (Element lineWithStations : linesWithStations) {
            String lineNumber = lineWithStations.attr("data-line");
            Line currentLine = stationIndexFromSite.getLine(lineNumber);
            lineWithStations.children().stream().forEach(s -> {

                String stationName = s.children().get(0).children().get(1).text();
                Station station = new Station(stationName, currentLine);
                stationIndexFromSite.addStation(station);
            });
        }
    }

    private static void createConnectionsTakenFromSite(StationIndex stationIndexFromSite, Elements linesWithStations) {
        for (Element lineWithStations : linesWithStations) {
            String lineNumber = lineWithStations.attr("data-line");

            lineWithStations.children().stream().forEach(s -> {
                int dataSize = s.children().get(0).children().size();

                if (dataSize > 2) {
                    createConnectionFromSiteForChosenStation(stationIndexFromSite, lineNumber, s, dataSize);
                }
            });
        }
    }

    private static void createConnectionFromSiteForChosenStation(StationIndex stationIndexFromSite, String lineNumber, Element element, int dataSize) {

        List <Station> connectedStations = new ArrayList<>();

        //добавим основную станцию
        String stationName = element.children().get(0).children().get(1).text();
        Station station = stationIndexFromSite.getStation(stationName, lineNumber);
        connectedStations.add(station);

        //добавим все переходы с нее
        for (int i = 2; i < dataSize; i++) {

            Station connectedStation = findConnectedStationBySiteInformation(stationIndexFromSite, element);
            connectedStations.add(connectedStation);
        }

        stationIndexFromSite.addConnection(connectedStations);
    }

    private static Station findConnectedStationBySiteInformation(StationIndex stationIndexFromSite, Element element) {
        //номер линии, на которую переходим
        String connectionLineNumber = element.children().get(0).children().get(2).attributes().get("class");
        connectionLineNumber = connectionLineNumber.replaceAll("t-icon-metroln ln-", "");

        //имя станции, на которую переходим
        String connectionStationText = element.children().get(0).children().get(2).attributes().get("title");
        connectionStationText = connectionStationText.substring(connectionStationText.indexOf('«') + 1);
        connectionStationText = connectionStationText.substring(0, connectionStationText.indexOf('»'));

        Station connectedStation = stationIndexFromSite.getStation(connectionStationText, connectionLineNumber);
        return connectedStation;
    }


    public static void saveMetroSchemeToJsonFile(StationIndex stationIndexFromSite, String filePath) {
        //3. Создаёт и записывает на диск JSON-файл со списком станций по линиям и списком линий по формату JSON-файла из проекта SPBMetro (файл map.json).
        JSONObject jsonObject = new JSONObject();

        JSONObject jsonObjectStations = new JSONObject();
        JSONArray jsonObjectLinesArray = new JSONArray();
        JSONArray jsonConnectionsArray = new JSONArray();

        prepareStationsAndLinesForJson(stationIndexFromSite, jsonObjectStations, jsonObjectLinesArray);
        prepareConnectionsForJson(stationIndexFromSite, jsonConnectionsArray);

        jsonObject.put("stations", jsonObjectStations);
        jsonObject.put("connections", jsonConnectionsArray);
        jsonObject.put("lines", jsonObjectLinesArray);

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath))) {
            writer.write(jsonObject.toJSONString());
            writer.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        System.out.println(jsonObject);

    }

    private static void prepareStationsAndLinesForJson(StationIndex stationIndexFromSite, JSONObject jsonObjectStations, JSONArray jsonObjectLinesArray) {
        for (Map.Entry<String, Line> lineEntry : stationIndexFromSite.getNumber2line().entrySet()) {
            JSONArray stationsListArray = new JSONArray();
            stationIndexFromSite.getStationsOnLine(lineEntry.getValue()).stream()
                    .forEach(s -> stationsListArray.add(s.getName()));
            jsonObjectStations.put(lineEntry.getValue().getNumber(), stationsListArray);

            JSONObject lineNumberObject = new JSONObject();
            addLineNumber(lineNumberObject,"number", lineEntry.getValue().getNumber());
            addLineOrStationName(lineNumberObject,"name", lineEntry.getValue().getName());

            jsonObjectLinesArray.add(lineNumberObject);

        }
    }

    private static void prepareConnectionsForJson(StationIndex stationIndexFromSite, JSONArray jsonConnectionsArray) {

        stationIndexFromSite.getConnections().entrySet().stream().forEach(s -> {
            JSONArray jsonArrayConn = new JSONArray();
            addConnectionToJsonConnectionArray(jsonArrayConn, s.getKey());
            s.getValue().stream().forEach(station-> addConnectionToJsonConnectionArray(jsonArrayConn, station));
            jsonConnectionsArray.add(jsonArrayConn);
        });
    }

    private static void addConnectionToJsonConnectionArray(JSONArray jsonArrayConn, Station station) {
        JSONObject connectionObject = new JSONObject();
        addLineNumber(connectionObject,"line", station.getLine().getNumber());
        addLineOrStationName(connectionObject,"station", station.getName());
        jsonArrayConn.add(connectionObject);
    }

    private static void addLineNumber(JSONObject jsonObject, String tag, String number) {
        jsonObject.put(tag, number);
    }

    private static void addLineOrStationName(JSONObject jsonObject, String tag, String name) {
        jsonObject.put(tag, name);
    }


    public static void readStationsFromJsonFile(StationIndex stationIndexFromJson, String filePath) {
        //4 (1) Читает файл

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile(filePath));

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray, stationIndexFromJson);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject, stationIndexFromJson);

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray, stationIndexFromJson);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    private static void parseConnections(JSONArray connectionsArray, StationIndex stationIndexFromJson) {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                String lineNumber = (String) itemObject.get("line");
                String stationName = (String) itemObject.get("station");

                Station station = stationIndexFromJson.getStation(stationName, lineNumber);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            stationIndexFromJson.addConnection(connectionStations);
        });
    }

    private static void parseLines(JSONArray linesArray, StationIndex stationIndexFromJson) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    (String) lineJsonObject.get("number"),
                    (String) lineJsonObject.get("name")
            );
            stationIndexFromJson.addLine(line);
        });
    }

    private static void parseStations(JSONObject stationsObject, StationIndex stationIndexFromJson) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            String lineNumber = (String) lineNumberObject;
            Line line = stationIndexFromJson.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                stationIndexFromJson.addStation(station);
            });
        });
    }

    private static String getJsonFile(String filePath) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            lines.forEach(line -> builder.append(line));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return builder.toString();
    }


    public static void printStationsQuantityOnLine(StationIndex stationIndexFromJson) {
        //4 (2) выводит в консоль количество станций на каждой линии.
        stationIndexFromJson.getNumber2line().entrySet().stream()
                .forEach(s -> System.out.println(s.getValue().getName() + ": " + stationIndexFromJson.getStationsOnLine(s.getValue()).size()));
    }

    public static void printConnectionsQuantity(StationIndex stationIndexFromJson) {
        //5 выводит в консоль общее количество переходов
        //формула: если переход между n станциями, то всего n*(n-1)/2 переходов
        //т.к. для каждой из n станций делаю подсчет, то  нужно еще делить на n
        //итого суммирую (n-1)/2
        double result = stationIndexFromJson.getConnections().values().stream().mapToInt(stations -> stations.size()  / 2).sum();
        System.out.println("Всего переходов: "+ (int) result);
    }



}
