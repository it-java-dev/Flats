package org.example.Service;

import org.example.Entity.Flat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class ChangeService {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");;
    static EntityManager em = emf.createEntityManager();
    public static void changeFlatByParameters(Scanner sc) {
        try {
            while (true) {
                System.out.println("1: change the flat region");
                System.out.println("2: change the flat address");
                System.out.println("3: change the flat square");
                System.out.println("4: change the flat rooms");
                System.out.println("5: change the flat price");
                System.out.println("6: previous menu");
                System.out.print("-> ");

                String s = sc.nextLine();
                if (s.equals("1")) {
                    changeFlatRegion(sc);
                } else if (s.equals("2")) {
                    changeFlatAddress(sc);
                } else if (s.equals("3")) {
                    changeFlatSquare(sc);
                } else if (s.equals("4")) {
                    changeFlatRooms(sc);
                } else if (s.equals("5")) {
                    changeFlatPrice(sc);
                } else if (s.equals("6")) {
                    return;
                } else {
                    return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void changeFlatRegion(Scanner sc) {
        System.out.print("Enter flat address: ");
        String flatAddress = sc.nextLine();
        System.out.print("Enter new flat region: ");
        String flatRegion = sc.nextLine();
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address", Flat.class);
            query.setParameter("address", flatAddress);

            flat = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Flat not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            flat.setRegion(flatRegion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeFlatAddress(Scanner sc) {
        System.out.print("Enter old flat address: ");
        String oldFlatAddress = sc.nextLine();
        System.out.print("Enter new flat address: ");
        String newFlatAddress = sc.nextLine();
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address", Flat.class);
            query.setParameter("address", oldFlatAddress);

            flat = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Flat not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            flat.setAddress(newFlatAddress);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeFlatSquare(Scanner sc) {
        System.out.print("Enter flat address: ");
        String flatAddress = sc.nextLine();
        System.out.print("Enter flat square: ");
        String sSquare = sc.nextLine();
        Double flatSquare = Double.valueOf(sSquare);
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address", Flat.class);
            query.setParameter("address", flatAddress);

            flat = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Flat not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            flat.setSquare(flatSquare);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeFlatRooms(Scanner sc) {
        System.out.print("Enter flat address: ");
        String flatAddress = sc.nextLine();
        System.out.print("Enter flat rooms: ");
        String sRooms = sc.nextLine();
        Integer flatRooms = Integer.valueOf(sRooms);
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address", Flat.class);
            query.setParameter("address", flatAddress);

            flat = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Flat not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            flat.setRooms(flatRooms);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeFlatPrice(Scanner sc) {
        System.out.print("Enter flat address: ");
        String flatAddress = sc.nextLine();
        System.out.print("Enter new flat price: ");
        String sPrice = sc.nextLine();
        Double dPrice = Double.valueOf(sPrice);
        BigDecimal flatPrice = BigDecimal.valueOf(dPrice);
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address", Flat.class);
            query.setParameter("address", flatAddress);

            flat = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Flat not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            flat.setPrice(flatPrice);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
}
