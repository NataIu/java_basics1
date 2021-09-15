import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XmlHandler extends DefaultHandler {

    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
//    private HashMap<Voter, Integer> voterCount = new HashMap<>();
//    private HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter") && voter == null) {
                Date birthDate = null;
                birthDate = birthDayFormat.parse(attributes.getValue("birthDay"));

                voter = new Voter(attributes.getValue("name"), birthDate);
            } else if (qName.equals("visit") && voter != null) {
                DBConnection.countVoter(voter.getName(),birthDayFormat.format(voter.getBirthDay()));


                int station = Integer.valueOf(attributes.getValue("station"));
                Date dateTime = visitDateFormat.parse(attributes.getValue("time"));
                DBConnection.recordWorkTime(station, dateTime);
//
//                WorkTime workTime = voteStationWorkTimes.get(station);
//                if (workTime == null) {
//                    workTime = new WorkTime();
//                    voteStationWorkTimes.put(station, workTime);
//                }
//                workTime.addVisitTime(time.getTime());

            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("voter")) {
            voter = null;
        }
    }

//    public void printDuplicatedVoters() {
//        for (Voter voter : voterCount.keySet()) {
//            int count = voterCount.get(voter);
//            if (count > 1) {
//                System.out.println(voter.toString() + " - " + count);
//            }
//        }

//    }

//    public void printWorkTime() {
//        for (Integer votingStation : voteStationWorkTimes.keySet()) {
//            WorkTime workTime = voteStationWorkTimes.get(votingStation);
//            System.out.println("\t" + votingStation + " - " + workTime);
//        }
//    }

}
