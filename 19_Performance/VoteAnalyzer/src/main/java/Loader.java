import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Loader {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();
    public static void main(String[] args) throws Exception {
        String fileName = "data-18M.xml";

        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XmlHandler handler = new XmlHandler();
        long time = System.currentTimeMillis();
        parser.parse(new File(fileName), handler);
        DBConnection.executeMultiInsert();
        System.out.println("time: " + (System.currentTimeMillis()-time));
        DBConnection.printVoterCounts();
        DBConnection.printWorkTime();
//        handler.printDuplicatedVoters();
//        handler.printWorkTime();
        long usageSax = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;

//        long usage2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        SAXParserFactory factory2 = SAXParserFactory.newInstance();
//        SAXParser parser2 = factory2.newSAXParser();
//        XmlHandler2 handler2 = new XmlHandler2();
//        parser2.parse(new File(fileName), handler2);
//        handler2.printDuplicatedVoters();
////        handler2.printWorkTime();
//        long usageSax2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage2;


//        long usage3 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        parseFile(fileName);
//        //Printing results
//        System.out.println("Voting station work times: ");
//        for (Integer votingStation : voteStationWorkTimes.keySet()) {
//            WorkTime workTime = voteStationWorkTimes.get(votingStation);
//            System.out.println("\t" + votingStation + " - " + workTime);
//        }
//        System.out.println("Duplicated voters: ");
//        for (Voter voter : voterCounts.keySet()) {
//            Integer count = voterCounts.get(voter);
//            if (count > 1) {
//                System.out.println("\t" + voter + " - " + count);
//            }
//        }
//        long usageDom = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage3;

//        System.out.println("SAX_: "+ usageSax/8/1024/1024);
//        System.out.println("SAX2: "+ usageSax2/8/1024/1024);
//        System.out.println("DOM_: "+ usageDom/8/1024/1024);
    }

    private static void parseFile(String fileName) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        findEqualVoters(doc);
//        fixWorkTimes(doc);
    }

    private static void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            Date birthDay = birthDayFormat.parse(attributes.getNamedItem("birthDay").getNodeValue());

            Voter voter = new Voter(name, birthDay);
            Integer count = voterCounts.get(voter);
            voterCounts.put(voter, count == null ? 1 : count + 1);
        }
    }

    private static void fixWorkTimes(Document doc) throws Exception {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for (int i = 0; i < visitCount; i++) {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = voteStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }
}