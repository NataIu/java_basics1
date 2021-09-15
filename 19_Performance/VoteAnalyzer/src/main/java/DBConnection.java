import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "rootroot";

    private static StringBuilder insertQuery = new StringBuilder();
    private static StringBuilder insertVisitQuery = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id)," +
                        "UNIQUE KEY name_date(name(50), birthDate))");

                connection.createStatement().execute("DROP TABLE IF EXISTS vote_station_work_times");
                connection.createStatement().execute("CREATE TABLE vote_station_work_times(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "station INT NOT NULL, " +
                        "work_date DATE NOT NULL, " +
                        "min_work_time TIME NOT NULL, " +
                        "max_work_time TIME NOT NULL," +
                        "PRIMARY KEY(id)," +
                        "UNIQUE KEY work_date(station, work_date))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) VALUES " +
                insertQuery +
                "ON DUPLICATE KEY UPDATE `count`= `count` + 1";

        String sqlVisit = "INSERT INTO vote_station_work_times(station, work_date, min_work_time, max_work_time) VALUES " +
                insertVisitQuery +
                "ON DUPLICATE KEY UPDATE min_work_time = \n" +
                "case when min_work_time< VALUES(min_work_time) then min_work_time\n" +
                "else VALUES(min_work_time)\n" +
                "end,\n" +
                "max_work_time = \n" +
                "case when max_work_time> VALUES(max_work_time) then max_work_time\n" +
                "else VALUES(max_work_time)\n" +
                "end";
        Connection conn = DBConnection.getConnection();
        conn.createStatement().executeUpdate(sql);
        conn.createStatement().executeUpdate(sqlVisit);

    }

    public static void countVoter(String name, String birthDay) throws SQLException {

        birthDay = birthDay.replace('.', '-');
        insertQuery.append((insertQuery.length() == 0 ? " " : ", ") + "('" + name + "', '" + birthDay + "', 1) ");

    }

    public static void recordWorkTime(int station, Date dateTime) throws SQLException {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(dateTime);
        String time = new SimpleDateFormat("HH:mm:ss").format(dateTime);
        insertVisitQuery.append((insertVisitQuery.length() == 0 ? " " : ", ") + "('" + station + "', '" + date + "', '" + time + "', '" + time + "'  ) ");

    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }

    public static void printWorkTime() throws SQLException {
        String sql = "SELECT station, work_date, min_work_time, max_work_time FROM vote_station_work_times ORDER BY station";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        int currentStation = 0;
        int station = 0;
        StringBuilder visitTime = new StringBuilder();
        while (rs.next()) {
            station = rs.getInt("station");
            if (currentStation != station) {
                if (visitTime.length() > 0) {
                    System.out.println(visitTime.toString());
                }
                visitTime = new StringBuilder();
                visitTime.append("\t" + rs.getString("station") + " - ");
                currentStation = station;
            }
            visitTime.append(rs.getString("work_date") + " " + rs.getString("min_work_time") + " - " +
                    rs.getString("work_date") + " " + rs.getString("max_work_time") + "; ");
        }

        if (visitTime.length() > 0) {
            System.out.println(visitTime.toString());
        }

    }
}
