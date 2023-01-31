package org.example.Service;

import org.example.Entity.Flat;
import org.example.Random.MyRandom;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Scanner;

public class MyRandomService {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");;
    static EntityManager em = emf.createEntityManager();
    static MyRandom myRandom;
    static final java.util.Random RND = new java.util.Random();

    public static void insertRandomFlats(Scanner sc) {
        System.out.print("Enter flats count: ");
        String sCount = sc.nextLine();
        Integer count = Integer.valueOf(sCount);
        em.getTransaction().begin();
        try {
            if (count >= 1) {
                for (int i = 1; i < count+1; i++) {
                    Flat f = new Flat(randomRegion(), randomAddresses(), randomSquare(), randomRooms(), randomPrice());
                    em.persist(f);
                }
                em.getTransaction().commit();
            } else {
                System.out.println("Parameters of your flat can't be negative are zero!!!");
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static String randomRegion() {
        return myRandom.REGIONS[RND.nextInt(myRandom.REGIONS.length)];
    }

    private static String randomAddresses() {
        return myRandom.ADDRESSES[RND.nextInt(myRandom.ADDRESSES.length)] + " " + RND.nextInt(50);
    }

    private static Double randomSquare() {
        double min = 10.0;
        double max = 100.0;
        double mathResult = min + Math.random() * max;
        double scale = Math.pow(10, 2);
        double result = Math.ceil(mathResult * scale) / scale;
        return result;

    }

    private static Integer randomRooms() {
        int min = 1;
        int max = 4;
        int result = (int) (min + Math.random() * max);
        return result;
    }

    private static BigDecimal randomPrice() {
        double min = 10000;
        double max = 1000000;
        double mathResult = min + Math.random() * max;
        double scale = Math.pow(10,3);
        double result = Math.ceil(mathResult * scale) / scale;
        return BigDecimal.valueOf(result);
    }
}
