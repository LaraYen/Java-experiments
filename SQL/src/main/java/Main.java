import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "gevf1313";

        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select course_name, (sum(purchCount)/(max(purchMonth)-min(purchMonth)+1)) as avgPurch from\n" +
                    "(SELECT course_name, month(subscription_date) as purchMonth , count(student_name) as purchCount FROM skillbox.purchaselist\n" +
                    "where year(subscription_date) = \"2018\"\n" +
                    "group by course_name, month(subscription_date), student_name) as t1 \n" +
                    "group by course_name");
            while(resultSet.next()){
                String courseName = resultSet.getString("course_name");
                String avgPurch = resultSet.getString("avgPurch");
                System.out.println(courseName + " " + avgPurch);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
