package DB_Apps_Introduction_Exercises;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Scanner;

public class PrintAllMinions {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        String user = scanner.nextLine();
        String password = scanner.nextLine();


        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement selectAllMinions = connection.prepareStatement("SELECT name FROM minions");
        ResultSet minionSet = selectAllMinions.executeQuery();

        ArrayDeque<String> deque = new ArrayDeque<>();
        while (minionSet.next()){
            String minionName = minionSet.getString("name");
            deque.addLast(minionName);

        }
        while (!deque.isEmpty()){
            String firstName = deque.removeFirst();
            System.out.println(firstName);
            if (!deque.isEmpty()){
                String lastName = deque.removeLast();
                System.out.println(lastName);
            }
        }
    }
}
