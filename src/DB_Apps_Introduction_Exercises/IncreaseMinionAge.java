package DB_Apps_Introduction_Exercises;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class IncreaseMinionAge {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);


        String user = scanner.nextLine();
        String password = scanner.nextLine();


        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);


            PreparedStatement updateMinions = connection.prepareCall("CALL usp_get_older(?)");

            int minionId = Integer.parseInt(scanner.nextLine());
            updateMinions.setInt(1, minionId);
            updateMinions.execute();

        ResultSet setMinions = connection.createStatement().executeQuery("SELECT id,name,age from minions");
        while (setMinions.next()) {
            System.out.printf("%d %s %d%n", setMinions.getInt("id"), setMinions.getString("name"), setMinions.getInt("age"));
        }
    }
}
