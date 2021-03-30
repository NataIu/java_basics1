import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "rootroot";

        String query = "SELECT  name, COUNT(student_id)/COUNT(DISTINCT MONTH(subscription_date)) AS avg_count FROM skillbox.Subscriptions " +
                "INNER JOIN skillbox.Courses ON Subscriptions.course_id = Courses.id " +
                "WHERE subscription_date >='2018-01-01' AND subscription_date < '2019-01-01' " +
                "GROUP BY course_id";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") +" - "+ resultSet.getString("avg_count") );
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
