package DB_Apps_Introduction_Exercises;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class RemoveVillains {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String user = scanner.nextLine();
        String password = scanner.nextLine();


        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);


        int villainId = Integer.parseInt(scanner.nextLine());
        PreparedStatement selectVillain= connection.prepareStatement("SELECT name FROM villains WHERE id = ?");
        selectVillain.setInt(1,villainId);
        ResultSet villainSet = selectVillain.executeQuery();

        if (!villainSet.next()){
            System.out.println("No such villain was found");
            return;
        }

        String villainName = villainSet.getString("name");

        PreparedStatement selectAllVillainMinions  =connection.prepareStatement("SELECT COUNT(DISTINCT minion_id) as m_count FROM minions_villains where villain_id = ?");
        selectAllVillainMinions.setInt(1,villainId);
        ResultSet minionSet = selectAllVillainMinions.executeQuery();
        minionSet.next();
        int countMinionRelease = minionSet.getInt("m_count");

        connection.setAutoCommit(false);

        try {
            PreparedStatement deletedMinionsVillains = connection.prepareStatement("DELETE FROM minions_villains WHERE villain_id  = ?");
            deletedMinionsVillains.setInt(1,villainId);
            deletedMinionsVillains.executeUpdate();

            PreparedStatement deletedVillains = connection.prepareStatement("DELETE FROM villains WHERE id  = ?");
            deletedVillains.setInt(1,villainId);
            deletedVillains.executeUpdate();

            connection.commit();
            System.out.println(villainName + " was deleted");
            System.out.println(countMinionRelease + " minions released");
        } catch (SQLException sql){
            connection.rollback();
        }

        connection.close();
    }
}
