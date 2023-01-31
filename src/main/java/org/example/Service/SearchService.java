package org.example.Service;

import org.example.Entity.Flat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class SearchService {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
    static EntityManager em = emf.createEntityManager();

    public static void searchFlatsByParameters(Scanner sc) {
        try {
            while (true) {
                System.out.println("1: search the flats by region");
                System.out.println("2: search the flats by address");
                System.out.println("3: search the flats by square");
                System.out.println("4: search the flats by rooms");
                System.out.println("5: search the flats by price");
                System.out.println("6: search the flats by all parameters");
                System.out.println("7: previous menu");
                System.out.print("-> ");

                String s = sc.nextLine();
                if (s.equals("1")) {
                    searchFlatsByRegion(sc);
                } else if (s.equals("2")) {
                    searchFlatsByAddress(sc);
                } else if (s.equals("3")) {
                    searchFlatsBySquare(sc);
                } else if (s.equals("4")) {
                    searchFlatsByRooms(sc);
                } else if (s.equals("5")) {
                    searchFlatsByPrice(sc);
                } else if (s.equals("6")) {
                    searchFlats(sc);
                } else if (s.equals("7")) {
                    return;
                } else {
                    return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void searchFlats(Scanner sc) {
        System.out.print("Enter region: ");
        String sRegion = sc.nextLine();
        String region = '%' + sRegion + '%';
        System.out.print("Rooms count no more than: ");
        String sRooms = sc.nextLine();
        Integer rooms = Integer.valueOf(sRooms);
        System.out.print("Price no more than : ");
        String sPrice = sc.nextLine();
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(sPrice));
        System.out.print("Square no more than : ");
        String sSquare = sc.nextLine();
        Double square = Double.parseDouble(sSquare);
        Query query = em.createQuery(
                "SELECT x FROM Flat x WHERE x.region like :region and x.rooms <= :rooms and x.price <= :price and x.square <= :square ORDER BY x.price", Flat.class);
        query.setParameter("region", region);
        query.setParameter("price", price);
        query.setParameter("rooms", rooms);
        query.setParameter("square", square);
        List<Flat> list = (List<Flat>) query.getResultList();
        for (Flat x : list)
            System.out.println(x);
    }

    private static void searchFlatsByRegion(Scanner sc) {
        System.out.println("Enter region which you want");
        String flatRegion = sc.nextLine();
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.region = :region", Flat.class);
            query.setParameter("region", flatRegion);
            List<Flat> list = (List<Flat>) query.getResultList();
            for (Flat x : list)
                System.out.println(x);
        } catch (NoResultException ex) {
            System.out.println("No any matches...");
        }

    }

    private static void searchFlatsByAddress(Scanner sc) {
        System.out.println("Enter address which your want");
        String flatAddress = sc.nextLine();
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address ORDER BY x.address", Flat.class);
            query.setParameter("address", flatAddress);
            List<Flat> list = (List<Flat>) query.getResultList();
            for (Flat x : list)
                System.out.println(x);
        } catch (NoResultException ex) {
            System.out.println("No any matches...");
        }

    }

    private static void searchFlatsBySquare(Scanner sc) {
        System.out.print("Square no more then : ");
        String sSquare = sc.nextLine();
        Double flatSquare = Double.valueOf(sSquare);
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.square <= :square ORDER BY x.square", Flat.class);
            query.setParameter("square", flatSquare);
            List<Flat> list = query.getResultList();
            for (Flat x : list)
                System.out.println(x);
        } catch (NoResultException ex) {
            System.out.println("No any matches...");
        }

    }

    private static void searchFlatsByRooms(Scanner sc) {
        System.out.print("Quantity of rooms no more then : ");
        String sRooms = sc.nextLine();
        Integer flatRooms = Integer.valueOf(sRooms);
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.rooms <= :rooms ORDER BY x.rooms", Flat.class);
            query.setParameter("rooms", flatRooms);
            List<Flat> list = (List<Flat>) query.getResultList();
            for (Flat x : list)
                System.out.println(x);
        } catch (NoResultException ex) {
            System.out.println("No any matches...");
        }

    }

    private static void searchFlatsByPrice(Scanner sc) {
        System.out.print("Price no more than : ");
        String sPrice = sc.nextLine();
        BigDecimal flatPrice = BigDecimal.valueOf(Long.parseLong(sPrice));
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.price <= :price ORDER BY x.price", Flat.class);
            query.setParameter("price", flatPrice);
            List<Flat> list = (List<Flat>) query.getResultList();
            for (Flat x : list)
                System.out.println(x);
        } catch (NoResultException ex) {
            System.out.println("No any matches...");
        }

    }
}
