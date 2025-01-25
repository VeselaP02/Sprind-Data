package DB_Apps_Introduction_Exercises;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Get_Villians_Name {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String user = scanner.nextLine();
        String password = scanner.nextLine();


        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement villainsStatement = connection.prepareStatement("SELECT name, count(mv.minion_id) as count_minions FROM villains as v" +
                " JOIN minions_villains as mv ON v.id = mv.villain_id" +
                " GROUP BY mv.villain_id"+
                " HAVING count_minions > ?" +
                " ORDER BY count_minions DESC");

        villainsStatement.setInt(1,15);

        ResultSet villainsSet = villainsStatement.executeQuery();

        while (villainsSet.next()){
            String villainName = villainsSet.getString("name");
            int minions_count = villainsSet.getInt("count_minions");
            System.out.printf("%s %d%n",villainName,minions_count);
        }

        connection.close();
    }

}
