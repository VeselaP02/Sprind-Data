import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Drivers driver1 = new Drivers("Iliqn Petkov");
        Drivers driver2 = new Drivers("Petur Ivanov");

        Truck truck = new Truck("Truck125",12588,"diesel",120.00);

        truck.addDrivers(driver1);
        truck.addDrivers(driver2);

        entityManager.persist(truck);
        entityManager.persist(driver1);
        entityManager.persist(driver2);



        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
