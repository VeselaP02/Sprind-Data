package DB_Apps_Introduction_Exercises;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ChangeTowns_Names {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        String user = scanner.nextLine();
        String password = scanner.nextLine();


        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String country = scanner.nextLine();

        PreparedStatement changeTownsName = connection.prepareCall("UPDATE towns" +
                " SET name = upper(name)" +
                " WHERE country = ?");

        changeTownsName.setString(1,country);

        int countTowns = changeTownsName.executeUpdate();

        if (countTowns ==0){
            System.out.println("No town names were affected.");
            return;
        }
            System.out.printf("%d town names were affected.%n",countTowns);

        PreparedStatement selectTowns = connection.prepareStatement("SELECT name from towns WHERE country = ?");
        selectTowns.setString(1,country);
        ResultSet townSet = selectTowns.executeQuery();

        List<String> townsName = new ArrayList<>();
        while (townSet.next()){
            String name = townSet.getString("name");
            townsName.add(name);
        }

        System.out.println(townsName);

        connection.close();
    }
}
