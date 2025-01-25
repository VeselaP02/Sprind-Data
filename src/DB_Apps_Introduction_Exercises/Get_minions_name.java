package DB_Apps_Introduction_Exercises;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Get_minions_name {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String user = scanner.nextLine();
        String password = scanner.nextLine();


        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement villainStatement = connection.prepareStatement("SELECT name from villains where id = ?");


        int villainId = Integer.parseInt(scanner.nextLine());

        villainStatement.setInt(1,villainId);
        ResultSet villainSet = villainStatement.executeQuery();

        if (!villainSet.next()){
            System.out.printf("No villain with ID %d exists in the database.",villainId);
            return;
        }

        PreparedStatement minionsNameStatement = connection.prepareStatement("SELECT m.name,m.age FROM villains as v" +
                " JOIN minions_villains as mv ON v.id = mv.villain_id" +
                " JOIN minions as m on m.id = mv.minion_id" +
                " WHERE v.id = ?");

        minionsNameStatement.setInt(1,villainId);
        ResultSet minionSet = minionsNameStatement.executeQuery();

        String villainName = villainSet.getString("name");
        System.out.println("Villain: "+ villainName);

        int index = 1;
        while (minionSet.next()){
            String minionName = minionSet.getString("m.name");
            int ageMinion = minionSet.getInt("m.age");
            System.out.printf("%d. %s %d%n",index,minionName,ageMinion);
            index++;
        }
connection.close();

    }
}
