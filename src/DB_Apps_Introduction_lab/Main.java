package DB_Apps_Introduction_lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        String user = scanner.nextLine();
        String password = scanner.nextLine();

        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stm = connection.prepareStatement("SELECT user_name,first_name,last_name, COUNT(ug.id) as count_games FROM users as u" +
                " JOIN users_games as ug on u.id = ug.user_id" +
                " WHERE user_name = ?" +
                " GROUP BY ug.user_id;");

        String username = scanner.nextLine();
        stm.setString(1,username);

        ResultSet rs = stm.executeQuery();

        if (rs.next()){
            String user_name = rs.getString("user_name");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            Integer count_games = rs.getInt("count_games");

            System.out.printf("User: %s\n" +
                    "%s %s has played %d games\n",user_name,first_name,last_name,count_games);
        } else {
            System.out.println("No such user exists");
        }






    }
}
