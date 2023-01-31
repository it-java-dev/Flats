package org.example.Service;

import org.example.Entity.Flat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class FlatService {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");;
    static EntityManager em = emf.createEntityManager();
    static FlatService fs;
    static MyRandomService myRnd;
    static ChangeService changeS;
    static SearchService searchS;




        /*try {
            while (true) {
                System.out.println("1: add flat");
                System.out.println("2: add random flat");
                System.out.println("3: delete flat");
                System.out.println("4: change flat");
                System.out.println("5: view flat");
                System.out.println("6: search the flat by parameters");
                System.out.println("7: exit");
                System.out.print("-> ");

                String s = sc.nextLine();
                if (s.equals("1")) {
                    flatS.addFlat(sc);
                } else if (s.equals("2")) {
                    myRnd.insertRandomFlats(sc);
                } else if (s.equals("3")) {
                    flatS.deleteFlat(sc);
                } else if (s.equals("4")) {
                    changeS.changeFlatByParameters(sc);
                } else if (s.equals("5")) {
                    viewFlats();
                } else if (s.equals("6")) {
                    searchS.searchFlatsByParameters(sc);
                } else if (s.equals("7")) {
                    break;
                }else {
                    break;
                }
            }
        } finally {
            sc.close();
            em.close();
            emf.close();
        }*/

    public static void mainMenu(Scanner sc) {
        try {
            while (true) {
                System.out.println("1: add flat");
                System.out.println("2: add random flat");
                System.out.println("3: delete flat");
                System.out.println("4: change flat");
                System.out.println("5: view flat");
                System.out.println("6: search the flat by parameters");
                System.out.println("7: exit");
                System.out.print("-> ");

                String s = sc.nextLine();
                if (s.equals("1")) {
                    fs.addFlat(sc);
                } else if (s.equals("2")) {
                    myRnd.insertRandomFlats(sc);
                } else if (s.equals("3")) {
                    fs.deleteFlat(sc);
                } else if (s.equals("4")) {
                    changeS.changeFlatByParameters(sc);
                } else if (s.equals("5")) {
                    viewFlats();
                } else if (s.equals("6")) {
                    searchS.searchFlatsByParameters(sc);
                } else if (s.equals("7")) {
                    break;
                }else {
                    break;
                }
            }
        } finally {
            sc.close();
            em.close();
            emf.close();
        }
    }
    private static void addFlat(Scanner sc) {
        System.out.print("Enter flat region: ");
        String flatRegion = sc.nextLine();
        System.out.print("Enter flat address: ");
        String flatAddress = sc.nextLine();
        try {
            System.out.print("Enter flat square: ");
            String sSquare = sc.nextLine();
            Double flatSquare = Double.valueOf(sSquare);
            System.out.print("Enter flat rooms: ");
            String sRooms = sc.nextLine();
            Integer flatRooms = Integer.valueOf(sRooms);
            System.out.print("Enter flat price: ");
            String sPrice = sc.nextLine();
            Double dPrice = Double.valueOf(sPrice);
            BigDecimal flatPrice = BigDecimal.valueOf(dPrice);
            em.getTransaction().begin();
            try {
                Flat f = new Flat(flatRegion, flatAddress, flatSquare, flatRooms, flatPrice);
                em.persist(f);
                em.getTransaction().commit();
                System.out.println(f.getId());

            } catch (Exception ex) {
                em.getTransaction().rollback();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Wrong parameter");
        }
    }

    private static void deleteFlat(Scanner sc) {
        System.out.print("Enter flat id: ");
        String flatId = sc.nextLine();
        long id = Long.parseLong(flatId);

        Flat f = em.getReference(Flat.class, id);
        if (f == null) {
            System.out.println("flat not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(f);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
    private static void viewFlats() {
        Query query = em.createQuery("SELECT f FROM Flat f", Flat.class);
        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat f : list)
            System.out.println(f);
    }

}
