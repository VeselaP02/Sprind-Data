package DB_Apps_Introduction_Exercises;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Add_minion {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String user = scanner.nextLine();
        String password = scanner.nextLine();


        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String [] infoMinion = scanner.nextLine().split(" ");
        String minionName = infoMinion[1];
        int age = Integer.parseInt(infoMinion[2]);
        String town = infoMinion[3];
        String villainName = scanner.nextLine().split(" ")[1];

        int townId = getOrInsertTown(connection, town);
        int villainId = getOrInsertVillain(connection,villainName);

        PreparedStatement insertMinion = connection.prepareStatement("INSERT INTO minions(name,age,town_id) VALUES (?,?,?);");
        insertMinion.setString(1,minionName);
        insertMinion.setInt(2,age);
        insertMinion.setInt(3,townId);

        insertMinion.executeUpdate();

        PreparedStatement getLastMinionId = connection.prepareStatement("SELECT id FROM minions ORDER BY id DESC LIMIT 1;");
        ResultSet lastMinionSet = getLastMinionId.executeQuery();
        lastMinionSet.next();
        int lastMinionId = lastMinionSet.getInt("id");

        PreparedStatement insertVillains_minions = connection.prepareStatement("INSERT INTO minions_villains(minion_id,villain_id) VALUES (?,?);");
        insertVillains_minions.setInt(1,lastMinionId);
        insertVillains_minions.setInt(2,villainId);

        System.out.printf("Successfully added %s to be minion of %s",minionName,villainName);

        connection.close();

    }

    private static int getOrInsertVillain(Connection connection, String villainName) throws  SQLException {
        PreparedStatement selectVillain = connection.prepareStatement("SELECT id FROM villains" +
                " WHERE name = ?;");
        selectVillain.setString(1,villainName);
        ResultSet villainSet = selectVillain.executeQuery();

        int villainId = 0;
        if (!villainSet.next()){
            PreparedStatement insertVillain = connection.prepareStatement("INSERT INTO villains(name,evilness_factor) VALUES (?,?);");
            insertVillain.setString(1,villainName);
            insertVillain.setString(2,"evil");
            insertVillain.executeUpdate();

            ResultSet newVillainSet = selectVillain.executeQuery();
            newVillainSet.next();

            villainId = newVillainSet.getInt("id");
            System.out.printf("Villain %s was added to the database.%n",villainName);
        } else {
            villainId = villainSet.getInt("id");
        }
        return 0;
    }

    private static int getOrInsertTown(Connection connection, String town) throws SQLException {
        PreparedStatement townStatement = connection.prepareStatement("SELECT id FROM towns WHERE name = ?");

        townStatement.setString(1,town);
        ResultSet townSet = townStatement.executeQuery();

        int townId = 0;
        if (!townSet.next()){
            PreparedStatement insertTown = connection.prepareStatement("INSERT INTO towns(name) VALUES (?);");
            insertTown.setString(1,town);
            insertTown.executeUpdate();

            ResultSet newTownSet = townStatement.executeQuery();
            newTownSet.next();

            townId = newTownSet.getInt("id");
            System.out.printf("Town %s was added to the database.%n",town);
        } else {
            townId = townSet.getInt("id");
        }
        return townId;
    }
}
