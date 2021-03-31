import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "rootroot";

        String query = "SELECT  name, COUNT(student_id)/COUNT(DISTINCT MONTH(subscription_date)) AS avg_count FROM skillbox.Subscriptions " +
                "INNER JOIN skillbox.Courses ON Subscriptions.course_id = Courses.id " +
                "WHERE (subscription_date >= ?) AND (subscription_date < ?) " +
                "GROUP BY course_id";
        try (Connection connection = DriverManager.getConnection(url, user, pass))
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf("2018-01-01"));
            preparedStatement.setDate(2, Date.valueOf("2019-01-01"));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") +" - "+ resultSet.getString("avg_count") );
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
